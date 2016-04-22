package com.doea.app.presentation.view.activity;

import android.content.Intent;
import android.os.Bundle;

import com.doea.app.presentation.ApplicationController;
import com.doea.app.presentation.R;
import com.doea.app.presentation.dashboardfeature.view.DashboardActivity;
import com.doea.app.presentation.authfeature.view.activity.LoginActivity;
import com.doea.app.presentation.utility.PrefManager;

/**
 * This is the First Activity which can be used for initial checks, inits at app Startup
 */
public class SplashActivity extends ABaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        if (!PrefManager.isAuthenticated()) {
            startActivity(new Intent(SplashActivity.this, LoginActivity.class));
        } else {
            ApplicationController.apiManager.setupEndpoint(PrefManager.getInstanceUrl());
            startActivity(new Intent(SplashActivity.this, DashboardActivity.class));
        }
        finish();
    }
}
