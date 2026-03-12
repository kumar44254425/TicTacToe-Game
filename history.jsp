<%@ page import="java.util.*,com.tictactoe.model.Game,com.tictactoe.model.Player" %>

<html>

<head>

<title>Game History</title>

<link rel="stylesheet" href="css/main.css">
<link rel="stylesheet" href="css/history.css">

</head>

<body>

<div class="history-page">

<h1 class="page-title">Game History</h1>

<div class="tables-wrapper">

<!-- Match History -->
<div class="table-box">

<h2>Match History</h2>

<table>

<tr>
<th>Player 1</th>
<th>Player 2</th>
<th>Winner</th>
</tr>

<%
List<Game> games = (List<Game>) request.getAttribute("games");

if(games!=null){
for(Game g : games){
%>

<tr>
<td><%=g.getPlayer1()%></td>
<td><%=g.getPlayer2()%></td>
<td><%=g.getWinner()%></td>
</tr>

<%
}
}
%>

</table>

</div>


<!-- Leaderboard -->
<div class="table-box">

<h2>Leaderboard</h2>

<table>

<tr>
<th>Rank</th>
<th>Player</th>
<th>Wins</th>
</tr>

<%
List<Player> lb = (List<Player>) request.getAttribute("leaderboard");

int rank=1;

if(lb!=null){
for(Player p : lb){
%>

<tr>
<td><%=rank++%></td>
<td><%=p.getName()%></td>
<td><%=p.getWins()%></td>
</tr>

<%
}
}
%>

</table>

</div>

</div>

<div class="back-container">
<a href="index.jsp" class="back-btn">Back to Game</a>
</div>

</div>

</body>

</html>