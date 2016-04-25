package com.rowland.auction.data.bidfeature.cache;


import com.rowland.auction.data.bidfeature.payload.BidPayload;

import rx.Observable;

/**
 * An interface representing a user Cache.
 */
public interface IBidCache {
    /**
     * Gets an {@link rx.Observable} which will emit a {@link BidPayload}.
     *
     * @param userId The bid id to retrieve data.
     */
    Observable<BidPayload> get(final int userId);

    /**
     * Puts and element into the cache.
     *
     * @param userPayload Element to insert in the cache.
     */
    void put(BidPayload userPayload);

    /**
     * Checks if an element (Bid) exists in the cache.
     *
     * @param userId The id used to look for inside the cache.
     * @return true if the element is cached, otherwise false.
     */
    boolean isCached(final int userId);

    /**
     * Checks if the cache is expired.
     *
     * @return true, the cache is expired, otherwise false.
     */
    boolean isExpired();

    /**
     * Evict all elements of the cache.
     */
    void evictAll();
}
