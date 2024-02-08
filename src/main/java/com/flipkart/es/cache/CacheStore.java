package com.flipkart.es.cache;

import java.time.Duration;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

public class CacheStore<T> {

    private Cache<String, T> cache;

    public CacheStore(Duration expiryTime) {
        this.cache = CacheBuilder.newBuilder()
        // explicitly setting up the time for expiry of that particular object
                .expireAfterWrite(expiryTime)
                // getting the processors available time
                .concurrencyLevel(Runtime.getRuntime().availableProcessors())
                .build();
    }

    public void add(String key, T value){
        cache.put(key, value);
    }

    public T get(String key){
        return cache.getIfPresent(key);
    }

    public void remove(String key){
        cache.invalidate(key);
    }

}
