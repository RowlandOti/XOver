package com.rowland.auction.domain.bidfeature.interactor;

import com.rowland.auction.domain.executor.IPostExecutionThread;
import com.rowland.auction.domain.executor.IThreadExecutor;
import com.rowland.auction.domain.interactor.UseCase;

import javax.inject.Inject;

import rx.Observable;

/**
 * This class is an implementation of {@link UseCase} that represents a use case for
 * retrieving a collection of all {@link com.rowland.auction.domain.bidfeature.model.Bid}.
 */
public class GetBidDetailsInteractor extends UseCase {

    private final int bidId;
    private final com.rowland.auction.domain.bidfeature.repository.IBidRepository bidRepository;

    @Inject
    public GetBidDetailsInteractor(int bidId, com.rowland.auction.domain.bidfeature.repository.IBidRepository bidRepository, IThreadExecutor threadExecutor, IPostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.bidId = bidId;
        this.bidRepository = bidRepository;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return this.bidRepository.getItem(this.bidId);
    }
}
