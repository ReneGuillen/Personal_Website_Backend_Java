package util;

import org.junit.jupiter.api.Test;
import org.recorder.domain.db.TeamDBModel;
import org.recorder.domain.soccer.Team;
import org.recorder.util.TeamConverter;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TeamConverterTest {

    private static final Team SOCCER_TEAM =
            Team.builder()
                    .name("PSG")
                    .id(7)
                    .isFavorite(true)
                    .leagueId("30")
                    .build();

    private static final TeamDBModel DB_MODEL_TEAM =
            new TeamDBModel(
                    UUID.randomUUID().toString(),
                    true,
                    "PSG",
                    7,
                    "30"
            );

    @Test
    public void convertToSoccerTeam_givenValidDBModelTeam_convertsToSoccerTeam() {
        // GIVEN

        // WHEN
        final Team output = TeamConverter.convertToSoccerTeam(DB_MODEL_TEAM);

        // THEN
        assertEquals(SOCCER_TEAM, output);
    }

    @Test
    public void convertToDBModelTeam_givenValidSoccerTeam_convertsToDBModelTeam() {
        // GIVEN

        // WHEN
        final TeamDBModel output = TeamConverter.convertToDBModelTeam(SOCCER_TEAM);

        // THEN
        DB_MODEL_TEAM.setUniqueId(output.getUniqueId());
        assertEquals(DB_MODEL_TEAM, output);
    }
}
