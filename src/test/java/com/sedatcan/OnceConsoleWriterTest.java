package com.sedatcan;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by sedatcans on 28.04.2017.
 */
public class OnceConsoleWriterTest {

    @Test
    public void shouldLockAndTry()
    {
        OnceConsoleWriter onceConsoleWriter = new OnceConsoleWriter("testLock" );
        assertFalse(onceConsoleWriter.isLocked());
        onceConsoleWriter.lock(10, TimeUnit.SECONDS);
        assertTrue(onceConsoleWriter.isLocked());
        onceConsoleWriter.unlock();
    }
    @Test
    public void shouldUnLockAndTry()
    {
        OnceConsoleWriter onceConsoleWriter = new OnceConsoleWriter("testLock" );
        assertFalse(onceConsoleWriter.isLocked());
        onceConsoleWriter.lock(-1, TimeUnit.SECONDS);
        onceConsoleWriter.unlock();
        assertFalse(onceConsoleWriter.isLocked());

    }


}
