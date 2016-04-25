package com.rowland.auction.data.userfeature.cache;

import com.google.gson.Gson;
import com.rowland.auction.data.dashboardfeature.payload.BidPayload;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Class user as Serializer/Deserializer for user entities.
 */
@Singleton
public class BidJsonSerializer {

  private final Gson gson = new Gson();

  @Inject
  public BidJsonSerializer() {}

  /**
   * Serialize an object to Json.
   *
   * @param userPayload {@link BidPayload} to serialize.
   */
  public String serialize(BidPayload userPayload) {
    String jsonString = gson.toJson(userPayload, BidPayload.class);
    return jsonString;
  }

  /**
   * Deserialize a json representation of an object.
   *
   * @param jsonString A json string to deserialize.
   * @return {@link BidPayload}
   */
  public BidPayload deserialize(String jsonString) {
    BidPayload userPayload = gson.fromJson(jsonString, BidPayload.class);
    return userPayload;
  }
}
