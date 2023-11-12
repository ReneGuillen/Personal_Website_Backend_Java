package org.recorder.dagger;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class AWSModule {

    @Provides
    @Singleton
    public DynamoDBMapper provideDynamoDBClient(final AmazonDynamoDB amazonDynamoDB) {
        return new DynamoDBMapper(amazonDynamoDB);
    }

    @Provides
    @Singleton
    public AmazonDynamoDB provideDynamoDB() {
        return AmazonDynamoDBClientBuilder.standard().build();
    }
}
