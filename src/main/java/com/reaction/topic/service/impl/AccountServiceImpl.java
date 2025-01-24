package com.reaction.topic.service.impl;

import com.reaction.topic.exception.BadRequestException;
import com.reaction.topic.model.dto.request.RegisterDto;
import com.reaction.topic.model.dto.response.ResponseDto;
import com.reaction.topic.model.entity.Account;
import com.reaction.topic.repository.AccountRepository;
import com.reaction.topic.service.AccountService;
import com.reaction.topic.util.EncryptUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.SerializationException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

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

            return new ResponseDto(201, "Success", savedAccount);
        } catch (Exception e) {
            throw new SerializationException(e.getMessage());
        }
    }
}
