package org.recorder.dal;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import java.util.List;

@Slf4j
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
        DynamoDBQueryExpression<T> queryExpression =
                new DynamoDBQueryExpression<T>();
        return dynamoDBMapper.query(clazz, queryExpression);
    }
}
