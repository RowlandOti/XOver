package com.rowland.auction.presentation.bidfeature.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

import com.rowland.auction.presentation.R;
import com.rowland.auction.presentation.bidfeature.view.fragment.BidListFragment;
import com.rowland.auction.presentation.internal.di.HasComponent;
import com.rowland.auction.presentation.internal.di.components.DaggerBidComponent;
import com.rowland.auction.presentation.internal.di.components.BidComponent;
import com.rowland.auction.presentation.bidfeature.model.BidModel;
import com.rowland.auction.presentation.view.activity.ABaseActivity;

/**
 * Activity that shows a list of Bids.
 */
public class BidListActivity extends ABaseActivity implements HasComponent<BidComponent>,BidListFragment.BidListListener {

  public static Intent getCallingIntent(Context context) {
    return new Intent(context, BidListActivity.class);
  }

  private BidComponent bidComponent;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
    setContentView(R.layout.activity_bid_list);

    this.initializeInjector();
    if (savedInstanceState == null) {
      addFragment(R.id.fragmentContainer, new BidListFragment(),true);
    }
  }

  private void initializeInjector() {
    this.bidComponent = DaggerBidComponent.builder()
        .applicationComponent(getApplicationComponent())
        .activityModule(getActivityModule())
        .build();
  }

  @Override public BidComponent getComponent() {
    return bidComponent;
  }

  @Override public void onBidClicked(BidModel bidModel) {
    this.navigator.navigateToBidDetails(this, bidModel.getBidModelId());
  }
}
