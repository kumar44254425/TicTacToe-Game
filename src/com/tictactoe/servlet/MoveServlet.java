package com.tictactoe.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;
import com.tictactoe.util.DBConnection;

public class MoveServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        String room = req.getParameter("room");
        int index = Integer.parseInt(req.getParameter("index"));

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM rooms WHERE room_id=?");

            ps.setString(1, room);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                String board = rs.getString("board");
                String turn = rs.getString("turn");

                String[] cells = board.split(",");

                if (cells[index].equals("-")) {

                    cells[index] = turn;

                    if (turn.equals("X"))
                        turn = "O";
                    else
                        turn = "X";

                }

                String newBoard = String.join(",", cells);

                PreparedStatement update = con.prepareStatement(
                        "UPDATE rooms SET board=?, turn=? WHERE room_id=?");

                update.setString(1, newBoard);
                update.setString(2, turn);
                update.setString(3, room);

                update.executeUpdate();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}