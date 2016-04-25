package com.rowland.auction.presentation.userfeature.presenter;

import android.support.annotation.NonNull;

import com.rowland.auction.domain.exception.DefaultErrorBundle;
import com.rowland.auction.domain.exception.IErrorBundle;
import com.rowland.auction.domain.interactor.DefaultSubscriber;
import com.rowland.auction.domain.interactor.UseCase;
import com.rowland.auction.domain.userfeature.model.User;
import com.rowland.auction.presentation.exception.ErrorMessageFactory;
import com.rowland.auction.presentation.internal.di.PerActivity;
import com.rowland.auction.presentation.userfeature.mapper.UserModelDataMapper;
import com.rowland.auction.presentation.presenter.IPresenter;
import com.rowland.auction.presentation.userfeature.model.UserModel;
import com.rowland.auction.presentation.userfeature.view.IUserDetailsView;
import com.fernandocejas.frodo.annotation.RxLogSubscriber;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * {@link IPresenter} that controls communication between views and models of the presentation
 * layer.
 */
@PerActivity
public class UserDetailsPresenter implements IPresenter {

  private IUserDetailsView viewDetailsView;

  private final UseCase getUserDetailsUseCase;
  private final UserModelDataMapper userModelDataMapper;

  @Inject
  public UserDetailsPresenter(@Named("userDetails") UseCase getUserDetailsUseCase, UserModelDataMapper userModelDataMapper) {
    this.getUserDetailsUseCase = getUserDetailsUseCase;
    this.userModelDataMapper = userModelDataMapper;
  }

  public void setView(@NonNull IUserDetailsView view) {
    this.viewDetailsView = view;
  }

  @Override public void resume() {}

  @Override public void pause() {}

  @Override public void destroy() {
    this.getUserDetailsUseCase.unsubscribe();
    this.viewDetailsView = null;
  }

  /**
   * Initializes the presenter by start retrieving user details.
   */
  public void initialize() {
    this.loadUserDetails();
  }

  /**
   * Loads user details.
   */
  private void loadUserDetails() {
    this.hideViewRetry();
    this.showViewLoading();
    this.getUserDetails();
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

  private void showUserDetailsInView(User user) {
    final UserModel userModel = this.userModelDataMapper.transform(user);
    this.viewDetailsView.renderUser(userModel);
  }

  private void getUserDetails() {
    this.getUserDetailsUseCase.execute(new UserDetailsSubscriber());
  }

  @RxLogSubscriber
  private final class UserDetailsSubscriber extends DefaultSubscriber<User> {

    @Override public void onCompleted() {
      UserDetailsPresenter.this.hideViewLoading();
    }

    @Override public void onError(Throwable e) {
      UserDetailsPresenter.this.hideViewLoading();
      UserDetailsPresenter.this.showErrorMessage(new DefaultErrorBundle((Exception) e));
      UserDetailsPresenter.this.showViewRetry();
    }

    @Override public void onNext(User user) {
      UserDetailsPresenter.this.showUserDetailsInView(user);
    }
  }
}
