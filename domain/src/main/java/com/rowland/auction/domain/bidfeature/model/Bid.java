package com.rowland.auction.domain.bidfeature.model;

public class Bid {

    private final int bidId;

    public Bid(int bidId) {
        this.bidId = bidId;
    }

    private String title;
    private String description;
    private String status;

    public int getBidId() {
        return bidId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("***** Bid Details *****\n");
        stringBuilder.append("id=" + this.getBidId() + "\n");
        stringBuilder.append("title=" + this.getTitle() + "\n");
        stringBuilder.append("description=" + this.getDescription() + "\n");
        stringBuilder.append("status=" + this.getStatus() + "\n");
        stringBuilder.append("*******************************");
        return stringBuilder.toString();
    }
}
