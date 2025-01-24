package com.reaction.topic.service.impl;

import com.reaction.topic.exception.ForbiddenException;
import com.reaction.topic.exception.NotFoundException;
import com.reaction.topic.model.dto.request.LoginDto;
import com.reaction.topic.model.dto.response.AuthDto;
import com.reaction.topic.model.dto.response.ResponseDto;
import com.reaction.topic.model.entity.Account;
import com.reaction.topic.repository.AccountRepository;
import com.reaction.topic.service.AuthService;
import com.reaction.topic.token.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final AccountRepository accountRepository;
    private final JwtService jwtService;

    @Override
    @Transactional
    public ResponseDto doLogin(LoginDto login) {
        login.setEmail(login.getEmail().toLowerCase());
        Account account = accountRepository.findByEmail(login.getEmail())
                .orElseThrow(() -> new NotFoundException("Email does not exist"));

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword())
            );

            if (!authentication.isAuthenticated()) {
                throw new ForbiddenException("Incorrect password");
            }
        } catch (Exception e) {
            throw new ForbiddenException("Incorrect password");
        }

        AuthDto result = new AuthDto(
                jwtService.generateAccessToken(account),
                "Bearer",
                3600,
                jwtService.generateRefreshToken(account)
        );

        return new ResponseDto(200, "Success", result);
    }
}
