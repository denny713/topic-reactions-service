package com.reaction.topic.service;

import com.reaction.topic.model.dto.request.VoteSubmitDto;
import com.reaction.topic.model.dto.response.ResponseDto;

public interface VoteService {

    ResponseDto doSubmitVote(VoteSubmitDto vote);
}
