package com.likebook.model.view;

import com.likebook.model.enumerated.MoodEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Set;

@Getter
@Setter
@Accessors(chain=true)
public class PostView {
    private String username;

    private MoodEnum mood;

    private String content;

    private Set<UserView> userLikes;

    private Long id;
}
