package com.tictactoe.websocket;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.json.JSONObject;

import com.tictactoe.util.DBConnection;

@ServerEndpoint("/game")
public class GameSocket {

    static Set<Session> sessions = Collections.synchronizedSet(new HashSet<>());

    @OnOpen
    public void onOpen(Session session) {

        System.out.println("Player connected");

        sessions.add(session);

    }

    @OnClose
    public void onClose(Session session) {

        System.out.println("Player disconnected");

        sessions.remove(session);

    }

    @OnMessage
    public void onMessage(String message, Session session) {

        try {

            JSONObject obj = new JSONObject(message);

            /* ---------------- JOIN REQUEST ---------------- */

            if (obj.has("type") && obj.getString("type").equals("join")) {

                String room = obj.getString("room");

                Connection con = DBConnection.getConnection();

                PreparedStatement ps = con.prepareStatement(
                        "SELECT player1,player2,board,turn FROM rooms WHERE room_id=?");

                ps.setString(1, room);

                ResultSet rs = ps.executeQuery();

                if (rs.next()) {

                    JSONObject response = new JSONObject();

                    response.put("board", rs.getString("board"));
                    response.put("turn", rs.getString("turn"));
                    response.put("player1", rs.getString("player1"));
                    response.put("player2", rs.getString("player2"));

                    session.getBasicRemote().sendText(response.toString());

                }

                return;

            }

            /* ---------------- MOVE REQUEST ---------------- */

            String room = obj.getString("room");
            String player = obj.getString("player");
            int index = obj.getInt("index");

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
                } else {
                    symbol = "O";
                }

                /* check turn */

                if (!turn.equals(symbol))
                    return;

                String[] cells = board.split(",");

                /* check cell empty */

                if (!cells[index].equals("-"))
                    return;

                /* update board */

                cells[index] = symbol;

                /* switch turn */

                if (turn.equals("X"))
                    turn = "O";
                else
                    turn = "X";

                String newBoard = String.join(",", cells);

                /* update database */

                PreparedStatement update = con.prepareStatement(
                        "UPDATE rooms SET board=?,turn=? WHERE room_id=?");

                update.setString(1, newBoard);
                update.setString(2, turn);
                update.setString(3, room);

                update.executeUpdate();

                /* broadcast board */

                JSONObject response = new JSONObject();

                response.put("board", newBoard);
                response.put("turn", turn);
                response.put("player1", player1);
                response.put("player2", player2);

                for (Session s : sessions) {

                    s.getBasicRemote().sendText(response.toString());

                }

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

}