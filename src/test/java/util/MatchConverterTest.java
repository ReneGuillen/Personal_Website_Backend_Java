package util;

import org.junit.jupiter.api.Test;
import org.recorder.domain.db.MatchDBModel;
import org.recorder.domain.soccer.*;
import org.recorder.util.MatchConverter;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MatchConverterTest {

    public static final Match SOCCER_MATCH =
            Match.builder()
                    .fixture(Fixture.builder()
                            .timestamp(1700426682)
                            .build())
                    .league(League.builder()
                            .id(30)
                            .name("LaLiga")
                            .build())
                    .teams(Teams.builder()
                            .home(Team.builder()
                                    .id(7)
                                    .name("PSG")
                                    .build())
                            .away(Team.builder()
                                    .id(10)
                                    .name("Real Madrid")
                                    .build())
                            .build())
                    .build();

    public static final MatchDBModel MATCH_DB_MODEL =
            MatchDBModel.builder()
                    .matchDateAndTime(Instant.ofEpochMilli(1700426682))
                    .teamName("PSG")
                    .teamId(7)
                    .leagueName("LaLiga")
                    .leagueId(30)
                    .againstTeamId(10)
                    .againstTeamName("Real Madrid")
                    .build();

    @Test
    public void convertToDBModelMatch_givenValidSoccerMatch_convertsToDBModelMatch() {
        // GIVEN

        // WHEN
        final MatchDBModel output = MatchConverter.convertToDBModelMatch(SOCCER_MATCH);

        // THEN
        MATCH_DB_MODEL.setUniqueId(output.getUniqueId());
        assertEquals(MATCH_DB_MODEL, output);
    }
}
