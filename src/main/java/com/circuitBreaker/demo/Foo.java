package com.circuitBreaker.demo;

import java.util.concurrent.atomic.AtomicInteger;

public class Foo {
    private static int THRESHOLD;
    private static final int COOLDOWN_PERIOD = 1;

    private AtomicInteger failureCount;
    private long lastOpenTime;
    private State state;

    public Foo(int threshold) {
    	Foo.THRESHOLD = threshold;
        this.failureCount = new AtomicInteger();
        this.state = State.CLOSED;
    }

    public boolean isServiceAvailable() {
        if (state == State.OPEN) {
            long currentTime = System.currentTimeMillis();
            if (currentTime - lastOpenTime > COOLDOWN_PERIOD * 1000) {
                state = State.PARTIALLY_OPEN;
            } else {
                return false;
            }
        }

        if (state == State.PARTIALLY_OPEN) {
            if (failureCount.get() > THRESHOLD) {
                state = State.OPEN;
                lastOpenTime = System.currentTimeMillis();
                reportSuccess();
                return false;
            } else {
            	state = State.CLOSED;
                return true;
            }
        }
        if(state == State.CLOSED) {
            if (failureCount.get() > THRESHOLD) {
                state = State.OPEN;
                lastOpenTime = System.currentTimeMillis();
                return false;
            } else {
                return true;
            }
        }
        
        return true;
    }

    public void reportFailure() {
        failureCount.incrementAndGet();
    }

    public void reportSuccess() {
        failureCount.set(0);
    }

    public State getState() {
        return state;
    }

    
}
