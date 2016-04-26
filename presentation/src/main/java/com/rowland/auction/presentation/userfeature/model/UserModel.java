package com.rowland.auction.presentation.userfeature.model;



/**
 * Class that represents a user in the presentation layer.
 */

public class UserModel {

    public UserModel(int userId) {
        this.userId = userId;
    }


    private final int userId;
    private String username;
    private String email;


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


    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("***** User Model Details *****\n");
        stringBuilder.append("id=" + this.getUserId() + "\n");
        stringBuilder.append("email=" + this.getEmail() + "\n");
        stringBuilder.append("username=" + this.getUsername() + "\n");
        stringBuilder.append("*******************************");
        return stringBuilder.toString();
    }
}
