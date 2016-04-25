package com.rowland.auction.data.dashboardfeature.payload;

import com.google.gson.annotations.SerializedName;

/**
 * User Entity used in the data layer.
 */
public class BidPayload {

    @SerializedName("id")
    private int bidPayloadId;

    @SerializedName("title")
    private String title;

    @SerializedName("description")
    private String description;

    @SerializedName("status")
    private String status;

    public BidPayload() {

    }

    public int getBidPayloadId() {
        return bidPayloadId;
    }

    public void setBidPayloadId(int userId) {
        this.bidPayloadId = bidPayloadId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String password) {
        this.status = status;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("***** User Entity Details *****\n");
        stringBuilder.append("id=" + this.getBidPayloadId() + "\n");
        stringBuilder.append("title=" + this.getTitle() + "\n");
        stringBuilder.append("description=" + this.getDescription() + "\n");
        stringBuilder.append("status=" + this.getStatus() + "\n");
        stringBuilder.append("*******************************");
        return stringBuilder.toString();
    }
}
