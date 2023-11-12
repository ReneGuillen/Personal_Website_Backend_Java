package org.recorder.dagger;

import dagger.Provides;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

import javax.inject.Singleton;

public class AWSModule {

    @Provides
    @Singleton
    public DynamoDbClient provideDynamoDBClient() {
        return DynamoDbClient.create();
    }
}
