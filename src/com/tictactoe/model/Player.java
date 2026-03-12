package com.tictactoe.model;

public class Player {

    private int id;
    private String name;
    private int wins;

    // Default constructor
    public Player() {
    }

    // Constructor with parameters
    public Player(int id, String name, int wins) {
        this.id = id;
        this.name = name;
        this.wins = wins;
    }

    // Getter for id
    public int getId() {
        return id;
    }

    // Setter for id
    public void setId(int id) {
        this.id = id;
    }

    // Getter for name
    public String getName() {
        return name;
    }

    // Setter for name
    public void setName(String name) {
        this.name = name;
    }

    // Getter for wins
    public int getWins() {
        return wins;
    }

    // Setter for wins
    public void setWins(int wins) {
        this.wins = wins;
    }

}