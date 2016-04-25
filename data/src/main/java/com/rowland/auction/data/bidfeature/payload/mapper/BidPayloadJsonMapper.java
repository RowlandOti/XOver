package com.rowland.auction.data.bidfeature.payload.mapper;

import com.rowland.auction.data.bidfeature.payload.BidPayload;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;
import javax.inject.Inject;

/**
 * Class used to transform from Strings representing json to valid objects.
 */
public class BidPayloadJsonMapper {

    private final Gson gson;

    @Inject
    public BidPayloadJsonMapper() {
        this.gson = new Gson();
    }

    /**
     * Transform from valid json string to {@link BidPayload}.
     *
     * @param userJsonResponse A json representing a user profile.
     * @return {@link BidPayload}.
     * @throws com.google.gson.JsonSyntaxException if the json string is not a valid json structure.
     */
    public BidPayload transformUserEntity(String userJsonResponse) throws JsonSyntaxException {
        try {
            Type userEntityType = new TypeToken<BidPayload>() {}.getType();
            BidPayload userPayload = this.gson.fromJson(userJsonResponse, userEntityType);

            return userPayload;
        } catch (JsonSyntaxException jsonException) {
            throw jsonException;
        }
    }

    /**
     * Transform from valid json string to List of {@link BidPayload}.
     *
     * @param userListJsonResponse A json representing a collection of users.
     * @return List of {@link BidPayload}.
     * @throws com.google.gson.JsonSyntaxException if the json string is not a valid json structure.
     */
    public List<BidPayload> transformUserEntityCollection(String userListJsonResponse)
            throws JsonSyntaxException {

        List<BidPayload> userPayloadCollection;
        try {
            Type listOfUserEntityType = new TypeToken<List<BidPayload>>() {}.getType();
            userPayloadCollection = this.gson.fromJson(userListJsonResponse, listOfUserEntityType);

            return userPayloadCollection;
        } catch (JsonSyntaxException jsonException) {
            throw jsonException;
        }
    }
}
