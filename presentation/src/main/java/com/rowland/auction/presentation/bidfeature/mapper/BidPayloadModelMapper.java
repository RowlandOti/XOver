package com.rowland.auction.presentation.bidfeature.mapper;

import com.rowland.auction.data.bidfeature.payload.BidPayload;
import com.rowland.auction.domain.bidfeature.model.Bid;
import com.rowland.auction.presentation.bidfeature.model.BidModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Mapper class used to transform {@link BidPayload} (in the data layer) to {@link BidModel} in the
 * presentation layer.
 */
@Singleton
public class BidPayloadModelMapper {

    // Class log identifier
    public final static String LOG_TAG = BidPayloadModelMapper.class.getSimpleName();

    @Inject
    public BidPayloadModelMapper() {
    }

    /**
     * Transform a {@link BidPayload} into an {@link Bid}.
     *
     * @param bidPayload Object to be transformed.
     * @return {@link BidModel} if valid {@link BidPayload} otherwise null.
     */
    public BidModel transformPayloadToModel(BidPayload bidPayload) {
        BidModel bidModel = null;
        if (bidPayload != null) {
            bidModel = new BidModel(bidPayload.getBidPayloadId());
            bidModel.setTitle(bidPayload.getTitle());
            bidModel.setDescription(bidPayload.getDescription());
            bidModel.setStatus(bidPayload.getStatus());
        }
        return bidModel;
    }

    /**
     * Transform a List of {@link BidPayload} into a Collection of {@link Bid}.
     *
     * @param bidPayloadCollection Object Collection to be transformed.
     * @return {@link BidModel} if valid {@link BidPayload} otherwise null.
     */
    public List<BidModel> transformPayloadToModel(Collection<BidPayload> bidPayloadCollection) {
        List<BidModel> bidModelList = new ArrayList<>(20);
        BidModel bidModel;
        for (BidPayload bidPayload : bidPayloadCollection) {
            bidModel = transformPayloadToModel(bidPayload);
            if (bidModel != null) {
                bidModelList.add(bidModel);
            }
        }
        return bidModelList;
    }

    /**
     * Transform a {@link BidPayload} into an {@link Bid}.
     *
     * @param bidModel Object to be transformed.
     * @return {@link BidModel} if valid {@link BidPayload} otherwise null.
     */
    public BidPayload transformModelToPayload(BidModel bidModel) {
        BidPayload bidPayload = null;
        if (bidModel != null) {
            bidPayload = new BidPayload();
            bidPayload.setBidPayloadId(bidModel.getBidModelId());
            bidPayload.setTitle(bidModel.getTitle());
            bidPayload.setDescription(bidModel.getDescription());
            bidPayload.setStatus(bidModel.getStatus());
        }
        return bidPayload;
    }

    /**
     * Transform a List of {@link BidPayload} into a Collection of {@link Bid}.
     *
     * @param bidModelCollection Object Collection to be transformed.
     * @return {@link BidModel} if valid {@link BidPayload} otherwise null.
     */
    public List<BidPayload> transformModelToPayload(Collection<BidModel> bidModelCollection) {
        List<BidPayload> bidPayloadList = new ArrayList<>(20);

        for (BidModel bidModel : bidModelCollection) {
            BidPayload bidPayload = transformModelToPayload(bidModel);
            if (bidPayload != null) {
                bidPayloadList.add(bidPayload);
            }
        }
        return bidPayloadList;
    }
}

