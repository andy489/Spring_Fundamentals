package com.likebook.service;

import com.likebook.mapper.MapStructMapper;
import com.likebook.model.dto.PostAddDto;
import com.likebook.model.entity.PostEntity;
import com.likebook.model.entity.UserEntity;
import com.likebook.model.view.PostModel;
import com.likebook.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final MapStructMapper mapper;
    private final UserService userService;
    private final MoodService moodService;

    @Autowired
    public PostService(
            PostRepository postRepository,
            MapStructMapper mapper,
            UserService userService,
            MoodService moodService
    ) {
        this.postRepository = postRepository;
        this.mapper = mapper;
        this.userService = userService;
        this.moodService = moodService;
    }

    public void addPost(PostAddDto postAddDto) {
        PostEntity postEntity = mapper.toPostEntity(postAddDto);

        postEntity.setMood(moodService.getByName(postAddDto.getMood()));
        postEntity.setCreator(userService.getCurrentUser());

        postRepository.saveAndFlush(postEntity);
    }

    public PostModel getData() {
        UserEntity currentUserEntity = userService.getCurrentUser();

        PostModel data = new PostModel();

        data.setMyPosts(postRepository.findAllByCreatorId(currentUserEntity.getId()).stream()
                .map(mapper::toPostViewFullMap).toList());

        data.setOtherPosts(postRepository.findPostsByCreatorIdNot(currentUserEntity.getId()).stream()
                .map(mapper::toPostViewFullMap).toList());

        return data;
    }

    public void removePostById(Long postId) {
        postRepository.deleteById(postId);
    }

    public void likePostWithId(Long postId) {
        PostEntity post = postRepository.findById(postId).orElseThrow(NoSuchElementException::new);

        UserEntity currUser = userService.getCurrentUser();

        post.like(currUser);


        postRepository.saveAndFlush(post);
    }
}
