package com.rowland.auction.presentation.bidfeature.presenter;

import android.support.annotation.NonNull;

import com.rowland.auction.domain.exception.DefaultErrorBundle;
import com.rowland.auction.domain.exception.IErrorBundle;
import com.rowland.auction.domain.interactor.DefaultSubscriber;
import com.rowland.auction.domain.interactor.UseCase;
import com.rowland.auction.domain.bidfeature.model.Bid;
import com.rowland.auction.presentation.exception.ErrorMessageFactory;
import com.rowland.auction.presentation.internal.di.PerActivity;
import com.rowland.auction.presentation.bidfeature.mapper.BidModelDataMapper;
import com.rowland.auction.presentation.presenter.IPresenter;
import com.rowland.auction.presentation.bidfeature.model.BidModel;
import com.rowland.auction.presentation.bidfeature.view.IBidListView;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * {@link IPresenter} that controls communication between views and models of the presentation
 * layer.
 */
@PerActivity
public class BidListPresenter implements IPresenter {

  private IBidListView viewListView;

  private final UseCase getBidListUseCase;
  private final BidModelDataMapper bidModelDataMapper;

  @Inject
  public BidListPresenter(@Named("bidList") UseCase getBidListBidCase, BidModelDataMapper bidModelDataMapper) {
    this.getBidListUseCase = getBidListBidCase;
    this.bidModelDataMapper = bidModelDataMapper;
  }

  public void setView(@NonNull IBidListView view) {
    this.viewListView = view;
  }

  @Override public void resume() {}

  @Override public void pause() {}

  @Override public void destroy() {
    this.getBidListUseCase.unsubscribe();
    this.viewListView = null;
  }

  /**
   * Initializes the presenter by start retrieving the bid list.
   */
  public void initialize() {
    this.loadBidList();
  }

  /**
   * Loads all bids.
   */
  private void loadBidList() {
    this.hideViewRetry();
    this.showViewLoading();
    this.getBidList();
  }

  public void onBidClicked(BidModel bidModel) {
    this.viewListView.viewBid(bidModel);
  }

  private void showViewLoading() {
    this.viewListView.showLoading();
  }

  private void hideViewLoading() {
    this.viewListView.hideLoading();
  }

  private void showViewRetry() {
    this.viewListView.showRetry();
  }

  private void hideViewRetry() {
    this.viewListView.hideRetry();
  }

  private void showErrorMessage(IErrorBundle errorBundle) {
    String errorMessage = ErrorMessageFactory.create(this.viewListView.context(),
        errorBundle.getException());
    this.viewListView.showError(errorMessage);
  }

  private void showBidsCollectionInView(Collection<Bid> bidsCollection) {
    final Collection<BidModel> bidModelsCollection =
        this.bidModelDataMapper.transform(bidsCollection);
    this.viewListView.renderBidList(bidModelsCollection);
  }

  private void getBidList() {
    this.getBidListUseCase.execute(new BidListSubscriber());
  }

  private final class BidListSubscriber extends DefaultSubscriber<List<Bid>> {

    @Override public void onCompleted() {
      BidListPresenter.this.hideViewLoading();
    }

    @Override public void onError(Throwable e) {
      BidListPresenter.this.hideViewLoading();
      BidListPresenter.this.showErrorMessage(new DefaultErrorBundle((Exception) e));
      BidListPresenter.this.showViewRetry();
    }

    @Override public void onNext(List<Bid> bids) {
      BidListPresenter.this.showBidsCollectionInView(bids);
    }
  }
}
