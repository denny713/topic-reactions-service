package com.reaction.topic.service.impl;

import com.reaction.topic.exception.BadRequestException;
import com.reaction.topic.exception.ServiceException;
import com.reaction.topic.model.dto.request.TopicSubmitDto;
import com.reaction.topic.model.dto.response.ResponseDto;
import com.reaction.topic.model.dto.response.TopicDto;
import com.reaction.topic.model.entity.Topic;
import com.reaction.topic.model.entity.Vote;
import com.reaction.topic.repository.TopicRepository;
import com.reaction.topic.service.TopicService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TopicServiceImpl implements TopicService {

    private final TopicRepository topicRepository;

    @Override
    @Transactional
    public ResponseDto doSubmitTopic(TopicSubmitDto topic) {

        if (StringUtils.isEmpty(topic.getTitle())) {
            throw new BadRequestException("Title cannot be null or empty");
        }

        if (StringUtils.isEmpty(topic.getDescription())) {
            throw new BadRequestException("Description cannot be null or empty");
        }

        try {
            Topic savedTopic = new Topic();
            BeanUtils.copyProperties(topic, savedTopic);
            topicRepository.save(savedTopic);

            return new ResponseDto(201, "Success", savedTopic);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public ResponseDto doGetListTopic() {
        try {
            List<Topic> topics = topicRepository.findAll();
            List<TopicDto> topicDtos = new ArrayList<>();

            topics.forEach(it -> topicDtos.add(new TopicDto(
                    it.getTopicId(),
                    it.getTitle(),
                    it.getDescription(),
                    it.getCreatedBy(),
                    it.getVotes().size(),
                    (int) ((it.getVotes().stream().filter(Vote::getLikeVote).count() / it.getVotes().size()) * 100),
                    (int) ((it.getVotes().stream().filter(Vote::getDislikeVote).count() / it.getVotes().size()) * 100)
            )));

            return new ResponseDto(200, "Success", topicDtos);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }
}
