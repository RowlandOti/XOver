package com.doea.app.presentation.userfeature.view;

import com.doea.app.presentation.userfeature.model.UserModel;
import com.doea.app.presentation.view.ILoadDataView;

/**
 * Interface representing a View in a model view presenter (MVP) pattern.
 * In this case is used as a view representing a user profile.
 */
public interface IUserDetailsView extends ILoadDataView {
  /**
   * Render a user in the UI.
   *
   * @param user The {@link UserModel} that will be shown.
   */
  void renderUser(UserModel user);
}
