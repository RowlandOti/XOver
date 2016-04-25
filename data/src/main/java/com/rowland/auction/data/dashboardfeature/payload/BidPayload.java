package com.rowland.auction.data.dashboardfeature.payload;

import com.google.gson.annotations.SerializedName;

/**
 * User Entity used in the data layer.
 */
public class BidPayload {

    @SerializedName("id")
    private int userId;

    @SerializedName("username")
    private String username;

    @SerializedName("email")
    private String email;

    @SerializedName("password")
    private String password;

    public BidPayload() {

    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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
        stringBuilder.append("***** User Entity Details *****\n");
        stringBuilder.append("id=" + this.getUserId() + "\n");
        stringBuilder.append("username=" + this.getUsername() + "\n");
        stringBuilder.append("email=" + this.getEmail() + "\n");
        stringBuilder.append("*******************************");
        return stringBuilder.toString();
    }
}
