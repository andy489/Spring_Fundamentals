package com.likebook.mapper;

import com.likebook.model.dto.PostAddDto;
import com.likebook.model.dto.UserRegisterDto;
import com.likebook.model.entity.PostEntity;
import com.likebook.model.entity.UserEntity;
import com.likebook.model.view.PostView;
import com.likebook.model.view.UserView;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface MapStructMapper {

    UserEntity toUserEntity(UserRegisterDto userRegisterDto);

    @Mapping(target="mood", ignore = true)
    PostEntity toPostEntity(PostAddDto postAddDto);

    @Mapping(target="mood", ignore = true)
    PostView toPostView(PostEntity postEntity);

    UserView toUserView(UserEntity userEntity);

    default PostView toPostViewFullMap(PostEntity postEntity){
        PostView postView = this.toPostView(postEntity);

        postView.setMood(postEntity.getMood().getName());

        postView.setUserLikes(postEntity.getLikes().stream().map(this::toUserView).collect(Collectors.toSet()));

        postView.setUsername(postEntity.getCreator().getUsername());

        return postView;
    }
}

