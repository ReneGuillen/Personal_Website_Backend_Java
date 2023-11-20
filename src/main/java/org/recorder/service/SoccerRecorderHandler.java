package org.recorder.service;

import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import java.util.Map;

@Slf4j
public class SoccerRecorderHandler {

    private final SoccerRecorder soccerRecorder;

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
