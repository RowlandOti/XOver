package com.doea.app.presentation.userfeature.view;

import com.doea.app.presentation.userfeature.model.UserModel;
import com.doea.app.presentation.view.ILoadDataView;

import java.util.Collection;

/**
 * Interface representing a View in a model view presenter (MVP) pattern.
 * In this case is used as a view representing a list of {@link UserModel}.
 */
public interface IUserListView extends ILoadDataView {
  /**
   * Render a user list in the UI.
   *
   * @param userModelCollection The collection of {@link UserModel} that will be shown.
   */
  void renderUserList(Collection<UserModel> userModelCollection);

  /**
   * View a {@link UserModel} profile/details.
   *
   * @param userModel The user that will be shown.
   */
  void viewUser(UserModel userModel);
}
