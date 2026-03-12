<%@ page session="true" %>

<html>

<head>

<title>Tic Tac Toe</title>

<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link rel="stylesheet" href="css/main.css">
<link rel="stylesheet" href="css/responsive.css">
<link rel="stylesheet" href="css/game.css">

</head>

<body>

<%
String roomId = request.getParameter("room");
%>

<div class="game-area">

<h2 class="players">
<%=session.getAttribute("player1")%>
VS
<%=session.getAttribute("player2")%>
</h2>

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

</div>

<script>

let roomId = "<%=roomId%>";

/* SEND MOVE */

function play(index){

fetch("MoveServlet",{
method:"POST",
headers:{
"Content-Type":"application/x-www-form-urlencoded"
},
body:"room="+roomId+"&index="+index
})

}

/* UPDATE BOARD */

function updateBoard(board){

let cells = document.querySelectorAll("#board button");

let arr = board.split(",");

for(let i=0;i<9;i++){

cells[i].innerText = arr[i];

if(arr[i] != "-"){
cells[i].disabled = true;
}

}

}

/* UPDATE TURN */

function updateTurn(turn){

document.getElementById("turn").innerText = "Turn : " + turn;

}

/* REAL TIME UPDATE */

function refreshGame(){

fetch("BoardServlet?room="+roomId)
.then(res => res.json())
.then(data => {

updateBoard(data.board);
updateTurn(data.turn);

});

}

/* AUTO UPDATE EVERY 700ms */

setInterval(refreshGame,700);

/* FIRST LOAD */

refreshGame();

</script>

</body>
</html>