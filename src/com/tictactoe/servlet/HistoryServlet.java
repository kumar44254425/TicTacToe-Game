package com.tictactoe.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;

import com.tictactoe.dao.GameDAO;
import com.tictactoe.model.Game;

public class HistoryServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        GameDAO dao = new GameDAO();

        List<Game> games = dao.getGames();

        req.setAttribute("games", games);

        RequestDispatcher rd = req.getRequestDispatcher("history.jsp");

        rd.forward(req, res);
    }
}