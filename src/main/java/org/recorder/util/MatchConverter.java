package org.recorder.util;

import org.recorder.domain.db.MatchDBModel;
import org.recorder.domain.soccer.Match;

import java.time.Instant;
import java.util.UUID;

public class MatchConverter {

    public static MatchDBModel convertToDBModelMatch(
            final Match soccerMatch) {
        return new MatchDBModel(
                UUID.randomUUID().toString(),
                Instant.ofEpochMilli(soccerMatch.getFixture().getTimestamp()),
                soccerMatch.getTeams().getHome().getName(),
                soccerMatch.getTeams().getHome().getId(),
                soccerMatch.getLeague().getName(),
                soccerMatch.getLeague().getId(),
                soccerMatch.getTeams().getAway().getId(),
                soccerMatch.getTeams().getAway().getName());
    }
}
