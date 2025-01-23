package com.reaction.topic.service;

import com.reaction.topic.model.dto.request.AddTopicDto;
import com.reaction.topic.model.dto.response.ResponseDto;

public interface TopicService {

    ResponseDto doSubmitTopic(AddTopicDto topic);
}
