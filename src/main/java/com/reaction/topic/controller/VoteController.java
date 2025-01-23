package com.reaction.topic.controller;

import com.reaction.topic.model.dto.request.VoteSubmitDto;
import com.reaction.topic.model.dto.response.ResponseDto;
import com.reaction.topic.service.VoteService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vote")
@AllArgsConstructor
public class VoteController {

    private final VoteService voteService;

    @PostMapping
    public ResponseEntity<ResponseDto> vote(@Valid @RequestBody VoteSubmitDto vote) {
        return ResponseEntity.ok(voteService.doSubmitVote(vote));
    }
}
