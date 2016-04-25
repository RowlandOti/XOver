package com.rowland.auction.presentation.bidfeature.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rowland.auction.presentation.R;
import com.rowland.auction.presentation.bidfeature.model.BidModel;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Adaptar that manages a collection of {@link BidModel}.
 */
public class BidAdapter extends RecyclerView.Adapter<BidAdapter.BidViewHolder> {

  public interface OnItemClickListener {
    void onBidItemClicked(BidModel bidModel);
  }

  private List<BidModel> bidsCollection;
  private final LayoutInflater layoutInflater;

  private OnItemClickListener onItemClickListener;

  @Inject
  public BidAdapter(Context context) {
    this.layoutInflater =
        (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    this.bidsCollection = Collections.emptyList();
  }

  @Override public int getItemCount() {
    return (this.bidsCollection != null) ? this.bidsCollection.size() : 0;
  }

  @Override public BidViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    final View view = this.layoutInflater.inflate(R.layout.row_bid, parent, false);
    return new BidViewHolder(view);
  }

  @Override public void onBindViewHolder(BidViewHolder holder, final int position) {
    final BidModel bidModel = this.bidsCollection.get(position);
    holder.textViewTitle.setText(bidModel.getTitle());
    holder.itemView.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        if (BidAdapter.this.onItemClickListener != null) {
          BidAdapter.this.onItemClickListener.onBidItemClicked(bidModel);
        }
      }
    });
  }

  @Override public long getItemId(int position) {
    return position;
  }

  public void setBidsCollection(Collection<BidModel> bidsCollection) {
    this.validateBidsCollection(bidsCollection);
    this.bidsCollection = (List<BidModel>) bidsCollection;
    this.notifyDataSetChanged();
  }

  public void setOnItemClickListener (OnItemClickListener onItemClickListener) {
    this.onItemClickListener = onItemClickListener;
  }

  private void validateBidsCollection(Collection<BidModel> bidsCollection) {
    if (bidsCollection == null) {
      throw new IllegalArgumentException("The list cannot be null");
    }
  }

  static class BidViewHolder extends RecyclerView.ViewHolder {
    @Bind(R.id.title) TextView textViewTitle;

    public BidViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }
}
