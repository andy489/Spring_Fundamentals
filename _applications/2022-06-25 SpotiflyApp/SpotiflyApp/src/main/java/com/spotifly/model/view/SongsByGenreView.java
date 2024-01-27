package com.spotifly.model.view;

import com.spotifly.model.enumerated.StyleEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.EnumMap;
import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
@ToString
public class SongsByGenreView {

    private EnumMap<StyleEnum, List<SongView>> songsEnumMap = new EnumMap<>(StyleEnum.class);

}

