package com.reaction.topic.model.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VoteSubmitDto {

    private Long topicId;
    private Boolean isLike;
}
