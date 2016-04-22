package com.doea.app.presentation.internal.di.modules;

import android.content.Context;

import com.doea.app.data.executor.JobThreadExecutor;
import com.doea.app.data.userfeature.cache.IUserCache;
import com.doea.app.data.userfeature.cache.UserCache;
import com.doea.app.presentation.userfeature.repository.UserDataRepository;
import com.doea.app.domain.executor.IPostExecutionThread;
import com.doea.app.domain.executor.IThreadExecutor;
import com.doea.app.domain.userfeature.repository.IUserRepository;
import com.doea.app.presentation.ApplicationController;
import com.doea.app.presentation.UIThread;

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
