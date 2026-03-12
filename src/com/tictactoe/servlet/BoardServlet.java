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

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(
                    "select board from rooms where room_id=?");

            ps.setString(1, room);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                String board = rs.getString("board");

                res.setContentType("application/json");

                res.getWriter().print(
                        "{\"board\":[\"" + board.replace(",", "\",\"") + "\"]}");

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}