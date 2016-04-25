package com.rowland.auction.presentation.bidfeature.presenter;

import android.support.annotation.NonNull;

import com.fernandocejas.frodo.annotation.RxLogSubscriber;
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
import com.rowland.auction.presentation.bidfeature.view.IBidDetailsView;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * {@link IPresenter} that controls communication between views and models of the presentation
 * layer.
 */
@PerActivity
public class BidDetailsPresenter implements IPresenter {

  private IBidDetailsView viewDetailsView;

  private final UseCase getBidDetailsUseCase;
  private final BidModelDataMapper bidModelDataMapper;

  @Inject
  public BidDetailsPresenter(@Named("bidDetails") UseCase getBidDetailsUseCase, BidModelDataMapper bidModelDataMapper) {
    this.getBidDetailsUseCase = getBidDetailsUseCase;
    this.bidModelDataMapper = bidModelDataMapper;
  }

  public void setView(@NonNull IBidDetailsView view) {
    this.viewDetailsView = view;
  }

  @Override public void resume() {}

  @Override public void pause() {}

  @Override public void destroy() {
    this.getBidDetailsUseCase.unsubscribe();
    this.viewDetailsView = null;
  }

  /**
   * Initializes the presenter by start retrieving bid details.
   */
  public void initialize() {
    this.loadBidDetails();
  }

  /**
   * Loads bid details.
   */
  private void loadBidDetails() {
    this.hideViewRetry();
    this.showViewLoading();
    this.getBidDetails();
  }

  private void showViewLoading() {
    this.viewDetailsView.showLoading();
  }

  private void hideViewLoading() {
    this.viewDetailsView.hideLoading();
  }

  private void showViewRetry() {
    this.viewDetailsView.showRetry();
  }

  private void hideViewRetry() {
    this.viewDetailsView.hideRetry();
  }

  private void showErrorMessage(IErrorBundle errorBundle) {
    String errorMessage = ErrorMessageFactory.create(this.viewDetailsView.context(), errorBundle.getException());
    this.viewDetailsView.showError(errorMessage);
  }

  private void showBidDetailsInView(Bid bid) {
    final BidModel bidModel = this.bidModelDataMapper.transform(bid);
    this.viewDetailsView.renderBid(bidModel);
  }

  private void getBidDetails() {
    this.getBidDetailsUseCase.execute(new BidDetailsSubscriber());
  }

  @RxLogSubscriber
  private final class BidDetailsSubscriber extends DefaultSubscriber<Bid> {

    @Override public void onCompleted() {
      BidDetailsPresenter.this.hideViewLoading();
    }

    @Override public void onError(Throwable e) {
      BidDetailsPresenter.this.hideViewLoading();
      BidDetailsPresenter.this.showErrorMessage(new DefaultErrorBundle((Exception) e));
      BidDetailsPresenter.this.showViewRetry();
    }

    @Override public void onNext(Bid bid) {
      BidDetailsPresenter.this.showBidDetailsInView(bid);
    }
  }
}
