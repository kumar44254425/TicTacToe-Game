package com.tictactoe.websocket;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.*;

import org.json.JSONObject;

import com.tictactoe.util.DBConnection;

@ServerEndpoint("/game")
public class GameSocket {

    /* store sessions by room */

    static Map<String, Set<Session>> rooms = new HashMap<>();

    @OnOpen
    public void onOpen(Session session) {

        System.out.println("Player connected");

    }

    @OnClose
    public void onClose(Session session) {

        System.out.println("Player disconnected");

        /* remove from room */

        rooms.values().forEach(set -> set.remove(session));

    }

    @OnMessage
    public void onMessage(String message, Session session) {

        try {

            JSONObject obj = new JSONObject(message);

            /* ---------- JOIN ROOM ---------- */

            if (obj.has("type") && obj.getString("type").equals("join")) {

                String room = obj.getString("room");

                /* add session to room */

                rooms.computeIfAbsent(room, k -> new HashSet<>()).add(session);

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

            /* ---------- MOVE ---------- */

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

                String symbol;

                if (player.equals(player1))
                    symbol = "X";
                else
                    symbol = "O";

                if (!turn.equals(symbol))
                    return;

                String[] cells = board.split(",");

                if (!cells[index].equals("-"))
                    return;

                cells[index] = symbol;

                if (turn.equals("X"))
                    turn = "O";
                else
                    turn = "X";

                String newBoard = String.join(",", cells);

                PreparedStatement update = con.prepareStatement(
                        "UPDATE rooms SET board=?,turn=? WHERE room_id=?");

                update.setString(1, newBoard);
                update.setString(2, turn);
                update.setString(3, room);

                update.executeUpdate();

                JSONObject response = new JSONObject();

                response.put("board", newBoard);
                response.put("turn", turn);
                response.put("player1", player1);
                response.put("player2", player2);

                /* send update only to players in same room */

                Set<Session> roomSessions = rooms.get(room);

                if (roomSessions != null) {

                    for (Session s : roomSessions) {

                        s.getBasicRemote().sendText(response.toString());

                    }

                }

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

}