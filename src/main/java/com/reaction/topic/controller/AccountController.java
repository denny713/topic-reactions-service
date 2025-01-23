package com.reaction.topic.controller;

import com.reaction.topic.model.dto.request.RegisterDto;
import com.reaction.topic.model.dto.response.ResponseDto;
import com.reaction.topic.service.AccountService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
@AllArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<ResponseDto> register(@Valid @RequestBody RegisterDto registerDto) {
        return ResponseEntity.ok(accountService.doRegister(registerDto));
    }
}
