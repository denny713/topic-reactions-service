package com.reaction.topic.controller;

import com.reaction.topic.model.dto.request.AddTopicDto;
import com.reaction.topic.model.dto.response.ResponseDto;
import com.reaction.topic.service.TopicService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/topic")
@AllArgsConstructor
public class TopicController {

    private final TopicService topicService;

    @PostMapping
    public ResponseEntity<ResponseDto> createTopic(@Valid @RequestBody AddTopicDto topic) {
        return ResponseEntity.ok(topicService.doSubmitTopic(topic));
    }
}
