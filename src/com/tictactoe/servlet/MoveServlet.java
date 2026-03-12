package com.tictactoe.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;
import com.tictactoe.util.DBConnection;

public class MoveServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        String room = req.getParameter("room");
        int pos = Integer.parseInt(req.getParameter("pos"));

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(
                    "select board,turn from rooms where room_id=?");

            ps.setString(1, room);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                String board = rs.getString("board");
                String turn = rs.getString("turn");

                String arr[] = board.split(",");

                if (arr[pos].equals("")) {

                    arr[pos] = turn;

                    turn = turn.equals("X") ? "O" : "X";

                    String newBoard = String.join(",", arr);

                    PreparedStatement update = con.prepareStatement(
                            "update rooms set board=?,turn=? where room_id=?");

                    update.setString(1, newBoard);
                    update.setString(2, turn);
                    update.setString(3, room);

                    update.executeUpdate();

                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}