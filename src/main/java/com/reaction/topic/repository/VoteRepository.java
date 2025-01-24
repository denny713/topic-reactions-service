package com.reaction.topic.repository;

import com.reaction.topic.model.entity.Topic;
import com.reaction.topic.model.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {

    Vote findByCreatedByAndTopic(String email, Topic topic);

    long countByCreatedByAndCreatedDateBetween(String email, LocalDateTime startDate, LocalDateTime endDate);
}
