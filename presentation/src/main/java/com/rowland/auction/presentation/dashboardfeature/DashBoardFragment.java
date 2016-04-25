package com.rowland.auction.presentation.dashboardfeature;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rowland.auction.presentation.R;
import com.rowland.auction.presentation.view.fragment.ABaseFragment;

/**
 * A simple {@link ABaseFragment} subclass.
 */
public class DashBoardFragment extends ABaseFragment {

    // Class log identifier
    public final static String LOG_TAG = DashBoardFragment.class.getSimpleName();


    public DashBoardFragment() {
        setRetainInstance(true);
    }

    // Actual method to use to create new fragment instance externally
    public static DashBoardFragment newInstance(@Nullable Bundle args) {
        DashBoardFragment fragmentInstance = new DashBoardFragment();
        if (args != null) {
            fragmentInstance.setArguments(args);
        }
        return fragmentInstance;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dash_board, container, false);
    }

}
