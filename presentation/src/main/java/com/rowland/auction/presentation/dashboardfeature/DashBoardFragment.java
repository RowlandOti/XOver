package com.rowland.auction.presentation.dashboardfeature;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rowland.auction.presentation.R;
import com.rowland.auction.presentation.view.fragment.ABaseFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link ABaseFragment} subclass.
 */
public class DashBoardFragment extends ABaseFragment {

    // Class log identifier
    public final static String LOG_TAG = DashBoardFragment.class.getSimpleName();

    @Bind(R.id.rv_dashboard)
    RecyclerView rvDashboard;
    @Bind(R.id.rl_progress)
    RelativeLayout rlProgress;
    @Bind(R.id.rl_retry)
    RelativeLayout rlRetry;
    @Bind(R.id.bt_retry)
    Button btRetry;

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
        super.onCreateView(inflater, container, savedInstanceState);
        final View fragmentView = inflater.inflate(R.layout.fragment_dash_board, container, false);
        ButterKnife.bind(this, fragmentView);
        return fragmentView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
