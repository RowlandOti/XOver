package com.rowland.auction.data.dashboardfeature.payload.mapper;

import com.rowland.auction.data.dashboardfeature.payload.BidPayload;
import com.rowland.auction.domain.dashboardfeature.model.Bid;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Mapper class used to transform {@link BidPayload} (in the data layer) to {@link Bid} in the
 * domain layer.
 */
@Singleton
public class BidPayloadDataMapper {

    @Inject
    public BidPayloadDataMapper() {
    }

    /**
     * Transform a {@link BidPayload} into an {@link Bid}.
     *
     * @param userPayload Object to be transformed.
     * @return {@link Bid} if valid {@link BidPayload} otherwise null.
     */
    public Bid transform(BidPayload userPayload) {
        Bid bid = null;
        if (userPayload != null) {
            bid = new Bid(userPayload.getBidPayloadId());
            bid.setTitle(userPayload.getTitle());
            bid.setDescription(userPayload.getDescription());
            bid.setStatus(userPayload.getStatus());
        }
        return bid;
    }

    /**
     * Transform a List of {@link BidPayload} into a Collection of {@link Bid}.
     *
     * @param bidPayloadCollection Object Collection to be transformed.
     * @return {@link Bid} if valid {@link BidPayload} otherwise null.
     */
    public List<Bid> transform(Collection<BidPayload> bidPayloadCollection) {
        List<Bid> bidList = new ArrayList<>(20);
        Bid user;
        for (BidPayload userPayload : bidPayloadCollection) {
            user = transform(userPayload);
            if (user != null) {
                bidList.add(user);
            }
        }
        return bidList;
    }
}

