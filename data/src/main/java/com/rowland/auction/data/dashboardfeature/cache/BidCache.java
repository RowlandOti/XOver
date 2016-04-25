package com.rowland.auction.data.dashboardfeature.cache;

import android.content.Context;

import com.rowland.auction.data.cache.FileManager;
import com.rowland.auction.data.dashboardfeature.exception.BidNotFoundException;
import com.rowland.auction.data.dashboardfeature.payload.BidPayload;
import com.rowland.auction.data.userfeature.cache.BidJsonSerializer;
import com.rowland.auction.domain.executor.IThreadExecutor;

import java.io.File;
import javax.inject.Inject;
import javax.inject.Singleton;
import rx.Observable;

/**
 * {@link BidCache} implementation.
 */
@Singleton
public class BidCache implements IBidCache {

  private static final String SETTINGS_FILE_NAME = "com.rowland.auction.SETTINGS";
  private static final String SETTINGS_KEY_LAST_CACHE_UPDATE = "last_cache_update";

  private static final String DEFAULT_FILE_NAME = "bid_";
  private static final long EXPIRATION_TIME = 60 * 10 * 1000;

  private final Context context;
  private final File cacheDir;
  private final BidJsonSerializer serializer;
  private final FileManager fileManager;
  private final IThreadExecutor threadExecutor;

  /**
   * Constructor of the class {@link BidCache}.
   *
   * @param context A
   * @param bidCacheSerializer {@link BidJsonSerializer} for object serialization.
   * @param fileManager {@link FileManager} for saving serialized objects to the file system.
   */
  @Inject
  public BidCache(Context context, BidJsonSerializer bidCacheSerializer,
                  FileManager fileManager, IThreadExecutor executor) {
    if (context == null || bidCacheSerializer == null || fileManager == null || executor == null) {
      throw new IllegalArgumentException("Invalid null parameter");
    }
    this.context = context.getApplicationContext();
    this.cacheDir = this.context.getCacheDir();
    this.serializer = bidCacheSerializer;
    this.fileManager = fileManager;
    this.threadExecutor = executor;
  }

  @Override public Observable<BidPayload> get(final int bidId) {
    return Observable.create(subscriber -> {
      File bidEntityFile = BidCache.this.buildFile(bidId);
      String fileContent = BidCache.this.fileManager.readFileContent(bidEntityFile);
      BidPayload bidPayload = BidCache.this.serializer.deserialize(fileContent);

      if (bidPayload != null) {
        subscriber.onNext(bidPayload);
        subscriber.onCompleted();
      } else {
        subscriber.onError(new BidNotFoundException());
      }
    });
  }

  @Override public void put(BidPayload bidPayload) {
    if (bidPayload != null) {
      File bidEntitiyFile = this.buildFile(bidPayload.getBidPayloadId());
      if (!isCached(bidPayload.getBidPayloadId())) {
        String jsonString = this.serializer.serialize(bidPayload);
        this.executeAsynchronously(new CacheWriter(this.fileManager, bidEntitiyFile,
            jsonString));
        setLastCacheUpdateTimeMillis();
      }
    }
  }

  @Override public boolean isCached(int bidId) {
    File bidEntitiyFile = this.buildFile(bidId);
    return this.fileManager.exists(bidEntitiyFile);
  }

  @Override public boolean isExpired() {
    long currentTime = System.currentTimeMillis();
    long lastUpdateTime = this.getLastCacheUpdateTimeMillis();

    boolean expired = ((currentTime - lastUpdateTime) > EXPIRATION_TIME);

    if (expired) {
      this.evictAll();
    }

    return expired;
  }

  @Override public void evictAll() {
    this.executeAsynchronously(new CacheEvictor(this.fileManager, this.cacheDir));
  }

  /**
   * Build a file, used to be inserted in the disk cache.
   *
   * @param bidId The id bid to build the file.
   * @return A valid file.
   */
  private File buildFile(int bidId) {
    StringBuilder fileNameBuilder = new StringBuilder();
    fileNameBuilder.append(this.cacheDir.getPath());
    fileNameBuilder.append(File.separator);
    fileNameBuilder.append(DEFAULT_FILE_NAME);
    fileNameBuilder.append(bidId);

    return new File(fileNameBuilder.toString());
  }

  /**
   * Set in millis, the last time the cache was accessed.
   */
  private void setLastCacheUpdateTimeMillis() {
    long currentMillis = System.currentTimeMillis();
    this.fileManager.writeToPreferences(this.context, SETTINGS_FILE_NAME,
        SETTINGS_KEY_LAST_CACHE_UPDATE, currentMillis);
  }

  /**
   * Get in millis, the last time the cache was accessed.
   */
  private long getLastCacheUpdateTimeMillis() {
    return this.fileManager.getFromPreferences(this.context, SETTINGS_FILE_NAME,
        SETTINGS_KEY_LAST_CACHE_UPDATE);
  }

  /**
   * Executes a {@link Runnable} in another Thread.
   *
   * @param runnable {@link Runnable} to execute
   */
  private void executeAsynchronously(Runnable runnable) {
    this.threadExecutor.execute(runnable);
  }

  /**
   * {@link Runnable} class for writing to disk.
   */
  private static class CacheWriter implements Runnable {
    private final FileManager fileManager;
    private final File fileToWrite;
    private final String fileContent;

    CacheWriter(FileManager fileManager, File fileToWrite, String fileContent) {
      this.fileManager = fileManager;
      this.fileToWrite = fileToWrite;
      this.fileContent = fileContent;
    }

    @Override public void run() {
      this.fileManager.writeToFile(fileToWrite, fileContent);
    }
  }

  /**
   * {@link Runnable} class for evicting all the cached files
   */
  private static class CacheEvictor implements Runnable {
    private final FileManager fileManager;
    private final File cacheDir;

    CacheEvictor(FileManager fileManager, File cacheDir) {
      this.fileManager = fileManager;
      this.cacheDir = cacheDir;
    }

    @Override public void run() {
      this.fileManager.clearDirectory(this.cacheDir);
    }
  }
}
