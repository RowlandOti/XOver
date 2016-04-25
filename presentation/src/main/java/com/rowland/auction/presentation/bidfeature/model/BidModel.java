package com.rowland.auction.presentation.bidfeature.model;

/**
 * Class that represents a user in the presentation layer.
 */
public class BidModel {

  private final int bidModelId;

  public BidModel(int bidModelId) {
    this.bidModelId = bidModelId;
  }

  private String title;
  private String description;
  private String status;

  public int getBidModelId() {
    return bidModelId;
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
    stringBuilder.append("id=" + this.getBidModelId() + "\n");
    stringBuilder.append("title=" + this.getTitle() + "\n");
    stringBuilder.append("description=" + this.getDescription() + "\n");
    stringBuilder.append("status=" + this.getStatus() + "\n");
    stringBuilder.append("*******************************");
    return stringBuilder.toString();
  }
}
