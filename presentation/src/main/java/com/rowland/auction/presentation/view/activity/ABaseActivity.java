package com.rowland.auction.presentation.view.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.rowland.auction.presentation.ApplicationController;
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
     */
    protected void addFragment(int containerViewId, Fragment fragment) {
        FragmentManager fm = this.getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.add(containerViewId, fragment);
        transaction.commit();
    }

    /**
     * Adds a {@link Fragment} to this activity's layout.
     *
     * @param containerViewId The container view to where add the fragment.
     * @param fragment        The fragment to be added.
     * @param addToBackStack  The boolean on whether to add to backstack.
     */
    protected void replaceFragment(int containerViewId, Fragment fragment, boolean addToBackStack) {
        // invalidateOptionsMenu();
        String backStateName = fragment.getClass().getName();
        FragmentManager fm = this.getSupportFragmentManager();
        boolean fragmentPopped = fm.popBackStackImmediate(backStateName, 0);

        if (!fragmentPopped && getSupportFragmentManager().findFragmentByTag(backStateName) == null) {
            FragmentTransaction transaction = fm.beginTransaction();
            transaction.replace(containerViewId, fragment, backStateName);
            if (addToBackStack) {
                transaction.addToBackStack(backStateName);
            }
            transaction.commit();
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
