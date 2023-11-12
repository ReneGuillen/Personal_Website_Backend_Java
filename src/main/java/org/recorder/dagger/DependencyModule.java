package org.recorder.dagger;

import dagger.Module;
import dagger.Provides;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;

import javax.inject.Singleton;

@Module
public class DependencyModule {

    @Provides
    @Singleton
    public CloseableHttpClient provideHttpClient() {
        return HttpClients.createDefault();
    }
}
