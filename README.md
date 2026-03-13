# 🎮 Tic Tac Toe Multiplayer (Java Web App)

A real-time multiplayer Tic Tac Toe game built using **Java Servlets, JSP, MySQL, and WebSockets**.
Players can create a room, share the room ID, and play the game live from different devices.

---

# 🚀 Features

✔ User Signup and Login
✔ Email OTP verification
✔ Create Game Room
✔ Join Room using Room ID
✔ Real-time Multiplayer Gameplay
✔ Turn-based system (Player1 = X, Player2 = O)
✔ Prevent clicking same box twice
✔ Live board updates without refresh
✔ Winner detection
✔ Game history storage
✔ Leaderboard system

---

# 🧠 How Multiplayer Works

1. Player 1 creates a room.
2. The system generates a **Room ID**.
3. Player 2 joins using the Room ID.
4. Both players connect using **WebSocket**.
5. Moves are stored in **MySQL database**.
6. Server broadcasts updates instantly to both players.

---

# 🛠 Tech Stack

### Frontend

* HTML
* CSS
* JavaScript

### Backend

* Java Servlets
* JSP

### Database

* MySQL

### Real-Time Communication

* WebSocket API

### Server

* Apache Tomcat

---

# 📂 Project Structure

```
TicTacToe
│
├── css
│   ├── main.css
│   ├── responsive.css
│   └── multiplayer.css
│
├── js
│   ├── game.js
│   └── multiplayer.js
│
├── src
│   └── com
│       └── tictactoe
│           ├── servlet
│           │   ├── LoginServlet.java
│           │   ├── SignupServlet.java
│           │   ├── MoveServlet.java
│           │   └── BoardServlet.java
│           │
│           └── websocket
│               └── GameSocket.java
│
├── WEB-INF
│   └── web.xml
│
├── login.jsp
├── signup.jsp
├── multiplayer.jsp
└── game.jsp
```

---

# ⚙️ Installation

### 1️⃣ Clone Repository

```
git clone https://github.com/YOUR_USERNAME/TicTacToe.git
```

### 2️⃣ Import into IDE

Use:

* Eclipse
* IntelliJ IDEA

### 3️⃣ Configure Database

Create database:

```
tictactoe
```

Import tables:

```
users
rooms
games
leaderboard
```

Update database connection in:

```
DBConnection.java
```

---

### 4️⃣ Deploy on Tomcat

Copy project to:

```
apache-tomcat/webapps/
```

Start server:

```
startup.bat
```

Open browser:

```
http://localhost:8080/TicTacToe
```

---

# 🎮 Gameplay Example

Player1 creates room:

```
Room ID: 3f6a2c
```

Player2 joins using same ID.

Game starts:

```
X | O | X
O | X | -
- | - | O
```

Winner detected automatically.

---

# 📸 Screenshots

(Add screenshots here)

Example:

Login Page
Room Creation
Multiplayer Game Board

---

# 👨‍💻 Author

**Kumar Gosukula**

Java Full Stack Developer
GitHub: https://github.com/YOUR_USERNAME

---

# ⭐ Future Improvements

* Chat between players
* Matchmaking system
* Online leaderboard
* Mobile responsive UI
* Deploy on cloud server

---

# 📜 License

This project is for educational purposes.
