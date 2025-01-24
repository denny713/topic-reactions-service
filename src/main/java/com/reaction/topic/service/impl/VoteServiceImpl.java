package com.reaction.topic.service.impl;

import com.reaction.topic.exception.BadRequestException;
import com.reaction.topic.exception.ForbiddenException;
import com.reaction.topic.exception.ServiceException;
import com.reaction.topic.model.dto.request.VoteSubmitDto;
import com.reaction.topic.model.dto.response.ResponseDto;
import com.reaction.topic.model.entity.Topic;
import com.reaction.topic.model.entity.Vote;
import com.reaction.topic.repository.TopicRepository;
import com.reaction.topic.repository.VoteRepository;
import com.reaction.topic.service.VoteService;
import com.reaction.topic.util.AccountUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class VoteServiceImpl implements VoteService {

    private final VoteRepository voteRepository;
    private final TopicRepository topicRepository;

    @Override
    @Transactional
    public ResponseDto doSubmitVote(VoteSubmitDto vote) {

        if (vote.getTopicId() == null) {
            throw new BadRequestException("Topic id cannot be null or empty");
        }

        if (vote.getIsLike() == null) {
            throw new BadRequestException("Like activity cannot be null or empty");
        }

        LocalDate today = LocalDate.now();
        long voteToday = voteRepository.countByCreatedByAndCreatedDateBetween(
                AccountUtil.getCurrentAccountEmail(),
                today.atStartOfDay(),
                today.atTime(23, 59, 59)
        );

        if (voteToday == 5) {
            throw new ForbiddenException("You have reached the maximum number of rates for today");
        }

        Topic topic = topicRepository.findById(vote.getTopicId()).orElse(null);
        if (topic == null) {
            throw new BadRequestException("Topic not found");
        }

        try {
            Vote savedVote = voteRepository.findByCreatedByAndTopic(AccountUtil.getCurrentAccountEmail(), topic);
            if (savedVote == null) {
                savedVote = new Vote();
                savedVote.setTopic(topic);
            }

            if (Boolean.FALSE.equals(vote.getIsLike())) {
                savedVote.setLikeVote(false);
                savedVote.setDislikeVote(true);
            } else {
                savedVote.setLikeVote(true);
                savedVote.setDislikeVote(false);
            }

            voteRepository.save(savedVote);

            return new ResponseDto(201, "Success", savedVote);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }
}
