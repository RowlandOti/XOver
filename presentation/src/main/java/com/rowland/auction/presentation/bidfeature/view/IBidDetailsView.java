package com.rowland.auction.presentation.bidfeature.view;

import com.rowland.auction.presentation.bidfeature.model.BidModel;
import com.rowland.auction.presentation.view.ILoadDataView;

/**
 * Interface representing a View in a model view presenter (MVP) pattern.
 * In this case is used as a view representing a bid profile.
 */
public interface IBidDetailsView extends ILoadDataView {
  /**
   * Render a bid in the UI.
   *
   * @param bid The {@link BidModel} that will be shown.
   */
  void renderBid(BidModel bid);
}
