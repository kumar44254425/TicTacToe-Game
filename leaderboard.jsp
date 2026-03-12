<%@ page import="java.util.*,com.tictactoe.model.Player" %>

<html>

<head>
<link rel="stylesheet" href="css/main.css">
<link rel="stylesheet" href="css/leaderboard.css">
</head>

<body>

<div class="container">

<h1>Leaderboard</h1>

<table>

<tr>
<th>Player</th>
<th>Wins</th>
</tr>

<%

List<Player> players=(List<Player>)request.getAttribute("players");

for(Player p:players){

%>

<tr>
<td><%=p.getName()%></td>
<td><%=p.getWins()%></td>
</tr>

<% } %>

</table>

</div>

</body>
</html>