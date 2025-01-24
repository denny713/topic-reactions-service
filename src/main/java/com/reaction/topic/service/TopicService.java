package com.reaction.topic.service;

import com.reaction.topic.model.dto.request.TopicSubmitDto;
import com.reaction.topic.model.dto.response.ResponseDto;

public interface TopicService {

    ResponseDto doSubmitTopic(TopicSubmitDto topic);

    ResponseDto doGetListTopic();
}
