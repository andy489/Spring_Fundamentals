package com.likebook.model.view;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
public class PostModel {

    private List<PostView> myPosts;

    private List<PostView> otherPosts;
}
