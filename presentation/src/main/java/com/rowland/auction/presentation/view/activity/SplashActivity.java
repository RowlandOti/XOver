package com.rowland.auction.presentation.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.rowland.auction.presentation.ApplicationController;
import com.rowland.auction.presentation.R;
import com.rowland.auction.presentation.authfeature.view.activity.AuthActivity;
import com.rowland.auction.presentation.bidfeature.view.activity.BidListActivity;
import com.rowland.auction.presentation.dashboardfeature.view.activity.DashboardActivity;
import com.rowland.auction.presentation.utility.PrefManager;

/**
 * This is the First Activity which can be used for initial checks, inits at app Startup
 */
public class SplashActivity extends ABaseActivity {

    // The class Log identifier
    private static final String LOG_TAG = SplashActivity.class.getSimpleName();

    private final int REQ_AUTH = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        if (!PrefManager.isAuthenticated()) {
            startAuthActivity();
        } else {
            startDashBoardActivity();
        }
    }

    private void startDashBoardActivity() {
        ApplicationController.apiManager.setupEndpoint(PrefManager.getInstanceUrl());
        Intent requestDashBoard = new Intent(SplashActivity.this, BidListActivity.class);
        // requestDashBoard.putExtras(getIntent().getExtras());
        startActivity(requestDashBoard);
    }

    private void startAuthActivity() {
        Intent requestAuthIntent = new Intent(SplashActivity.this, AuthActivity.class);
        // requestAuth.putExtras(getIntent().getExtras());
        startActivityForResult(requestAuthIntent, REQ_AUTH);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // The Auth activity returned
        if (requestCode == REQ_AUTH) {
            // User has successfully created an account
            if (resultCode == RESULT_OK) {
                startDashBoardActivity();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
