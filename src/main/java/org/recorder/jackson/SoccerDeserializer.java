package org.recorder.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.recorder.domain.soccer.Match;
import org.recorder.domain.soccer.Team;
import org.recorder.domain.soccer.TeamWrapper;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Stream;

@Slf4j
public class SoccerDeserializer {
    private final ObjectMapper objectMapper;

    @Inject
    public SoccerDeserializer(final ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public List<Match> deserializeMatches(final String jsonResponse)
            throws JsonProcessingException {
        JsonNode node = objectMapper.readTree(jsonResponse);
        JsonNode responseNode = node.get("response");
        return List.of(objectMapper.treeToValue(responseNode, Match[].class));
    }

    public List<Team> deserializeTeams(final String jsonResponse)
            throws JsonProcessingException {
        JsonNode node = objectMapper.readTree(jsonResponse);
        JsonNode responseNode = node.get("response");
        return Stream.of(objectMapper.treeToValue(responseNode, TeamWrapper[].class))
                .map(TeamWrapper::getTeam)
                .toList();
    }
}
