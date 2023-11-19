package org.recorder.domain.soccer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class Fixture {
    private String timezone;
    private String date;
    private long timestamp;
}
