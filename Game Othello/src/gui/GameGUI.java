package gui;

import logic.Game;
import utils.Constants;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GameGUI extends JFrame {
    private Game game;
    private JButton[][] buttons;
    private JLabel statusLabel;
    private boolean vsAI = false;
    private int aiLevel = Constants.AI_EASY;

    public GameGUI() {
        showStartScreen();
    }

    private void showStartScreen() {
        JFrame startFrame = new JFrame("Cờ Lật - Othello");
        startFrame.setSize(520, 600);
        startFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        startFrame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(30, 30, 30));

        JLabel title = new JLabel("Cờ Lật - OTHELLO", SwingConstants.CENTER);
        title.setFont(new Font("Verdana", Font.BOLD, 38));
        title.setForeground(new Color(255, 80, 80));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setBorder(BorderFactory.createEmptyBorder(30, 0, 30, 0));

        panel.add(title);

        String[] options = {
                "Người vs Người",
                "Người vs Máy (Dễ)",
                "Người vs Máy (Trung bình )",
                "Người vs Máy (Khó)"
        };

        for (int i = 0; i < options.length; i++) {
            JButton btn = createStyledButton(options[i]);
            int level = i;
            btn.addActionListener(e -> {
                vsAI = (level != 0);
                aiLevel = switch (level) {
                    case 1 -> Constants.AI_EASY;
                    case 2 -> Constants.AI_HARD;
                    case 3 -> Constants.AI_SUPER_HARD;
                    default -> Constants.AI_EASY;
                };
                startFrame.dispose();
                initGame();
            });
            panel.add(btn);
            panel.add(Box.createVerticalStrut(10));
        }

        JButton btnExit = createStyledButton("Thoát");
        btnExit.addActionListener(e -> System.exit(0));
        panel.add(btnExit);

        startFrame.add(panel);
        startFrame.setVisible(true);
    }

    private JButton createStyledButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 18));
        btn.setBackground(new Color(50, 50, 50));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn.setPreferredSize(new Dimension(250, 45));
        btn.setMaximumSize(new Dimension(250, 45));
        btn.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn.setBackground(new Color(80, 0, 0));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn.setBackground(new Color(50, 50, 50));
            }
        });
        return btn;
    }

    private void initGame() {
        game = new Game(vsAI, aiLevel);
        setTitle("Cờ Lật - Othello");
        setSize(740, 840);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.BLACK);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.BLACK);

        JPanel boardPanel = new JPanel(new GridLayout(Constants.BOARD_SIZE, Constants.BOARD_SIZE));
        boardPanel.setBackground(Color.BLACK);
        buttons = new JButton[Constants.BOARD_SIZE][Constants.BOARD_SIZE];

        for (int i = 0; i < Constants.BOARD_SIZE; i++) {
            for (int j = 0; j < Constants.BOARD_SIZE; j++) {
                JButton button = new JButton();
                button.setBackground(new Color(40, 40, 40));
                button.setOpaque(true);
                button.setPreferredSize(new Dimension(65, 65));
                button.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
                button.setFocusable(false);
                int row = i, col = j;
                button.addActionListener(e -> handleMove(row, col));
                buttons[i][j] = button;
                boardPanel.add(button);
            }
        }

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        controlPanel.setBackground(Color.BLACK);

        JButton btnSurrender = createStyledButton("Đầu hàng");
        JButton btnQuit = createStyledButton("Thoát game");

        btnSurrender.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn đầu hàng không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                String winner = (game.getCurrentPlayer() == Constants.BLACK) ? "Đỏ thắng!" : "Đen thắng!";
                JOptionPane.showMessageDialog(this, winner + " (Đối phương đầu hàng)", "Kết thúc", JOptionPane.INFORMATION_MESSAGE);
                dispose();
                SwingUtilities.invokeLater(GameGUI::new);
            }
        });

        btnQuit.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this, "Bạn có muốn thoát khỏi trò chơi không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });

        controlPanel.add(btnSurrender);
        controlPanel.add(btnQuit);

        statusLabel = new JLabel(" ", SwingConstants.CENTER);
        statusLabel.setFont(new Font("Consolas", Font.BOLD, 22));
        statusLabel.setForeground(Color.WHITE);
        statusLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        mainPanel.add(statusLabel, BorderLayout.NORTH);
        mainPanel.add(boardPanel, BorderLayout.CENTER);
        mainPanel.add(controlPanel, BorderLayout.SOUTH);

        add(mainPanel);
        setVisible(true);
        updateBoard();
    }

    private void handleMove(int row, int col) {
        if (!game.getBoard().isValidMove(row, col, game.getCurrentPlayer())) {
            JOptionPane.showMessageDialog(this, "Nước đi không hợp lệ!", "Lỗi", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (game.playMove(row, col)) {
            updateBoard();

            if (vsAI && game.getCurrentPlayer() == Constants.WHITE && !game.isGameOver()) {
                statusLabel.setText("Máy đang suy nghĩ...");
                statusLabel.setForeground(Color.ORANGE);

                Timer aiMoveTimer = new Timer(1500, e -> {
                    game.playAIMove();
                    updateBoard();
                });
                aiMoveTimer.setRepeats(false);
                aiMoveTimer.start();
            }
        }
    }

    private void updateBoard() {
        int[][] board = game.getBoard().getBoard();
        List<logic.Move> validMoves = game.getBoard().getValidMoves(game.getCurrentPlayer());

        for (int i = 0; i < Constants.BOARD_SIZE; i++) {
            for (int j = 0; j < Constants.BOARD_SIZE; j++) {
                JButton btn = buttons[i][j];
                btn.setIcon(null);
                btn.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));

                if (board[i][j] == Constants.BLACK) {
                    btn.setIcon(new CircleIcon(new Color(20, 20, 20), new Color(100, 100, 100)));
                } else if (board[i][j] == Constants.WHITE) {
                    btn.setIcon(new CircleIcon(new Color(220, 0, 0), new Color(255, 80, 80)));
                }
            }
        }

        for (logic.Move m : validMoves) {
            buttons[m.row][m.col].setBorder(BorderFactory.createLineBorder(Color.YELLOW, 3));
        }

        if (game.isGameOver()) {
            int black = game.getBoard().countPieces(Constants.BLACK);
            int white = game.getBoard().countPieces(Constants.WHITE);
            String winner = black > white ? "Đen thắng!" : (white > black ? "Đỏ thắng!" : "Hòa!");
            statusLabel.setText("Kết thúc - " + winner);

            SwingUtilities.invokeLater(() -> {
                int replay = JOptionPane.showConfirmDialog(this,
                        winner + "\nBạn có muốn chơi lại không?",
                        "Trò chơi kết thúc",
                        JOptionPane.YES_NO_OPTION);

                if (replay == JOptionPane.YES_OPTION) {
                    dispose();
                    SwingUtilities.invokeLater(GameGUI::new);
                } else {
                    System.exit(0);
                }
            });
        } else {
            int black = game.getBoard().countPieces(Constants.BLACK);
            int white = game.getBoard().countPieces(Constants.WHITE);
            String turn = game.getCurrentPlayer() == Constants.BLACK ? "Đen" : "Đỏ";
            statusLabel.setText("Lượt của: " + turn + "  |  Đen: " + black + " - Đỏ: " + white);
            statusLabel.setForeground(game.getCurrentPlayer() == Constants.BLACK ? Color.WHITE : Color.RED);
        }
    }

    private static class CircleIcon implements Icon {
        private final Color inner;
        private final Color outer;

        public CircleIcon(Color inner, Color outer) {
            this.inner = inner;
            this.outer = outer;
        }

        public int getIconWidth() { return 48; }
        public int getIconHeight() { return 48; }

        public void paintIcon(Component c, Graphics g, int x, int y) {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.85f));
            GradientPaint gp = new GradientPaint(x, y, outer, x + getIconWidth(), y + getIconHeight(), inner);
            g2d.setPaint(gp);
            g2d.fillOval(x + 6, y + 6, getIconWidth(), getIconHeight());
            g2d.dispose();
        }
    }

    public static void main(String[] args) {
        UIManager.put("OptionPane.messageFont", new Font("Segoe UI", Font.PLAIN, 18));
        UIManager.put("OptionPane.buttonFont", new Font("Segoe UI", Font.BOLD, 16));
        UIManager.put("OptionPane.background", new Color(30, 30, 30));
        UIManager.put("Panel.background", new Color(30, 30, 30));
        UIManager.put("OptionPane.messageForeground", Color.WHITE);
        UIManager.put("Button.background", new Color(60, 60, 60));
        UIManager.put("Button.foreground", Color.WHITE);

        SwingUtilities.invokeLater(GameGUI::new);
    }
}