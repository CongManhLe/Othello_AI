🕹️ CỜ LẬT – OTHELLO AI
📌 Giới thiệu
Cờ Lật – Othello là một trò chơi chiến thuật nổi tiếng dành cho 2 người chơi. Trò chơi được phát triển bằng Java (Swing GUI) với các chức năng:

🎮 Chơi giữa hai người chơi hoặc người chơi với máy (AI).

🧠 Tích hợp AI nhiều cấp độ: Dễ, Trung bình, Khó.

🧩 Sử dụng thuật toán Minimax và Alpha-Beta Pruning để tối ưu hóa chiến lược AI.

🎨 Giao diện đồ họa trực quan, bàn cờ 8x8, quân cờ hiệu ứng đổ bóng mờ hiện đại.

💡 Mục tiêu dự án là mô phỏng luật chơi đầy đủ của Othello và là cơ hội để sinh viên thực hành lập trình hướng đối tượng, thiết kế giao diện người dùng và áp dụng AI vào phát triển game.

💻 Yêu cầu hệ thống
Phần mềm:
Java JDK 8 hoặc cao hơn

IDE: IntelliJ IDEA, Eclipse, hoặc NetBeans

Phần cứng:
RAM: ≥ 4GB

CPU: 2 nhân trở lên

OS: Windows 10 trở lên (hoặc tương đương)

🚀 Cài đặt & Chạy ứng dụng
1. Clone hoặc tải source code:
bash
Copy
Edit
git clone https://github.com/[tên người dùng]/Othello_AI.git
2. Cấu trúc thư mục:
css
Copy
Edit
Othello_AI/
├── ai/                 # Các lớp AI (Random, Minimax, SuperHard)
├── gui/                # Giao diện người dùng (GameGUI)
├── logic/              # Xử lý logic game (Board, Game, Move)
├── utils/              # Hằng số hệ thống
└── Main.java           # Điểm khởi động chương trình
3. Chạy chương trình:
Mở file GameGUI.java hoặc Main.java trong IDE.

Nhấn Run.

Giao diện game sẽ hiển thị bàn cờ và menu chọn chế độ chơi.

🧠 Cách chơi
🎯 Mục tiêu:
Lật càng nhiều quân đối phương càng tốt. Khi bàn cờ đầy hoặc không còn nước đi hợp lệ, bên có nhiều quân hơn sẽ thắng.

🕹️ Các chế độ:
Người vs Người

Người vs Máy:

Dễ – Random AI

Trung bình – Minimax AI (độ sâu 3)

Khó – Super Hard AI (độ sâu 6, Alpha-Beta + Heuristic)

👨‍💻 Giao diện:
Bàn cờ: 8x8 với quân cờ đen và đỏ.

Thanh trạng thái hiển thị: lượt chơi, điểm số, thông báo kết thúc.

Nút:

Đầu hàng: Kết thúc trận đấu và xác định người thắng.

Thoát game: Đóng ứng dụng.

📌 Lưu ý
⏳ Ở chế độ AI Khó, có thể mất vài giây để AI đưa ra nước đi – đó là bình thường.

❌ Không thể click vào ô không hợp lệ – chương trình sẽ cảnh báo.

🔁 Sau mỗi ván, có thể chọn Chơi lại hoặc quay về menu chính.

📷 Hình ảnh minh họa
Menu chính	Giao diện chơi

👨‍💻 Tác giả
Lê Công Mạnh

Dự án thực hiện môn học Nhập môn Trí tuệ Nhân tạo
