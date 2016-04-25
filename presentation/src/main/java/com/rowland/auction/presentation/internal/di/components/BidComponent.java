package com.rowland.auction.presentation.internal.di.components;

import com.rowland.auction.presentation.internal.di.PerActivity;
import com.rowland.auction.presentation.internal.di.modules.ActivityModule;
import com.rowland.auction.presentation.internal.di.modules.BidModule;
import com.rowland.auction.presentation.bidfeature.view.fragment.BidDetailsFragment;
import com.rowland.auction.presentation.bidfeature.view.fragment.BidListFragment;

import dagger.Component;

/**
 * A scope {@link PerActivity} component.
 * Injects bid specific Fragments.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, BidModule.class})
public interface BidComponent extends ActivityComponent {
  void inject(BidListFragment bidListFragment);
  void inject(BidDetailsFragment bidDetailsFragment);
}
