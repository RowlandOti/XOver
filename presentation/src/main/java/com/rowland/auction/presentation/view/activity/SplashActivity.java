package com.rowland.auction.presentation.view.activity;

import android.content.Intent;
import android.os.Bundle;

import com.rowland.auction.presentation.ApplicationController;
import com.rowland.auction.presentation.R;
import com.rowland.auction.presentation.dashboardfeature.view.DashboardActivity;
import com.rowland.auction.presentation.authfeature.view.activity.AuthActivity;
import com.rowland.auction.presentation.utility.PrefManager;

/**
 * This is the First Activity which can be used for initial checks, inits at app Startup
 */
public class SplashActivity extends ABaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        if (!PrefManager.isAuthenticated()) {
            startActivity(new Intent(SplashActivity.this, AuthActivity.class));
        } else {
            ApplicationController.apiManager.setupEndpoint(PrefManager.getInstanceUrl());
            startActivity(new Intent(SplashActivity.this, DashboardActivity.class));
        }
        finish();
    }
}
