package com.spotify.model.view;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.text.DecimalFormat;

@Getter
@Setter
@Accessors(chain = true)
@ToString
public class SongView {
    protected static Integer MIN_IN_HOUR = 60;

    private final DecimalFormat df;

    private Long id;

    private String performer;

    private String title;

    private Integer duration;

    public SongView() {
        df = new DecimalFormat();
        df.setMinimumIntegerDigits(2);
    }

    public String dur() {
        return String.format("%d:%s", duration / MIN_IN_HOUR, df.format(duration % MIN_IN_HOUR));
    }

}
