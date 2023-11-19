package org.recorder.util;

import org.recorder.domain.db.TeamDBModel;
import org.recorder.domain.soccer.Team;

import java.util.UUID;

public class TeamConverter {

    public static Team convertToSoccerTeam(final TeamDBModel teamDBModel) {
        return Team.builder()
                .id(teamDBModel.getTeamId())
                .name(teamDBModel.getTeamName())
                .isFavorite(teamDBModel.isFavorite())
                .build();
    }

    public static TeamDBModel convertToDBModelTeam(final Team team) {
        return new TeamDBModel(
                UUID.randomUUID().toString(),
                team.isFavorite(),
                team.getName(),
                team.getId(),
                team.getLeagueId()
        );
    }
}
