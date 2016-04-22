package com.doea.app.domain.repository;

import com.doea.app.domain.userfeature.model.User;

import java.util.List;

import rx.Observable;

/**
 * Interface that represents a Repository for getting {@link User} related data.
 */
public interface IRepository<T> {
    /**
     * Get an {@link rx.Observable} which will emit a List of {@link User}.
     */
    Observable<List<T>> getList();

    /**
     * Get an {@link rx.Observable} which will emit a {@link User}.
     *
     * @param itemId The user id used to retrieve user data.
     */
    Observable<T> getItem(final int itemId);
}

