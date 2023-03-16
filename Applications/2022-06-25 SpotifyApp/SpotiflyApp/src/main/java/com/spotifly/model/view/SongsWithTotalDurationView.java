package com.spotifly.model.view;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.text.DecimalFormat;
import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
public class SongsWithTotalDurationView {

    private final List<SongView> mySongs;
    private final Integer totDur;

    private final DecimalFormat df;

    public SongsWithTotalDurationView(List<SongView> mySongs) {
        this.mySongs = mySongs;

        df = new DecimalFormat();
        df.setMinimumIntegerDigits(2);


        totDur = mySongs.stream().mapToInt(SongView::getDuration).sum();
    }

    public String totalDur() {
        return String.format("%d:%s", totDur / SongView.MIN_IN_HOUR, df.format(totDur % SongView.MIN_IN_HOUR));
    }

}
