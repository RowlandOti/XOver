package com.rowland.auction.presentation.bidfeature.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

import com.rowland.auction.presentation.R;
import com.rowland.auction.presentation.bidfeature.view.fragment.BidDetailsFragment;
import com.rowland.auction.presentation.internal.di.HasComponent;
import com.rowland.auction.presentation.internal.di.components.DaggerBidComponent;
import com.rowland.auction.presentation.internal.di.components.BidComponent;
import com.rowland.auction.presentation.internal.di.modules.BidModule;
import com.rowland.auction.presentation.view.activity.ABaseActivity;

/**
 * Activity that shows details of a certain bid.
 */
public class BidDetailsActivity extends ABaseActivity implements HasComponent<BidComponent> {

  private static final String INTENT_EXTRA_PARAM_BID_ID = "com.rowland.auction.INTENT_PARAM_BID_ID";
  private static final String INSTANCE_STATE_PARAM_BID_ID = "com.rowland.auction.STATE_PARAM_BID_ID";

  public static Intent getCallingIntent(Context context, int bidId) {
    Intent callingIntent = new Intent(context, BidDetailsActivity.class);
    callingIntent.putExtra(INTENT_EXTRA_PARAM_BID_ID, bidId);
    return callingIntent;
  }

  private int bidId;
  private BidComponent bidComponent;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
    setContentView(R.layout.activity_bid_details);

    this.initializeActivity(savedInstanceState);
    this.initializeInjector();
  }

  @Override protected void onSaveInstanceState(Bundle outState) {
    if (outState != null) {
      outState.putInt(INSTANCE_STATE_PARAM_BID_ID, this.bidId);
    }
    super.onSaveInstanceState(outState);
  }

  /**
   * Initializes this activity.
   */
  private void initializeActivity(Bundle savedInstanceState) {
    if (savedInstanceState == null) {
      this.bidId = getIntent().getIntExtra(INTENT_EXTRA_PARAM_BID_ID, -1);
      addFragment(R.id.fragmentContainer, new BidDetailsFragment(), true);
    } else {
      this.bidId = savedInstanceState.getInt(INSTANCE_STATE_PARAM_BID_ID);
    }
  }

  private void initializeInjector() {
    this.bidComponent = DaggerBidComponent.builder()
        .applicationComponent(getApplicationComponent())
        .activityModule(getActivityModule())
        .bidModule(new BidModule(this.bidId))
        .build();
  }

  @Override public BidComponent getComponent() {
    return bidComponent;
  }
}
