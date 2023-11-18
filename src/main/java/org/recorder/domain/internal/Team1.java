package org.recorder.domain.internal;

import lombok.Builder;

@Builder
public class Team1 {
    private String uniqueId;
    private String name;
    private String teamId;
    private String leagueName;
    private String leagueId;
    private boolean isFavorite;
}
