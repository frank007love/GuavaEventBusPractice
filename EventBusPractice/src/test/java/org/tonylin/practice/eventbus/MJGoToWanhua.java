package org.tonylin.practice.eventbus;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.eventbus.AllowConcurrentEvents;
import com.google.common.eventbus.Subscribe;

public class MJGoToWanhua {
    private static Logger logger = LoggerFactory.getLogger(MJGoToWanhua.class);
    private List<MJGotGodGuyCardEvent> receivedEvents = new CopyOnWriteArrayList<>();
    
    @AllowConcurrentEvents
    @Subscribe
    public void handle(MJGotGodGuyCardEvent event) {
        receivedEvents.add(event);
        logger.info("MJGoToWanhua due to {}", event.getGirlName());
    }
    
    public List<MJGotGodGuyCardEvent> getReceivedEvents(){
        return receivedEvents;
    }
}
