package com.reaction.topic.model.entity;

import com.reaction.topic.model.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "vote")
public class Vote extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vote_id")
    private Long voteId;

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "topic_id")
    private Topic topic;

    @Column(name = "like_vote", nullable = false)
    private Boolean likeVote;

    @Column(name = "dislike_vote", nullable = false)
    private Boolean dislikeVote;
}
