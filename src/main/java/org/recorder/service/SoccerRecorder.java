package org.recorder.service;

import lombok.extern.slf4j.Slf4j;
import org.recorder.dal.DynamoDAO;
import org.recorder.dal.SoccerDAO;
import org.recorder.domain.db.MatchDBModel;
import org.recorder.domain.db.TeamDBModel;
import org.recorder.domain.soccer.Team;
import org.recorder.util.MatchConverter;
import org.recorder.util.TeamConverter;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public class SoccerRecorder {
    // Currently only supporting [Premier_League | La_Liga | Ligue_One]
    private final List<String> SUPPORTED_LEAGUE_IDS = List.of("39", "140", "61");
    private final SoccerDAO soccerDAO;
    private final DynamoDAO dynamoDAO;

    @Inject
    public SoccerRecorder(
            final SoccerDAO soccerDAO, final DynamoDAO dynamoDAO) {
        this.soccerDAO = soccerDAO;
        this.dynamoDAO = dynamoDAO;
    }

    public void record() {
        log.info("Loading current teams stored in DB.. ");
        final List<TeamDBModel> teamsDBModel =
                dynamoDAO.loadItems(TeamDBModel.class);

        final Map<String, Team> teamsFromDB = teamsDBModel.stream()
                .collect(Collectors.toMap(
                        TeamDBModel::getTeamName,
                        TeamConverter::convertToSoccerTeam));

        log.info("Fetching all teams from Soccer API.. ");
        final List<TeamDBModel> teamsFromSoccerDao =
                this.getAllTeamsFromSupportedLeagues(
                        SUPPORTED_LEAGUE_IDS, teamsFromDB);

        log.info("Storing all teams in DynamoDB.. ");
        this.dynamoDAO.saveItems(teamsFromSoccerDao);

        log.info("Fetching all matches for favorite teams from Soccer API.. ");
        final List<MatchDBModel> matchesForFavoriteTeams =
                this.getAllMatchesForFavoriteTeams(teamsFromSoccerDao);

        log.info("String all matches in DynamoDB.. ");
        this.dynamoDAO.saveItems(matchesForFavoriteTeams);
    }

    private List<TeamDBModel> getAllTeamsFromSupportedLeagues(
            final List<String> leagueIds, final Map<String, Team> teamsFromDB) {
        return leagueIds.stream()
                .map(leagueId -> {
                    List<Team> teams = soccerDAO.getAllTeamsForLeague(leagueId);
                    teams.forEach(team -> {
                        final String name = team.getName();
                        team.setLeagueId(leagueId);
                        team.setFavorite(teamsFromDB.containsKey(name)
                                && teamsFromDB.get(name).isFavorite());
                    });
                    return teams;
                })
                .flatMap(List::stream)
                .map(TeamConverter::convertToDBModelTeam)
                .toList();
    }

    private List<MatchDBModel> getAllMatchesForFavoriteTeams(final List<TeamDBModel> teams) {
        return teams.stream()
                .filter(TeamDBModel::isFavorite)
                .map(team ->
                        soccerDAO.getAllMatchesForTeam(
                                Integer.toString(team.getTeamId()),
                                team.getLeagueId()))
                .flatMap(List::stream)
                .map(MatchConverter::convertToDBModelMatch)
                .toList();
    }

}
