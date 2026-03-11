<%@ page session="true" %>

<!DOCTYPE html>
<html>

<head>

<title>Game</title>

<link rel="stylesheet" href="css/game.css">

</head>

<body>

<h2>${sessionScope.player1} VS ${sessionScope.player2}</h2>

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

<br>

<form action="saveGame" method="post">

<input type="hidden" name="winner" id="winner">

<button type="submit">Save Result</button>

</form>

<script src="js/script.js"></script>

</body>

</html>