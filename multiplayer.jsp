<%@ page session="true" %>

<%
String roomId = request.getParameter("room");
String player = (String)session.getAttribute("username");
%>

<html>

<head>
<title>Multiplayer Tic Tac Toe</title>

<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link rel="stylesheet" href="css/multiplayer.css">

</head>

<body>

<div class="game-area">

<h2 class="players">Loading players...</h2>

<h3 id="turn">Loading...</h3>

<div id="board">

<button onclick="play(0)"></button>
<button onclick="play(1)"></button>
<button onclick="play(2)"></button>

<button onclick="play(3)"></button>
<button onclick="play(4)"></button>
<button onclick="play(5)"></button>

<button onclick="play(6)"></button>
<button onclick="play(7)"></button>
<button onclick="play(8)"></button>

</div>

<h2 id="winner"></h2>

</div>
<h2 id="winner"></h2>

<div class="game-buttons">

<button onclick="goBack()"> Go Back</button>

<button onclick="playAgain()"> Play Again</button>

</div>

<script>

let roomId="<%=roomId%>";
let player="<%=player%>";

</script>

<script src="js/multiplayer.js"></script>

</body>

</html>