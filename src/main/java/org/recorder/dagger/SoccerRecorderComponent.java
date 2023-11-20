package org.recorder.dagger;

import dagger.Component;
import org.recorder.service.SoccerRecorderHandler;

import javax.inject.Singleton;

@Singleton
@Component(modules = {AWSModule.class, DependencyModule.class})
public interface SoccerRecorderComponent {

    SoccerRecorderHandler provideSoccerRecorderHandler();
}
