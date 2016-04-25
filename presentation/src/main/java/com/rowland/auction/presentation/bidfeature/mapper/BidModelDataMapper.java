package com.rowland.auction.presentation.bidfeature.mapper;

import com.rowland.auction.domain.bidfeature.model.Bid;
import com.rowland.auction.presentation.internal.di.PerActivity;
import com.rowland.auction.presentation.bidfeature.model.BidModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javax.inject.Inject;

/**
 * Mapper class used to transform {@link Bid} (in the domain layer) to {@link BidModel} in the
 * presentation layer.
 */
@PerActivity
public class BidModelDataMapper {

  @Inject
  public BidModelDataMapper() {}

  /**
   * Transform a {@link Bid} into an {@link BidModel}.
   *
   * @param bid Object to be transformed.
   * @return {@link BidModel}.
   */
  public BidModel transform(Bid bid) {
    if (bid == null) {
      throw new IllegalArgumentException("Cannot transform a null value");
    }
    BidModel bidModel = new BidModel(bid.getBidId());
    bidModel.setTitle(bid.getTitle());
    bidModel.setDescription(bid.getDescription());
    bidModel.setStatus(bid.getStatus());

    return bidModel;
  }

  /**
   * Transform a Collection of {@link Bid} into a Collection of {@link BidModel}.
   *
   * @param bidsCollection Objects to be transformed.
   * @return List of {@link BidModel}.
   */
  public Collection<BidModel> transform(Collection<Bid> bidsCollection) {
    Collection<BidModel> bidModelsCollection;

    if (bidsCollection != null && !bidsCollection.isEmpty()) {
      bidModelsCollection = new ArrayList<>();
      for (Bid bid : bidsCollection) {
        bidModelsCollection.add(transform(bid));
      }
    } else {
      bidModelsCollection = Collections.emptyList();
    }

    return bidModelsCollection;
  }
}
