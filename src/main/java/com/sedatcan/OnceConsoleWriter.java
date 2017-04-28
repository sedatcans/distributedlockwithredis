package com.sedatcan;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RSemaphore;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.util.concurrent.TimeUnit;

/**
 * Created by sedatcans on 28.04.2017.
 */
public class OnceConsoleWriter {
    private static final String host = "54.91.79.201";
    private static final Integer port = 6379;
    private RedissonClient client = null;
    private RLock lock;


    public OnceConsoleWriter(String lockName) {
        Config config = new Config();
        config.useSingleServer()
                .setAddress(host + ":" + port).setTimeout(100000);
        client = Redisson.create(config);
        lock = getClient().getFairLock(lockName);
    }

    public RedissonClient getClient() {
        return client;
    }

    public RLock getWriterLock() {
        if (lock == null) {
            throw new RuntimeException("Object not Initialized");
        }
        return lock;
    }
    public RSemaphore getSemaphore()
    {
       return getClient().getSemaphore("SemaphoreWriter");
    }

    public void lock() {
        getWriterLock().lock();
    }

    public void lock(long timeoutSeconds, TimeUnit timeUnit) {
        getWriterLock().lock(timeoutSeconds, timeUnit);
    }

    public void lock(long timeoutSeconds) {
        lock(timeoutSeconds, TimeUnit.SECONDS);
    }

    public void unlock() {
        getWriterLock().unlock();
    }

    public boolean isLocked() {
        return getWriterLock().isLocked();
    }

    public boolean isHeld() {
        return getWriterLock().isHeldByCurrentThread();
    }


}
