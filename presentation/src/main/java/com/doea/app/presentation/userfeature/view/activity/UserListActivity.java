package com.doea.app.presentation.userfeature.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

import com.doea.app.presentation.R;
import com.doea.app.presentation.internal.di.HasComponent;
import com.doea.app.presentation.internal.di.components.DaggerUserComponent;
import com.doea.app.presentation.internal.di.components.UserComponent;
import com.doea.app.presentation.userfeature.model.UserModel;
import com.doea.app.presentation.userfeature.view.fragment.UserListFragment;
import com.doea.app.presentation.view.activity.ABaseActivity;

/**
 * Activity that shows a list of Users.
 */
public class UserListActivity extends ABaseActivity implements HasComponent<UserComponent>,UserListFragment.UserListListener {

  public static Intent getCallingIntent(Context context) {
    return new Intent(context, UserListActivity.class);
  }

  private UserComponent userComponent;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
    setContentView(R.layout.activity_user_list);

    this.initializeInjector();
    if (savedInstanceState == null) {
      addFragment(R.id.fragmentContainer, new UserListFragment());
    }
  }

  private void initializeInjector() {
    this.userComponent = DaggerUserComponent.builder()
        .applicationComponent(getApplicationComponent())
        .activityModule(getActivityModule())
        .build();
  }

  @Override public UserComponent getComponent() {
    return userComponent;
  }

  @Override public void onUserClicked(UserModel userModel) {
    this.navigator.navigateToUserDetails(this, userModel.getUserId());
  }
}
