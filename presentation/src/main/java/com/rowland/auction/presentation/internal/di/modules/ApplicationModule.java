package com.rowland.auction.presentation.internal.di.modules;

import android.content.Context;

import com.rowland.auction.data.executor.JobThreadExecutor;
import com.rowland.auction.data.userfeature.cache.IUserCache;
import com.rowland.auction.data.userfeature.cache.UserCache;
import com.rowland.auction.presentation.userfeature.repository.UserDataRepository;
import com.rowland.auction.domain.executor.IPostExecutionThread;
import com.rowland.auction.domain.executor.IThreadExecutor;
import com.rowland.auction.domain.userfeature.repository.IUserRepository;
import com.rowland.auction.presentation.ApplicationController;
import com.rowland.auction.presentation.UIThread;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger module that provides objects which will live during the application lifecycle.
 */
@Module
public class ApplicationModule {
    private final ApplicationController application;

    public ApplicationModule(ApplicationController application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return this.application;
    }

    @Provides
    @Singleton
    IThreadExecutor provideThreadExecutor(JobThreadExecutor jobExecutor) {
        return jobExecutor;
    }

    @Provides
    @Singleton
    IPostExecutionThread providePostExecutionThread(UIThread uiThread) {
        return uiThread;
    }

    @Provides
    @Singleton
    IUserCache provideUserCache(UserCache userCache) {
        return userCache;
    }

    @Provides
    @Singleton
    IUserRepository provideUserRepository(UserDataRepository userDataRepository) {
        return userDataRepository;
    }
}
