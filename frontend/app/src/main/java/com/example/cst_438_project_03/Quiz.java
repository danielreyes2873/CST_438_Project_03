package com.example.cst_438_project_03;

public class Quiz {
    private String name;
    private String description;
    private int userID;

    public Quiz(String name, String description, int userID) {
        this.name = name;
        this.description = description;
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getUserID() {
        return userID;
    }
}
