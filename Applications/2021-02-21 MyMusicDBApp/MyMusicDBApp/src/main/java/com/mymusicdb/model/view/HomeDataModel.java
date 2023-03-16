package com.mymusicdb.model.view;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
public class HomeDataModel {

    private List<AlbumView> albums;

}
