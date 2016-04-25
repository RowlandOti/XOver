package com.rowland.auction.presentation.bidfeature.view.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.rowland.auction.presentation.R;
import com.rowland.auction.presentation.bidfeature.view.IBidListView;
import com.rowland.auction.presentation.bidfeature.view.adapter.BidAdapter;
import com.rowland.auction.presentation.bidfeature.view.layoutmanager.BidLayoutManager;
import com.rowland.auction.presentation.internal.di.components.BidComponent;
import com.rowland.auction.presentation.bidfeature.model.BidModel;
import com.rowland.auction.presentation.bidfeature.presenter.BidListPresenter;
import com.rowland.auction.presentation.view.fragment.ABaseFragment;

import java.util.Collection;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Fragment that shows a list of Bids.
 */
public class BidListFragment extends ABaseFragment implements IBidListView {

    /**
     * Interface for listening bid list events.
     */
    public interface BidListListener {
        void onBidClicked(final BidModel bidModel);
    }

    @Inject
    BidListPresenter bidListPresenter;
    @Inject
    BidAdapter bidsAdapter;

    @Bind(R.id.rv_bids)
    RecyclerView rv_bids;
    @Bind(R.id.rl_progress)
    RelativeLayout rl_progress;
    @Bind(R.id.rl_retry)
    RelativeLayout rl_retry;
    @Bind(R.id.bt_retry)
    Button bt_retry;

    private BidListListener bidListListener;

    public BidListFragment() {
        setRetainInstance(true);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof BidListListener) {
            this.bidListListener = (BidListListener) activity;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getComponent(BidComponent.class).inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View fragmentView = inflater.inflate(R.layout.fragment_bid_list, container, false);
        ButterKnife.bind(this, fragmentView);
        setupRecyclerView();
        return fragmentView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.bidListPresenter.setView(this);
        if (savedInstanceState == null) {
            this.loadBidList();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        this.bidListPresenter.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        this.bidListPresenter.pause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        rv_bids.setAdapter(null);
        ButterKnife.unbind(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.bidListPresenter.destroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.bidListListener = null;
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
    public void renderBidList(Collection<BidModel> bidModelCollection) {
        if (bidModelCollection != null) {
            this.bidsAdapter.setBidsCollection(bidModelCollection);
        }
    }

    @Override
    public void viewBid(BidModel bidModel) {
        if (this.bidListListener != null) {
            this.bidListListener.onBidClicked(bidModel);
        }
    }

    @Override
    public void showError(String message) {
        this.showToastMessage(message);
    }

    @Override
    public Context context() {
        return this.getActivity().getApplicationContext();
    }

    private void setupRecyclerView() {
        this.bidsAdapter.setOnItemClickListener(onItemClickListener);
        this.rv_bids.setLayoutManager(new BidLayoutManager(context()));
        this.rv_bids.setAdapter(bidsAdapter);
    }

    /**
     * Loads all bids.
     */
    private void loadBidList() {
        this.bidListPresenter.initialize();
    }

    @OnClick(R.id.bt_retry)
    void onButtonRetryClick() {
        BidListFragment.this.loadBidList();
    }

    private BidAdapter.OnItemClickListener onItemClickListener =
            new BidAdapter.OnItemClickListener() {
                @Override
                public void onBidItemClicked(BidModel bidModel) {
                    if (BidListFragment.this.bidListPresenter != null && bidModel != null) {
                        BidListFragment.this.bidListPresenter.onBidClicked(bidModel);
                    }
                }
            };
}
