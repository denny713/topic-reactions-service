package com.reaction.topic.service.impl;

import com.reaction.topic.exception.BadRequestException;
import com.reaction.topic.exception.NotFoundException;
import com.reaction.topic.model.dto.request.RegisterDto;
import com.reaction.topic.model.dto.response.AccountDto;
import com.reaction.topic.model.dto.response.ResponseDto;
import com.reaction.topic.model.entity.Account;
import com.reaction.topic.repository.AccountRepository;
import com.reaction.topic.service.AccountService;
import com.reaction.topic.token.JwtService;
import com.reaction.topic.util.EncryptUtil;
import com.reaction.topic.util.TokenUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.SerializationException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final JwtService jwtService;

    @Override
    @Transactional
    public ResponseDto doRegister(RegisterDto account) {

        if (StringUtils.isEmpty(account.getName())) {
            throw new BadRequestException("Name cannot be null or empty");
        }

        if (StringUtils.isEmpty(account.getEmail())) {
            throw new BadRequestException("Email cannot be null or empty");
        }

        if (StringUtils.isEmpty(account.getPassword())) {
            throw new BadRequestException("Password cannot be null or empty");
        }

        try {
            Account savedAccount = new Account();
            savedAccount.setEmail(account.getEmail());
            savedAccount.setPassword(EncryptUtil.encrypt(account.getPassword()));
            savedAccount.setFullName(account.getName());
            accountRepository.save(savedAccount);

            return new ResponseDto(201, "Success", new AccountDto(
                    savedAccount.getAccountId(),
                    savedAccount.getEmail(),
                    savedAccount.getFullName()
            ));
        } catch (Exception e) {
            throw new SerializationException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public ResponseDto detail(HttpServletRequest request) {
        String token = TokenUtil.getTokenFromHeader(request);
        if (StringUtils.isEmpty(token)) {
            throw new NotFoundException("Token not found");
        }

        Account account = accountRepository.findByEmail(jwtService.getUsername(token))
                .orElseThrow(() -> new NotFoundException("Account not found"));

        return new ResponseDto(200, "Success",
                new AccountDto(account.getAccountId(), account.getEmail(), account.getFullName()));
    }
}
