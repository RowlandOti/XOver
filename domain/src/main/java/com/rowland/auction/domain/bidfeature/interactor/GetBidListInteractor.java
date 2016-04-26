package com.rowland.auction.domain.bidfeature.interactor;

import com.rowland.auction.domain.bidfeature.model.Bid;
import com.rowland.auction.domain.bidfeature.repository.IBidRepository;
import com.rowland.auction.domain.executor.IPostExecutionThread;
import com.rowland.auction.domain.executor.IThreadExecutor;
import com.rowland.auction.domain.interactor.UseCase;

import javax.inject.Inject;

import rx.Observable;
import sun.rmi.runtime.Log;

/**
 * This class is an implementation of {@link UseCase} that represents a use case for
 * retrieving a collection of all {@link Bid}.
 */
public class GetBidListInteractor extends UseCase {

    // Class log identifier
    public final static String LOG_TAG = GetBidListInteractor.class.getSimpleName();

    private final IBidRepository bidRepository;

    @Inject
    public GetBidListInteractor(com.rowland.auction.domain.bidfeature.repository.IBidRepository bidRepository, IThreadExecutor threadExecutor, IPostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.bidRepository = bidRepository;
    }

    @Override
    public Observable buildUseCaseObservable() {
        return this.bidRepository.getList();
    }
}
