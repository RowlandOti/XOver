package com.rowland.auction.presentation.internal.di.modules;

import com.rowland.auction.domain.executor.IPostExecutionThread;
import com.rowland.auction.domain.executor.IThreadExecutor;
import com.rowland.auction.domain.interactor.UseCase;
import com.rowland.auction.domain.bidfeature.interactor.GetBidDetailsInteractor;
import com.rowland.auction.domain.bidfeature.interactor.GetBidListInteractor;
import com.rowland.auction.domain.bidfeature.repository.IBidRepository;
import com.rowland.auction.presentation.internal.di.PerActivity;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger module that provides bid related collaborators.
 */
@Module
public class BidModule {

    private int bidId = -1;

    public BidModule() {
    }

    public BidModule(int bidId) {
        this.bidId = bidId;
    }

    @Provides
    @PerActivity
    @Named("bidList")
    UseCase provideGetBidListUseCase(GetBidListInteractor getBidList) {
        return getBidList;
    }

    @Provides
    @PerActivity
    @Named("bidDetails")
    UseCase provideGetBidDetailsUseCase(IBidRepository bidRepository, IThreadExecutor threadExecutor, IPostExecutionThread postExecutionThread) {
        return new GetBidDetailsInteractor(bidId, bidRepository, threadExecutor, postExecutionThread);
    }
}