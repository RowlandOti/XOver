package com.rowland.auction.data.userfeature.payload.mapper;

import com.rowland.auction.data.userfeature.payload.BidPayload;
import com.rowland.auction.domain.userfeature.model.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Mapper class used to transform {@link BidPayload} (in the data layer) to {@link User} in the
 * domain layer.
 */
@Singleton
public class UserPayloadDataMapper {

    @Inject
    public UserPayloadDataMapper() {}

    /**
     * Transform a {@link BidPayload} into an {@link User}.
     *
     * @param userPayload Object to be transformed.
     * @return {@link User} if valid {@link BidPayload} otherwise null.
     */
    public User transform(BidPayload userPayload) {
        User user = null;
        if (userPayload != null) {
            user = new User(userPayload.getUserId());
            user.setUsername(userPayload.getUsername());
            user.setEmail(userPayload.getEmail());
        }
        return user;
    }

    /**
     * Transform a List of {@link BidPayload} into a Collection of {@link User}.
     *
     * @param userPayloadCollection Object Collection to be transformed.
     * @return {@link User} if valid {@link BidPayload} otherwise null.
     */
    public List<User> transform(Collection<BidPayload> userPayloadCollection) {
        List<User> userList = new ArrayList<>(20);
        User user;
        for (BidPayload userPayload : userPayloadCollection) {
            user = transform(userPayload);
            if (user != null) {
                userList.add(user);
            }
        }
        return userList;
    }
}

