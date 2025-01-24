package com.reaction.topic.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VoteDto {

    private Long voteId;
    private String title;
    private String description;
    private Boolean likeVote;
    private Boolean dislikeVote;
}
