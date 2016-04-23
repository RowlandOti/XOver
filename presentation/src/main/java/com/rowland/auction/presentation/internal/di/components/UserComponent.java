package com.rowland.auction.presentation.internal.di.components;

import com.rowland.auction.presentation.internal.di.PerActivity;
import com.rowland.auction.presentation.internal.di.modules.ActivityModule;
import com.rowland.auction.presentation.internal.di.modules.UserModule;
import com.rowland.auction.presentation.userfeature.view.fragment.UserDetailsFragment;
import com.rowland.auction.presentation.userfeature.view.fragment.UserListFragment;

import dagger.Component;

/**
 * A scope {@link PerActivity} component.
 * Injects user specific Fragments.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, UserModule.class})
public interface UserComponent extends ActivityComponent {
  void inject(UserListFragment userListFragment);
  void inject(UserDetailsFragment userDetailsFragment);
}
