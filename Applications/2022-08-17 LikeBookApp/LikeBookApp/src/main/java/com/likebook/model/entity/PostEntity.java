package com.likebook.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.HashSet;
import java.util.Set;

@Entity(name = "posts")
@Getter
@Setter
@Accessors(chain = true)
@AllArgsConstructor(staticName = "of")
public class PostEntity extends BaseEntity {

    @Column(nullable = false)
    private String content;

    @ManyToOne
    private UserEntity creator;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "creator_id")
    private Set<UserEntity> likes;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "mood_id")
    private MoodEntity mood;

    public PostEntity() {
        likes = new HashSet<>();
    }

    public void like(UserEntity user) {
        if (creator.getId().equals(user.getId())) {
            return;
        }

        if (likes.contains(user)) {
            likes.remove(user);
            return;
        }

        likes.add(user);
    }
}
