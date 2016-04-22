package com.doea.app.domain.userfeature.interactor;

import com.doea.app.domain.executor.IPostExecutionThread;
import com.doea.app.domain.executor.IThreadExecutor;
import com.doea.app.domain.userfeature.model.User;
import com.doea.app.domain.repository.IRepository;
import com.doea.app.domain.userfeature.repository.IUserRepository;


import javax.inject.Inject;

import rx.Observable;

/**
 * This class is an implementation of {@link UseCase} that represents a use case for
 * retrieving data related to an specific {@link User}.
 */
public class GetUserDetailsInteractor extends UseCase {

    private final int userId;
    private final IUserRepository userRepository;

    @Inject
    public GetUserDetailsInteractor(int userId, IUserRepository userRepository, IThreadExecutor threadExecutor, IPostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.userId = userId;
        this.userRepository = userRepository;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return this.userRepository.getItem(this.userId);
    }
}
