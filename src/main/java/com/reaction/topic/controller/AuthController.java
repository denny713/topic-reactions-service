package com.reaction.topic.controller;

import com.reaction.topic.model.dto.request.LoginDto;
import com.reaction.topic.model.dto.response.ResponseDto;
import com.reaction.topic.service.AuthService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<ResponseDto> doLogin(@Valid @RequestBody LoginDto login) {
        return ResponseEntity.ok(authService.doLogin(login));
    }
}
