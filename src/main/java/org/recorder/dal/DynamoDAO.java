package org.recorder.dal;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;

import javax.inject.Inject;
import java.util.List;

public class DynamoDAO {
    private final DynamoDBMapper dynamoDBMapper;

    @Inject
    public DynamoDAO(final DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
    }

    public <T> void saveItems(final List<T> values) {
        values.forEach(dynamoDBMapper::save);
    }

    public <T> List<T> loadItems(final Class<T> clazz) {
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
        return dynamoDBMapper.scan(clazz, scanExpression);
    }
}
