package org.tonylin.practice.eventbus;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.google.common.eventbus.EventBus;

public class TestEventBus {

    private EventBus eventBus = new EventBus();
    
    private MJGoToPronhub MJGoToPronhubHandler = new MJGoToPronhub();
    private MJGoToWanhua MJGoToWanhuaHandler = new MJGoToWanhua();
    private MJGoToPronhubButWebCrash MJGoToPronhubButWebCrashHandler = new MJGoToPronhubButWebCrash();
    
    
    private MJGoToWanhua2 MJGoToWanhuaHandler2 = new MJGoToWanhua2();
    
    private void thenTheHandlerShouldReceiveTheEvent() {
        assertEquals(1, MJGoToPronhubHandler.getReceivedEvents().size());
        assertEquals(1, MJGoToWanhuaHandler.getReceivedEvents().size());
    }
    
    private void givenEventBusRegisterTwoGoodHandler(EventBus eventBus) {
        eventBus.register(MJGoToPronhubHandler);
        eventBus.register(MJGoToWanhuaHandler);
        eventBus.register(MJGoToWanhuaHandler2);
    }
    
    @Test
    public void ShouldGetReceivedEventsWhenPostEventToHandlers() {
        givenEventBusRegisterTwoGoodHandler(eventBus);
        MJGotGodGuyCardEvent event = new MJGotGodGuyCardEvent("Nancy");
        
        eventBus.post(event);
        
        thenTheHandlerShouldReceiveTheEvent();
    }
    
    private void givenEventBusRegisterContainBadeHandler() {
        eventBus.register(MJGoToPronhubHandler);
        eventBus.register(MJGoToPronhubButWebCrashHandler);
        eventBus.register(MJGoToWanhuaHandler);
    }
    
    @Test
    public void ShouldIgnoreErrorWhenPostEventToBadHandler() {
        givenEventBusRegisterContainBadeHandler();
        MJGotGodGuyCardEvent event = new MJGotGodGuyCardEvent("Nancy");
        
        eventBus.post(event);
        thenTheHandlerShouldReceiveTheEvent();
    }
}
