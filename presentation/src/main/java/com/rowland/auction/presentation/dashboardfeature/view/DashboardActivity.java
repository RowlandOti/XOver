package com.rowland.auction.presentation.dashboardfeature.view;

import android.os.Bundle;

import com.rowland.auction.presentation.R;
import com.rowland.auction.presentation.view.activity.ABaseActivity;

public class DashboardActivity extends ABaseActivity {

    // Class log identifier
    public final static String LOG_TAG = DashboardActivity.class.getSimpleName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
    }
}


