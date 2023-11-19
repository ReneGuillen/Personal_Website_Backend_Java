package org.recorder.domain.db;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

// FAVORITE_TEAM_MATCHES
// [ UniqueId | Date&Time | TeamName | TeamId | League | LeagueId | Against ]
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@DynamoDBTable(tableName = "Favorite-Team-Matches")
public class MatchDBModel {

    @DynamoDBHashKey(attributeName = "uniqueId")
    private String uniqueId;

    @DynamoDBRangeKey(attributeName = "matchDateAndTime")
    private Instant matchDateAndTime;

    private String teamName;
    private int teamId;
    private String LeagueName;
    private int leagueId;
    private int againstTeamId;
    private String againstTeamName;
}
