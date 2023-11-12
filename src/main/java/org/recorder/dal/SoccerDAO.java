package org.recorder.dal;

import lombok.extern.slf4j.Slf4j;
import org.apache.hc.client5.http.HttpResponseException;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.core5.http.HttpStatus;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;

import javax.inject.Inject;
import java.io.IOException;

@Slf4j
public class SoccerDAO {
    private static final String GET_ALL_SOCCER_TEAMS_FOR_LEAGUE_API_URL =
            "https://v3.football.api-sports.io/teams?league=%s&season=%s";
    private static final String GET_ALL_MATCHES_FOR_TEAM_API_URL =
            "https://v3.football.api-sports.io/fixtures?league=%s&season=%s&team=%s";
    private static final String API_KEY = "fake-api-key";
    private static final String API_HOST = "v3.football.api-sports.io";
    private static final String SEASON_YEAR = "2023";

    private final CloseableHttpClient httpClient;

    @Inject
    public SoccerDAO(
            final CloseableHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public String getAllTeamsForLeagues(final String leagueId)
            throws IOException, ParseException {

        // Set up the GET request
        final HttpGet getRequest =
                new HttpGet(String.format(
                        GET_ALL_SOCCER_TEAMS_FOR_LEAGUE_API_URL, leagueId, SEASON_YEAR));
        setHttpHeader(getRequest);

        // Make the call
        CloseableHttpResponse response = httpClient.execute(getRequest);

        // TODO: Need to find a way to serialize the JSON respond into a POJO classes.
        // Check if the response status code is OK (200)
        if (response.getCode() == HttpStatus.SC_OK) {
            // Read and print the response content
            String responseBody = EntityUtils.toString(response.getEntity());
            System.out.println("Response:\n" + responseBody);
            return responseBody;
        } else {
            // Handle non-OK response
            throw new HttpResponseException(response.getCode(), response.getReasonPhrase());
        }
    }

    public String getAllMatchesForTeam(final String teamId, final String leagueId)
            throws IOException, ParseException {

        // Set up the GET request
        final HttpGet getRequest =
                new HttpGet(String.format(
                        GET_ALL_MATCHES_FOR_TEAM_API_URL, teamId, leagueId, SEASON_YEAR));
        setHttpHeader(getRequest);

        // Make the call
        CloseableHttpResponse response = httpClient.execute(getRequest);

        // Check if the response status code is OK (200)
        if (response.getCode() == HttpStatus.SC_OK) {
            // Read and print the response content
            String responseBody = EntityUtils.toString(response.getEntity());
            System.out.println("Response:\n" + responseBody);
            return responseBody;
        } else {
            // Handle non-OK response
            throw new HttpResponseException(response.getCode(), response.getReasonPhrase());
        }
    }

    private void setHttpHeader(final HttpGet getRequest) {
        getRequest.setHeader("x-rapidapi-key", API_KEY);
        getRequest.setHeader("x-rapidapi-host", API_HOST);
    }


    // ALL_SOCCER_TEAMS [ UniqueID | isFavorite | TeamName | TeamID | League ]
    // Record ALL teams from supported league.
    // League: (LeagueOne, LaLiga, PremierLeague)
    // Cups: (ChampionsLeague)

    // FAVORITE_TEAM_MATCHES [ UniqueId | Date&Time | TeamName | TeamId | League | Against ]

    // => getFavoriteTeams()
    // => cleanTeamsTable()
    // => recordAllTeams()
    // => getMatchesForFavoriteTeams()
}
