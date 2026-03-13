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
        String player = req.getParameter("player");
        int index = Integer.parseInt(req.getParameter("index"));

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(
                    "SELECT player1,player2,board,turn FROM rooms WHERE room_id=?");

            ps.setString(1, room);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                String player1 = rs.getString("player1");
                String player2 = rs.getString("player2");
                String board = rs.getString("board");
                String turn = rs.getString("turn");

                /* determine symbol */

                String symbol;

                if (player.equals(player1)) {
                    symbol = "X";
                } else if (player.equals(player2)) {
                    symbol = "O";
                } else {
                    return; // player not part of room
                }

                /* check turn */

                if (!turn.equals(symbol)) {
                    return;
                }

                String[] cells = board.split(",");

                /* check cell empty */

                if (!cells[index].equals("-")) {
                    return;
                }

                /* make move */

                cells[index] = symbol;

                /* switch turn */

                turn = turn.equals("X") ? "O" : "X";

                String newBoard = String.join(",", cells);

                PreparedStatement update = con.prepareStatement(
                        "UPDATE rooms SET board=?,turn=? WHERE room_id=?");

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