package com.sparta.i_am_delivery.domain.comment.entity;

import com.sparta.i_am_delivery.common.entity.TimeStamped;
import com.sparta.i_am_delivery.domain.review.entity.Review;
import com.sparta.i_am_delivery.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "comments")
@NoArgsConstructor
public class Comment extends TimeStamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id", nullable = false)
    private Review review;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private String content;

    @Builder
    public Comment(Review review, User user, String content) {
        this.review = review;
        this.user = user;
        this.content = content;
    }
}
