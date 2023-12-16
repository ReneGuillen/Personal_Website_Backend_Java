package org.recorder.service;

import org.recorder.dagger.DaggerSoccerRecorderComponent;
import org.recorder.dagger.SoccerRecorderComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.Map;

public class SoccerRecorderHandler {

    private static final Logger log = LoggerFactory.getLogger(SoccerRecorderHandler.class);
    private final SoccerRecorder soccerRecorder;

    public SoccerRecorderHandler() {
        SoccerRecorderComponent component = DaggerSoccerRecorderComponent.create();
        this.soccerRecorder = component.provideSoccerRecorderHandler().soccerRecorder;
    }

    @Inject
    public SoccerRecorderHandler(final SoccerRecorder soccerRecorder) {
        this.soccerRecorder = soccerRecorder;
    }

    public void handleRequest(Map<String, Object> event) {
        log.info("Event received: {}", event);

        log.info("Starting the soccer recorder workflow.. ");
        soccerRecorder.record();
    }
}
