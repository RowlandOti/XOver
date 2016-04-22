package com.doea.app.domain.userfeature.interactor;

import com.doea.app.domain.executor.IPostExecutionThread;
import com.doea.app.domain.executor.IThreadExecutor;
import com.doea.app.domain.repository.IRepository;
import com.doea.app.domain.userfeature.model.User;
import com.doea.app.domain.userfeature.repository.IUserRepository;

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
