package com.example;

public class User {
    private String username;
    private String password;

    public User(String username,String password){
        this.password=password;
        this.username=username;
    }
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
