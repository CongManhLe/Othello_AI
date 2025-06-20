# 🕹️ CỜ LẬT – OTHELLO AI

## 📌 Giới thiệu

**Cờ Lật – Othello** là một trò chơi chiến thuật nổi tiếng dành cho 2 người chơi. Trò chơi được phát triển bằng **Java (Swing GUI)** với các chức năng nổi bật:

- 🎮 Hỗ trợ chơi giữa hai người hoặc người chơi với máy (AI).
- 🧠 Tích hợp AI nhiều cấp độ: **Dễ**, **Trung bình**, **Khó**.
- 🧩 Ứng dụng thuật toán **Minimax** và **Alpha-Beta Pruning** để tối ưu chiến lược.
- 🎨 Giao diện đồ họa trực quan, bàn cờ 8x8 với quân cờ hiệu ứng đổ bóng mờ hiện đại.
- 💡 Dự án giúp sinh viên thực hành **lập trình hướng đối tượng**, **thiết kế giao diện người dùng** và **ứng dụng AI** trong phát triển trò chơi.

---

## 💻 Yêu cầu hệ thống

- **Phần mềm**: Java JDK 8 trở lên  
- **IDE đề xuất**: IntelliJ IDEA / Eclipse / NetBeans  
- **Phần cứng**:
  - RAM: 4GB trở lên
  - CPU: 2 nhân trở lên
- **Hệ điều hành**: Windows 10 / macOS / Linux

---

## 🚀 Cài đặt & Chạy ứng dụng

### 1. Clone dự án

```bash
git clone https://github.com/[tên người dùng]/Othello_AI.git
cd Othello_AI
2. Cấu trúc thư mục
css
Copy
Edit
Othello_AI/
├── ai/         # Các lớp AI (Random, Minimax, SuperHard)
├── gui/        # Giao diện người dùng (GameGUI.java)
├── logic/      # Xử lý logic game (Board.java, Game.java, Move.java)
├── utils/      # Hằng số hệ thống
└── Main.java   # Điểm khởi động chương trình (có thể tích hợp vào GameGUI)
3. Chạy chương trình
Mở dự án bằng IDE bạn chọn (IntelliJ/Eclipse/NetBeans).

Mở GameGUI.java hoặc Main.java.

Nhấn Run để khởi động game.

🎯 Cách chơi
Mục tiêu
Lật càng nhiều quân của đối phương càng tốt.

Trò chơi kết thúc khi bàn cờ đầy hoặc không còn nước đi hợp lệ.

Bên có nhiều quân hơn sẽ thắng.

Các chế độ chơi
Người vs Người

Người vs Máy:

Dễ: Random AI – chọn nước đi ngẫu nhiên

Trung bình: Minimax AI – áp dụng thuật toán Minimax với độ sâu 3

Khó: Super Hard AI – dùng Minimax với Alpha-Beta Pruning & Heuristic nâng cao (độ sâu 6)

👨‍💻 Giao diện người dùng
Bàn cờ: 8x8, quân cờ đen và đỏ với hiệu ứng bóng mờ đẹp mắt.

Thanh trạng thái: Hiển thị lượt chơi, điểm số, kết quả trận đấu.

Chức năng chính:

Đầu hàng: Kết thúc ván chơi, xác định người thắng.

Thoát game: Đóng ứng dụng.

Chơi lại: Sau khi kết thúc có thể khởi động lại ván mới.

📌 Lưu ý
⏳ Ở chế độ AI Khó, máy có thể mất vài giây để tính toán – điều này là bình thường.

❌ Không thể click vào các ô không hợp lệ – sẽ có cảnh báo.

🔁 Sau khi kết thúc ván chơi, bạn có thể chọn Chơi lại hoặc quay về Menu chính.

📷 Hình ảnh minh họa (tuỳ chọn)
Bạn có thể thêm hình ảnh giao diện bằng cách chèn:

markdown
Copy
Edit
![Menu chính](./screenshots/menu.png)
![Giao diện chơi](./screenshots/board.png)
👨‍💻 Tác giả
Lê Công Mạnh
Nguyễn Đức Lương
Tạ Văn Long
Dự án được thực hiện trong khuôn khổ môn học Nhập môn Trí tuệ Nhân tạo – Khoa Vật lý 