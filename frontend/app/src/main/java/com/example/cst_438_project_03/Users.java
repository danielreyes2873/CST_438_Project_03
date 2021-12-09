package com.example.cst_438_project_03;

public class Users {
    private String username;

    private String password;

    private String firstname;

    private String lastname;

    public Users(String username, String password, String firstname, String lastname) {
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstname(){return firstname;}

    public String getLastname() {return lastname;}
}
