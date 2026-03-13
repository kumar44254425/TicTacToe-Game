package com.tictactoe.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;
import java.util.UUID;
import com.tictactoe.util.DBConnection;

public class CreateRoomServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        String player = req.getParameter("player");

        HttpSession session = req.getSession();
        session.setAttribute("username", player);
        session.setAttribute("player1", player);

        String roomId = UUID.randomUUID().toString().substring(0, 6);

        try {

            Connection con = DBConnection.getConnection();

            String board = "-,-,-,-,-,-,-,-,-";

            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO rooms(room_id,player1,player2,board,turn) VALUES(?,?,?,?,?)");

            ps.setString(1, roomId);
            ps.setString(2, player);
            ps.setString(3, null);
            ps.setString(4, board);
            ps.setString(5, "X");

            ps.executeUpdate();

            // redirect to room page
            res.sendRedirect("room.jsp?room=" + roomId);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}