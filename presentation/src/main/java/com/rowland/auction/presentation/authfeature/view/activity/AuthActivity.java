package com.rowland.auction.presentation.authfeature.view.activity;

import android.os.Bundle;

import com.rowland.auction.presentation.R;
import com.rowland.auction.presentation.authfeature.view.fragment.LoginUserFragment;
import com.rowland.auction.presentation.authfeature.view.fragment.RegisterUserFragment;
import com.rowland.auction.presentation.view.activity.ABaseActivity;

public class AuthActivity extends ABaseActivity implements RegisterUserFragment.onRegisterFinishBtnClickListener, LoginUserFragment.onLoginFinishBtnClickListener {

    public static String AUTHEMAIL = "AUTH.EMAIL";
    public static String AUTHUSERNAME = "AUTH.USERNAME";
    public static String AUTHPASSWORD = "AUTH.PASSWORD";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        // Check that the activity is using the layout with the fragment_container id
        if (findViewById(R.id.fragment_container) != null) {
            // However, if we're being restored from a previous state, then we don't need to do
            // anything and should return or else we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            } else {
                // Pass bundle to the fragment
                showLoginFragment(null);
            }
        }
    }

    private void showLoginFragment(Bundle args) {
        LoginUserFragment fragment = LoginUserFragment.newInstance(args);
        replaceFragment(R.id.fragment_container, fragment, false);
    }

    private void showRegisterFragment(Bundle args) {
        LoginUserFragment fragment = LoginUserFragment.newInstance(args);
        replaceFragment(R.id.fragment_container, fragment, false);
    }

    @Override
    public void onRegisterFinishClicked(Bundle args) {
        showLoginFragment(args);
    }

    @Override
    public void onCallLoginClicked(Bundle args) {
        showLoginFragment(args);
    }

    @Override
    public void onLoginFinishClicked(String email, String passWord) {

    }

    @Override
    public void onCallRegisterClicked(Bundle args) {
        showRegisterFragment(args);
    }
}