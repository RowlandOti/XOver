package com.rowland.auction.data.dashboardfeature.repository.datasource;

import com.rowland.auction.data.dashboardfeature.payload.BidPayload;

import java.util.List;
import rx.Observable;

/**
 * Interface that represents a data store from where data is retrieved.
 */
public interface IBidDataStore {
  /**
   * Get an {@link Observable} which will emit a List of {@link BidPayload}.
   */
  Observable<List<BidPayload>> userEntityList();

  /**
   * Get an {@link Observable} which will emit a {@link BidPayload} by its id.
   *
   * @param userId The id to retrieve user data.
   */
  Observable<BidPayload> userEntityDetails(final int userId);
}
