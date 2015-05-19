package com.happysathya.codekata;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.fail;

public class SyncObjectTest {

    private SyncObject syncObject;

    @Before
    public void setup() {
        syncObject = new SyncObject(new Lock());
    }

    @Test
    public void shouldNotThrowExceptionUsingSynchronizedPattern() {
        try {
            syncObject.startTwoThreads();
        } catch (InterruptedException e) {
            fail();
        }
    }

}
