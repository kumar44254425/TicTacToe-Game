<%@ page session="true" %>

<html>

<head>

<title>Tic Tac Toe</title>

<link rel="stylesheet" href="css/main.css">
<link rel="stylesheet" href="css/game.css">

</head>

<body>

<div class="game-area">

<h2 class="players">

<%=session.getAttribute("player1")%>

VS

<%=session.getAttribute("player2")%>

</h2>

<div id="board">

<button onclick="play(this)"></button>
<button onclick="play(this)"></button>
<button onclick="play(this)"></button>

<button onclick="play(this)"></button>
<button onclick="play(this)"></button>
<button onclick="play(this)"></button>

<button onclick="play(this)"></button>
<button onclick="play(this)"></button>
<button onclick="play(this)"></button>

</div>

<form action="saveGame" method="post">

<input type="hidden" name="player1" value="<%=session.getAttribute("player1")%>">
<input type="hidden" name="player2" value="<%=session.getAttribute("player2")%>">
<input type="hidden" id="winner" name="winner">

<br>

<button class="save-btn">Save Result</button>

</form>

</div>

<script src="js/game.js"></script>

</body>
</html>