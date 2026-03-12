<%@ page language="java" %>

<%
String room = request.getParameter("room");
%>

<!DOCTYPE html>
<html>

<head>

<title>Game Room</title>

<link rel="stylesheet" href="css/main.css">
<link rel="stylesheet" href="css/roomgame.css">

</head>

<body>

<h1 class="title">Room ID: <%=room%></h1>

<div class="board">

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

<script src="js/game.js"></script>

</body>

</html>