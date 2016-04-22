package com.doea.app.presentation.userfeature.repository;

import com.doea.app.data.userfeature.payload.mapper.UserPayloadDataMapper;
import com.doea.app.domain.userfeature.repository.IUserRepository;
import com.doea.app.data.userfeature.repository.datasource.IUserDataStore;
import com.doea.app.domain.repository.IRepository;
import com.doea.app.domain.userfeature.model.User;
import com.doea.app.presentation.userfeature.repository.datasource.UserDataStoreFactory;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;

/**
 * {@link IUserRepository} for retrieving user data.
 */
@Singleton
public class UserDataRepository implements IRepository, IUserRepository {

    private final UserDataStoreFactory userDataStoreFactory;
    private final UserPayloadDataMapper userEntityDataMapper;

    /**
     * Constructs a {@link IUserRepository}.
     *
     * @param dataStoreFactory     A factory to construct different data source implementations.
     * @param userEntityDataMapper {@link UserPayloadDataMapper}.
     */
    @Inject
    public UserDataRepository(UserDataStoreFactory dataStoreFactory, UserPayloadDataMapper userEntityDataMapper) {
        this.userDataStoreFactory = dataStoreFactory;
        this.userEntityDataMapper = userEntityDataMapper;
    }

    @SuppressWarnings("Convert2MethodRef")
    @Override
    public Observable<List<User>> getList() {
        //we always get all users from the cloud
        final IUserDataStore userDataStore = this.userDataStoreFactory.createCloudDataStore();
        return userDataStore.userEntityList().map(userEntities -> this.userEntityDataMapper.transform(userEntities));
    }

    @SuppressWarnings("Convert2MethodRef")
    @Override
    public Observable<User> getItem(int userId) {
        final IUserDataStore userDataStore = this.userDataStoreFactory.create(userId);
        return userDataStore.userEntityDetails(userId).map(userEntity -> this.userEntityDataMapper.transform(userEntity));
    }
}
