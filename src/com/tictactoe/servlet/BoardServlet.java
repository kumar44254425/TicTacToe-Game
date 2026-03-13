package com.tictactoe.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;

import com.tictactoe.util.DBConnection;

public class BoardServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        String room = req.getParameter("room");

        res.setContentType("application/json");

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(
                    "SELECT player1,player2,board,turn FROM rooms WHERE room_id=?");

            ps.setString(1, room);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                String board = rs.getString("board");
                String turn = rs.getString("turn");
                String player1 = rs.getString("player1");
                String player2 = rs.getString("player2");

                String json = "{\"board\":\"" + board + "\",\"turn\":\"" + turn + "\",\"player1\":\"" + player1
                        + "\",\"player2\":\"" + player2 + "\"}";

                res.getWriter().write(json);

            } else {

                res.getWriter().write("{\"board\":\"-,-,-,-,-,-,-,-,-\",\"turn\":\"X\"}");

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}