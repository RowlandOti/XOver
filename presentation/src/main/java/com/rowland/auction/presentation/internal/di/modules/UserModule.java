package com.rowland.auction.presentation.internal.di.modules;

import com.rowland.auction.domain.userfeature.repository.IUserRepository;
import com.rowland.auction.domain.executor.IPostExecutionThread;
import com.rowland.auction.domain.executor.IThreadExecutor;
import com.rowland.auction.domain.userfeature.interactor.GetUserDetailsInteractor;
import com.rowland.auction.domain.userfeature.interactor.GetUserListInteractor;
import com.rowland.auction.domain.interactor.UseCase;
import com.rowland.auction.presentation.internal.di.PerActivity;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger module that provides user related collaborators.
 */
@Module
public class UserModule {

    private int userId = -1;

    public UserModule() {
    }

    public UserModule(int userId) {
        this.userId = userId;
    }

    @Provides
    @PerActivity
    @Named("userList")
    UseCase provideGetUserListUseCase(GetUserListInteractor getUserList) {
        return getUserList;
    }

    @Provides
    @PerActivity
    @Named("userDetails")
    UseCase provideGetUserDetailsUseCase(IUserRepository userRepository, IThreadExecutor threadExecutor, IPostExecutionThread postExecutionThread) {
        return new GetUserDetailsInteractor(userId, userRepository, threadExecutor, postExecutionThread);
    }
}