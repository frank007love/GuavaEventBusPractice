package org.tonylin.practice.eventbus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.eventbus.AllowConcurrentEvents;
import com.google.common.eventbus.Subscribe;

public class MJGoToPronhubButWebCrash {
    private static Logger logger = LoggerFactory.getLogger(MJGoToPronhubButWebCrash.class);
    
    @AllowConcurrentEvents
    @Subscribe
    public void handle(MJGotGodGuyCardEvent event) {
        logger.info("MJGoToPronhubButWebCrash due to {}", event.getGirlName());
        throw new RuntimeException("MJ cry cry");
    }
    
}
