package com.reaction.topic.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TopicDto {

    private Long topicId;
    private String title;
    private String description;
    private String author;
    private Integer totalVote;
    private Integer likePercentage;
    private Integer dislikePercentage;
}
