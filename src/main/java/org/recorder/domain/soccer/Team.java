package org.recorder.domain.soccer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class Team {
    private int id;
    private String name;
    private boolean isFavorite;
    private String leagueId;
}
