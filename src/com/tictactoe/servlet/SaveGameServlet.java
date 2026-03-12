package com.tictactoe.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.*;
import javax.servlet.http.*;

import com.tictactoe.dao.GameDAO;
import com.tictactoe.model.Game;
import com.tictactoe.util.DBConnection;

public class SaveGameServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        HttpSession session = req.getSession();

        String player1 = (String) session.getAttribute("player1");
        String player2 = (String) session.getAttribute("player2");

        String winner = req.getParameter("winner");

        Game g = new Game();

        g.setPlayer1(player1);
        g.setPlayer2(player2);
        g.setWinner(winner);

        GameDAO dao = new GameDAO();
        dao.saveGame(g);

        // UPDATE LEADERBOARD
        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(
            "INSERT INTO leaderboard(player,wins) VALUES(?,1) ON DUPLICATE KEY UPDATE wins=wins+1");

            if(winner.equals("X"))
                ps.setString(1, player1);
            else
                ps.setString(1, player2);

            ps.executeUpdate();

        } catch(Exception e){
            e.printStackTrace();
        }

        res.sendRedirect("history");

    }
}