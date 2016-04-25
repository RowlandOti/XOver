package com.rowland.auction.presentation.view.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.rowland.auction.presentation.ApplicationController;
import com.rowland.auction.presentation.R;
import com.rowland.auction.presentation.internal.di.components.ApplicationComponent;
import com.rowland.auction.presentation.internal.di.modules.ActivityModule;
import com.rowland.auction.presentation.navigation.Navigator;

import javax.inject.Inject;

/**
 * Base {@link Activity} class for every Activity in this application.
 */
public abstract class ABaseActivity extends AppCompatActivity {

    @Inject
    protected
    Navigator navigator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getApplicationComponent().inject(this);
    }

    /**
     * Adds a {@link Fragment} to this activity's layout.
     *
     * @param containerViewId The container view to where add the fragment.
     * @param fragment        The fragment to be added.
     * @param isAnimateTransition  The boolean on whether to animate fragment transition.
     */
    protected void addFragment(int containerViewId, Fragment fragment, boolean isAnimateTransition ) {
        FragmentManager fm = this.getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if(isAnimateTransition) {
            animateFragmentTransition(ft);
        }
        ft.add(containerViewId, fragment);
        ft.commit();
    }

    /**
     * Adds a {@link Fragment} to this activity's layout.
     *
     * @param containerViewId The container view to where add the fragment.
     * @param fragment        The fragment to be added.
     * @param isAddToBackStack  The boolean on whether to add to backstack.
     * @param isAnimateTransition  The boolean on whether to animate fragment transition.
     */
    protected void replaceFragment(int containerViewId, Fragment fragment, boolean isAddToBackStack, boolean isAnimateTransition) {
        // invalidateOptionsMenu();
        String backStateName = fragment.getClass().getName();
        FragmentManager fm = this.getSupportFragmentManager();
        boolean fragmentPopped = fm.popBackStackImmediate(backStateName, 0);

        if (!fragmentPopped && getSupportFragmentManager().findFragmentByTag(backStateName) == null) {
            FragmentTransaction ft = fm.beginTransaction();
            if(isAnimateTransition) {
                animateFragmentTransition(ft);
            }
            ft.replace(containerViewId, fragment, backStateName);
            if (isAddToBackStack) {
                ft.addToBackStack(backStateName);
            }
            ft.commit();
        }
    }

    /**
     * Adds a {@link FragmentTransaction} to this activity's layout.
     *
     * @param ft The FragmentTransaction
     */
    private void animateFragmentTransition(FragmentTransaction ft) {
        ft.setCustomAnimations(R.anim.slide_in_to_left,
                R.anim.slide_out_to_left, R.anim.slide_in_to_right,
                R.anim.slide_out_to_right);
    }

    /**
     * Adds a {@link Fragment} to this activity's layout.
     *
     * @param isToolbarTransparent whether to set statusbar transparent.
     */
    public void setStatusbarTransparent(boolean isToolbarTransparent) {
        if (isToolbarTransparent) {
            // Check for minimum api as Lollipop
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                // Set up  the systemUi flags
                getWindow().getDecorView().setSystemUiVisibility(
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
                // Set the status bar tobe transparent
                getWindow().setStatusBarColor(Color.TRANSPARENT);
            }
        }
    }

    /**
     * Get the Main Application component for dependency injection.
     *
     * @return {@link ApplicationComponent}
     */
    protected ApplicationComponent getApplicationComponent() {
        return ((ApplicationController) getApplication()).getAppComponent();
    }

    /**
     * Get an Activity module for dependency injection.
     *
     * @return {@link ActivityModule}
     */
    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }
}
