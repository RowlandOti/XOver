package com.rowland.auction.presentation.bidfeature.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rowland.auction.presentation.R;
import com.rowland.auction.presentation.bidfeature.model.BidModel;
import com.rowland.auction.presentation.bidfeature.presenter.BidDetailsPresenter;
import com.rowland.auction.presentation.bidfeature.view.IBidDetailsView;
import com.rowland.auction.presentation.internal.di.components.BidComponent;
import com.rowland.auction.presentation.view.fragment.ABaseFragment;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Fragment that shows details of a certain bid.
 */
public class BidDetailsFragment extends ABaseFragment implements IBidDetailsView {

    @Inject
    BidDetailsPresenter bidDetailsPresenter;

    @Bind(R.id.iv_cover)
    ImageView iv_cover;
    @Bind(R.id.tv_title)
    TextView tv_title;
    @Bind(R.id.tv_description)
    TextView tv_description;
    @Bind(R.id.tv_status)
    TextView tv_status;
    @Bind(R.id.rl_progress)
    RelativeLayout rl_progress;
    @Bind(R.id.rl_retry)
    RelativeLayout rl_retry;
    @Bind(R.id.bt_retry)
    Button bt_retry;

    public BidDetailsFragment() {
        setRetainInstance(true);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getComponent(BidComponent.class).inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View fragmentView = inflater.inflate(R.layout.fragment_bid_details, container, false);
        ButterKnife.bind(this, fragmentView);
        return fragmentView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.bidDetailsPresenter.setView(this);
        if (savedInstanceState == null) {
            this.loadBidDetails();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        this.bidDetailsPresenter.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        this.bidDetailsPresenter.pause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.bidDetailsPresenter.destroy();
    }

    @Override
    public void renderBid(BidModel bid) {
        if (bid != null) {
            //this.iv_cover.setImageUrl(bid.getCoverUrl());
            this.tv_title.setText(bid.getTitle());
            this.tv_description.setText(bid.getDescription());
            this.tv_status.setText(bid.getStatus());
        }
    }

    @Override
    public void showLoading() {
        this.rl_progress.setVisibility(View.VISIBLE);
        this.getActivity().setProgressBarIndeterminateVisibility(true);
    }

    @Override
    public void hideLoading() {
        this.rl_progress.setVisibility(View.GONE);
        this.getActivity().setProgressBarIndeterminateVisibility(false);
    }

    @Override
    public void showRetry() {
        this.rl_retry.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideRetry() {
        this.rl_retry.setVisibility(View.GONE);
    }

    @Override
    public void showError(String message) {
        this.showToastMessage(message);
    }

    @Override
    public Context context() {
        return getActivity().getApplicationContext();
    }

    /**
     * Loads all bids.
     */
    private void loadBidDetails() {
        if (this.bidDetailsPresenter != null) {
            this.bidDetailsPresenter.initialize();
        }
    }

    @OnClick(R.id.bt_retry)
    void onButtonRetryClick() {
        BidDetailsFragment.this.loadBidDetails();
    }
}
