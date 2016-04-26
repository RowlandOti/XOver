package com.rowland.auction.data.bidfeature.repository.datasource;

import com.rowland.auction.data.bidfeature.payload.BidPayload;

import java.util.List;
import rx.Observable;

/**
 * Interface that represents a data store from where data is retrieved.
 */
public interface IBidDataStore {
  /**
   * Get an {@link Observable} which will emit a List of {@link BidPayload}.
   */
  Observable<List<BidPayload>> bidPayloadList();

  /**
   * Get an {@link Observable} which will emit a {@link BidPayload} by its id.
   *
   * @param bidId The id to retrieve bid data.
   */
  Observable<BidPayload> bidPayloadDetails(final int bidId);
}
