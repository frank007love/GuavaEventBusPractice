package org.tonylin.practice.eventbus;

public class MJGotGodGuyCardEvent {

    private final String girlName;
    
    public MJGotGodGuyCardEvent(String girlName) {
        this.girlName = girlName;
    }
    
    public String getGirlName() {
        return girlName;
    }
    
}
