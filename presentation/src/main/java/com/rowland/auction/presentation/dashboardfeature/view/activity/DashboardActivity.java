package com.rowland.auction.presentation.dashboardfeature.view.activity;

import android.os.Bundle;

import com.rowland.auction.presentation.R;
import com.rowland.auction.presentation.dashboardfeature.DashBoardFragment;
import com.rowland.auction.presentation.view.activity.ABaseActivity;

public class DashboardActivity extends ABaseActivity {

    // Class log identifier
    public final static String LOG_TAG = DashboardActivity.class.getSimpleName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        // Check that the activity is using the layout with the fragment_container id
        if (findViewById(R.id.fragment_container) != null) {
            // However, if we're being restored from a previous state, then we don't need to do
            // anything and should return or else we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            } else {
                // Pass bundle to the fragment
                showDashBoardFragment(null);
            }
        }
    }

    private void showDashBoardFragment(Bundle args) {
        DashBoardFragment fragment = DashBoardFragment.newInstance(args);
        replaceFragment(R.id.fragment_container, fragment, false, true);
    }
}


