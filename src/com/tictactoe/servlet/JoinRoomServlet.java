package com.tictactoe.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;
import com.tictactoe.util.DBConnection;

public class JoinRoomServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        String player = req.getParameter("player");
        String roomId = req.getParameter("room");

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(
                    "UPDATE rooms SET player2=? WHERE room_id=?");

            ps.setString(1, player);
            ps.setString(2, roomId);

            ps.executeUpdate();

            res.sendRedirect("game.jsp?room=" + roomId);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}