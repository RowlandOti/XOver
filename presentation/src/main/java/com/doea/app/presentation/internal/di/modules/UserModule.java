package com.doea.app.presentation.internal.di.modules;

import com.doea.app.domain.userfeature.repository.IUserRepository;
import com.doea.app.domain.executor.IPostExecutionThread;
import com.doea.app.domain.executor.IThreadExecutor;
import com.doea.app.domain.userfeature.interactor.GetUserDetailsInteractor;
import com.doea.app.domain.userfeature.interactor.GetUserListInteractor;
import com.doea.app.domain.userfeature.interactor.UseCase;
import com.doea.app.presentation.internal.di.PerActivity;

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
    UseCase provideGetUserDetailsUseCase(IUserRepository userRepository, IThreadExecutor threadExecutor,
                                         IPostExecutionThread postExecutionThread) {
        return new GetUserDetailsInteractor(userId, userRepository, threadExecutor, postExecutionThread);
    }
}