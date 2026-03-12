package com.tictactoe.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;

import com.tictactoe.dao.GameDAO;
import com.tictactoe.dao.LeaderboardDAO;
import com.tictactoe.model.Game;
import com.tictactoe.model.Player;

public class HistoryServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        // Game history
        GameDAO dao = new GameDAO();
        List<Game> games = dao.getGames();
        req.setAttribute("games", games);

        // Leaderboard
        LeaderboardDAO ldao = new LeaderboardDAO();
        List<Player> players = ldao.getLeaderboard();
        req.setAttribute("leaderboard", players);

        RequestDispatcher rd = req.getRequestDispatcher("history.jsp");
        rd.forward(req, res);
    }
}