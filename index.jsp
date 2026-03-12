<%
String user = (String)session.getAttribute("user");

if(user == null){
response.sendRedirect("login.jsp");
}
%>

<!DOCTYPE html>
<html>

<head>

<title>Tic Tac Toe</title>

<link rel="stylesheet" href="css/main.css">
<link rel="stylesheet" href="css/index.css">

</head>

<body>

<h1 class="game-title">TIC TAC TOE</h1>

<div class="game-wrapper">

<!-- LOCAL GAME -->

<div class="game-box">

<h2>Local Game</h2>

<form action="game" method="post">

<input type="text" name="player1" placeholder="Player 1 Name" required>

<input type="text" name="player2" placeholder="Player 2 Name" required>

<button class="start-btn">Start Game</button>

</form>

</div>


<!-- ONLINE GAME -->

<div class="game-box">

<h2>Online Multiplayer</h2>

<a href="createRoom.jsp" class="room-btn">Create Room</a>

<br><br>

<a href="joinRoom.jsp" class="join-btn">Join Room</a>

</div>

</div>


<!-- BOTTOM BUTTONS -->

<div class="bottom-buttons">

<a href="history" class="room-btn">View Game History</a>

<br><br>

<a href="logout" class="join-btn">Logout</a>

</div>

</body>

</html>