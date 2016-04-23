package com.rowland.auction.presentation.authfeature.view.activity;

import android.os.Bundle;

import com.rowland.auction.presentation.R;
import com.rowland.auction.presentation.view.activity.ABaseActivity;

public class AuthActivity extends ABaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
    }
}