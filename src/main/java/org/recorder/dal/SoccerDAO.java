package org.recorder.dal;

import lombok.extern.slf4j.Slf4j;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.recorder.domain.soccer.Match;
import org.recorder.domain.soccer.Team;
import org.recorder.jackson.SoccerDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.List;

public class SoccerDAO {
    private static final String GET_ALL_SOCCER_TEAMS_FOR_LEAGUE_API_URL =
            "https://v3.football.api-sports.io/teams?league=%s&season=%s";
    private static final String GET_ALL_MATCHES_FOR_TEAM_API_URL =
            "https://v3.football.api-sports.io/fixtures?league=%s&season=%s&team=%s&from=%s&to=%s";
    private static final String API_KEY = "fake-api-key";
    private static final String API_HOST = "v3.football.api-sports.io";
    private static final String SEASON_YEAR = "2023";
    // Logger declaration
    private static final Logger log = LoggerFactory.getLogger(SoccerDAO.class);

    private final CloseableHttpClient httpClient;
    private final SoccerDeserializer soccerDeserializer;

    @Inject
    public SoccerDAO(
            final CloseableHttpClient httpClient,
            final SoccerDeserializer soccerDeserializer) {
        this.httpClient = httpClient;
        this.soccerDeserializer = soccerDeserializer;
    }

    public List<Team> getAllTeamsForLeague(final String leagueId) {

        // Set up the GET request
        final HttpGet getRequest =
                new HttpGet(String.format(
                        GET_ALL_SOCCER_TEAMS_FOR_LEAGUE_API_URL, leagueId, SEASON_YEAR));
        setHttpHeader(getRequest);

        // Make the call
        try {
            CloseableHttpResponse response = httpClient.execute(getRequest);
            String responseBody = EntityUtils.toString(response.getEntity());
            log.info("Response: {}", responseBody);
            return soccerDeserializer.deserializeTeams(responseBody);
        } catch (final Exception e) {
            log.error("Failed to retrieve all teams from Soccer API..", e);
            return List.of();
        }
    }

    public List<Match> getAllMatchesForTeam(
            final String teamId, final String leagueId) {

        // Set up the GET request
        final LocalDate fromDate = LocalDate.now();
        final LocalDate toDate = fromDate.plusDays(7);
        final HttpGet getRequest =
                new HttpGet(String.format(
                        GET_ALL_MATCHES_FOR_TEAM_API_URL,
                        leagueId,
                        SEASON_YEAR,
                        teamId,
                        fromDate,
                        toDate));
        setHttpHeader(getRequest);

        try {
            // Make the call
            CloseableHttpResponse response = httpClient.execute(getRequest);
            String responseBody = EntityUtils.toString(response.getEntity());
            log.info("Response: {}", responseBody);
            return soccerDeserializer.deserializeMatches(responseBody);
        } catch (final Exception e) {
            log.error("Failed to retrieve matches for leagueId: {} and teamId: {}", leagueId, teamId, e);
            return List.of();
        }
    }

    private void setHttpHeader(final HttpGet getRequest) {
        getRequest.setHeader("x-rapidapi-key", API_KEY);
        getRequest.setHeader("x-rapidapi-host", API_HOST);
    }
}
