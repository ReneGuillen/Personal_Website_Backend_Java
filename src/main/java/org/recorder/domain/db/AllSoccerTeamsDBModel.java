package org.recorder.domain.db;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// ALL_SOCCER_TEAMS
// [ UniqueID | isFavorite | TeamName | TeamID | League | LeagueId ]
// Record ALL teams from supported league.
// League: (LeagueOne, LaLiga, PremierLeague)
// Cups: (ChampionsLeague)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@DynamoDBTable(tableName="AllSoccerTeams")
public class AllSoccerTeamsDBModel {

    @DynamoDBHashKey(attributeName = "uniqueId")
    private String uniqueId;

    @DynamoDBRangeKey(attributeName = "isFavorite")
    private boolean isFavorite;

    private String teamName;
    private String teamId;
    private String leagueName;
    private String leagueId;
}
