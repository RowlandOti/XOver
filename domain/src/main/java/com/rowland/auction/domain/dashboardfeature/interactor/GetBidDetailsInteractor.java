package com.rowland.auction.domain.dashboardfeature.interactor;

import com.rowland.auction.domain.dashboardfeature.model.Bid;
import com.rowland.auction.domain.dashboardfeature.repository.IBidRepository;
import com.rowland.auction.domain.executor.IPostExecutionThread;
import com.rowland.auction.domain.executor.IThreadExecutor;
import com.rowland.auction.domain.interactor.UseCase;

import javax.inject.Inject;

import rx.Observable;

/**
 * This class is an implementation of {@link UseCase} that represents a use case for
 * retrieving a collection of all {@link Bid}.
 */
public class GetBidDetailsInteractor extends UseCase {

    private final int bidId;
    private final IBidRepository bidRepository;

    @Inject
    public GetBidDetailsInteractor(int bidId, IBidRepository bidRepository, IThreadExecutor threadExecutor, IPostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.bidId = bidId;
        this.bidRepository = bidRepository;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return this.bidRepository.getItem(this.bidId);
    }
}
