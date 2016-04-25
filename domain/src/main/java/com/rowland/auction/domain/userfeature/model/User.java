package com.rowland.auction.domain.userfeature.model;

public class User {

    private final int userId;

    public User(int userId) {
        this.userId = userId;
    }

    private String username;
    private String email;
    private String password;

    public int getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("***** User Details *****\n");
        stringBuilder.append("id=" + this.getUserId() + "\n");
        stringBuilder.append("email=" + this.getEmail() + "\n");
        stringBuilder.append("*******************************");
        return stringBuilder.toString();
    }
}
