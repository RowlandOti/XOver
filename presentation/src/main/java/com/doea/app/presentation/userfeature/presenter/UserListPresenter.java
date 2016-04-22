package com.doea.app.presentation.userfeature.presenter;

import android.support.annotation.NonNull;

import com.doea.app.domain.exception.DefaultErrorBundle;
import com.doea.app.domain.exception.IErrorBundle;
import com.doea.app.domain.interactor.DefaultSubscriber;
import com.doea.app.domain.userfeature.interactor.UseCase;
import com.doea.app.domain.userfeature.model.User;
import com.doea.app.presentation.exception.ErrorMessageFactory;
import com.doea.app.presentation.internal.di.PerActivity;
import com.doea.app.presentation.mapper.UserModelDataMapper;
import com.doea.app.presentation.presenter.IPresenter;
import com.doea.app.presentation.userfeature.model.UserModel;
import com.doea.app.presentation.userfeature.view.IUserListView;

import java.util.Collection;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * {@link IPresenter} that controls communication between views and models of the presentation
 * layer.
 */
@PerActivity
public class UserListPresenter implements IPresenter {

  private IUserListView viewListView;

  private final UseCase getUserListUseCase;
  private final UserModelDataMapper userModelDataMapper;

  @Inject
  public UserListPresenter(@Named("userList") UseCase getUserListUserCase, UserModelDataMapper userModelDataMapper) {
    this.getUserListUseCase = getUserListUserCase;
    this.userModelDataMapper = userModelDataMapper;
  }

  public void setView(@NonNull IUserListView view) {
    this.viewListView = view;
  }

  @Override public void resume() {}

  @Override public void pause() {}

  @Override public void destroy() {
    this.getUserListUseCase.unsubscribe();
    this.viewListView = null;
  }

  /**
   * Initializes the presenter by start retrieving the user list.
   */
  public void initialize() {
    this.loadUserList();
  }

  /**
   * Loads all users.
   */
  private void loadUserList() {
    this.hideViewRetry();
    this.showViewLoading();
    this.getUserList();
  }

  public void onUserClicked(UserModel userModel) {
    this.viewListView.viewUser(userModel);
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

  private void showUsersCollectionInView(Collection<User> usersCollection) {
    final Collection<UserModel> userModelsCollection =
        this.userModelDataMapper.transform(usersCollection);
    this.viewListView.renderUserList(userModelsCollection);
  }

  private void getUserList() {
    this.getUserListUseCase.execute(new UserListSubscriber());
  }

  private final class UserListSubscriber extends DefaultSubscriber<List<User>> {

    @Override public void onCompleted() {
      UserListPresenter.this.hideViewLoading();
    }

    @Override public void onError(Throwable e) {
      UserListPresenter.this.hideViewLoading();
      UserListPresenter.this.showErrorMessage(new DefaultErrorBundle((Exception) e));
      UserListPresenter.this.showViewRetry();
    }

    @Override public void onNext(List<User> users) {
      UserListPresenter.this.showUsersCollectionInView(users);
    }
  }
}
