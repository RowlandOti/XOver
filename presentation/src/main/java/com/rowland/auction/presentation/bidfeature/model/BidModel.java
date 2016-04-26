package com.rowland.auction.presentation.bidfeature.model;

import com.orm.SugarRecord;

/**
 * Class that represents a bid in the presentation layer.
 */

public class BidModel extends SugarRecord {

  public static final String TABLE_NAME = "bid_table";

  public interface Columns {
    public static final String ID = "bid_id";
    public static final String TITLE = "bid_title";
    public static final String CREATED_AT = "bid_created_at";
    public static final String UPDATED_AT = "bid_updated_at";
    public static final String DESCRIPTION = "bid_description";
    public static final String STATUS = "bid_status";
  }


  int bidModelId;

  String title;
  String description;
  boolean status;

  public BidModel() {

  }

  public BidModel(int bidModelId) {
    this.bidModelId = bidModelId;
  }

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

  public void setStatus(boolean status) {
    this.status = status;
  }

  public boolean getStatus() {
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
