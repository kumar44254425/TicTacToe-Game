package com.tictactoe.dao;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.tictactoe.model.Player;
import com.tictactoe.util.DBConnection;

public class LeaderboardDAO {

    public List<Player> getLeaderboard() {

        List<Player> list = new ArrayList<>();

        try {

            Connection con = DBConnection.getConnection();

            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(
                    "SELECT player, wins FROM leaderboard ORDER BY wins DESC");

            while (rs.next()) {

                Player p = new Player();

                p.setName(rs.getString("player"));
                p.setWins(rs.getInt("wins"));

                list.add(p);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}