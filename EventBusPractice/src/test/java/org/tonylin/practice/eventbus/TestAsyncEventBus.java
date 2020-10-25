package org.tonylin.practice.eventbus;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;

public class TestAsyncEventBus {
    private AsyncEventBus asyncEventBus = new AsyncEventBus(Executors.newCachedThreadPool());
    
    private MJGoToPronhub MJGoToPronhubHandler = new MJGoToPronhub();
    private MJGoToWanhua MJGoToWanhuaHandler = new MJGoToWanhua();
    
    private void thenTheHandlerShouldReceiveTheEvent() {
        assertEquals(1, MJGoToPronhubHandler.getReceivedEvents().size());
        assertEquals(1, MJGoToWanhuaHandler.getReceivedEvents().size());
    }
    
    private void givenEventBusRegisterTwoGoodHandler(EventBus eventBus) {
        eventBus.register(MJGoToPronhubHandler);
        eventBus.register(MJGoToWanhuaHandler);
    }
    
    private MJGotGodGuyCardEvent givenDelayedMJGotGodGuyCardEvent(CountDownLatch latch) {
        return new MJGotGodGuyCardEvent("Nancy") {
            @Override
            public String getGirlName() {
                try {
                    TimeUnit.SECONDS.sleep(1);
                    return super.getGirlName();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    latch.countDown();
                }
            }
        };
    }
    
    private void thenPostShouldBeNotBlocked(long startTime) {
        long afterTime = System.currentTimeMillis();
        assertTrue((afterTime-startTime)<1000);
    }
    
    private void thenPostShouldBeDoneWithParallel(long startTime, CountDownLatch latch) throws InterruptedException {
        latch.await();
        long afterTime = System.currentTimeMillis();
        assertTrue((afterTime-startTime)>1000);
    }
    
    @Test
    public void testAsyncEventBus() throws InterruptedException {
        givenEventBusRegisterTwoGoodHandler(asyncEventBus);
        
        CountDownLatch latch = new CountDownLatch(2);
        MJGotGodGuyCardEvent event = givenDelayedMJGotGodGuyCardEvent(latch);
        
        long startTime = System.currentTimeMillis();
        asyncEventBus.post(event);
       
        thenPostShouldBeNotBlocked(startTime);
        thenPostShouldBeDoneWithParallel(startTime, latch);
        thenTheHandlerShouldReceiveTheEvent();
    }
}
