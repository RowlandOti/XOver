package com.rowland.auction.presentation.bidfeature.view;

import com.rowland.auction.presentation.bidfeature.model.BidModel;
import com.rowland.auction.presentation.view.ILoadDataView;

import java.util.Collection;

/**
 * Interface representing a View in a model view presenter (MVP) pattern.
 * In this case is used as a view representing a list of {@link BidModel}.
 */
public interface IBidListView extends ILoadDataView {
  /**
   * Render a bid list in the UI.
   *
   * @param bidModelCollection The collection of {@link BidModel} that will be shown.
   */
  void renderBidList(Collection<BidModel> bidModelCollection);

  /**
   * View a {@link BidModel} profile/details.
   *
   * @param bidModel The bid that will be shown.
   */
  void viewBid(BidModel bidModel);
}
