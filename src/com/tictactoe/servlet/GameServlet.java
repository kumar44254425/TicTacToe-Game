package com.tictactoe.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class GameServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String player1 = request.getParameter("player1");
        String player2 = request.getParameter("player2");

        HttpSession session = request.getSession();

        session.setAttribute("player1", player1);
        session.setAttribute("player2", player2);

        request.getRequestDispatcher("game.jsp").forward(request, response);
    }
}