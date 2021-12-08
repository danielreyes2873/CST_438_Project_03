package com.example.cst_438_project_03;

public class Quiz {
    private String name;
    private String description;
    private String username;

    public Quiz(String name, String description, String username) {
        this.name = name;
        this.description = description;
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getUserID() {
        return username;
    }
}
