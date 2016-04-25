package com.rowland.auction.domain.userfeature.interactor;

import com.rowland.auction.domain.executor.IPostExecutionThread;
import com.rowland.auction.domain.executor.IThreadExecutor;
import com.rowland.auction.domain.interactor.UseCase;
import com.rowland.auction.domain.userfeature.model.User;
import com.rowland.auction.domain.userfeature.repository.IUserRepository;

import javax.inject.Inject;

import rx.Observable;

/**
 * This class is an implementation of {@link UseCase} that represents a use case for
 * retrieving a collection of all {@link User}.
 */
public class GetUserListInteractor extends UseCase {

    private final IUserRepository userRepository;

    @Inject
    public GetUserListInteractor(IUserRepository userRepository, IThreadExecutor threadExecutor, IPostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.userRepository = userRepository;
    }

    @Override
    public Observable buildUseCaseObservable() {
        return this.userRepository.getList();
    }
}
