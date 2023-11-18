package org.recorder.domain.internal;

import lombok.Builder;

import java.time.Instant;

@Builder
public class Match1 {
    private String uniqueId;
    private Instant matchDateAndTime;
    private String teamName;
    private String teamId;
    private String LeagueName;
    private String leagueId;
    private String againstTeam;
}
