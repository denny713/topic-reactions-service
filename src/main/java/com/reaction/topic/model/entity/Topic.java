package com.reaction.topic.model.entity;

import com.reaction.topic.model.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "topic")
public class Topic extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "topic_id")
    private Long topicId;

    @Column(name = "title", nullable = false, length = 35)
    private String title;

    @Column(name = "description", nullable = false, length = 255)
    private String description;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "topic")
    private List<Vote> votes;
}
