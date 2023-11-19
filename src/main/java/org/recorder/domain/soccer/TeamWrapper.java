package org.recorder.domain.soccer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class TeamWrapper {
    /** Wraps the team object for deserialization matching. **/
    private Team team;
}
