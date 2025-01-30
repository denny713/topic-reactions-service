package com.reaction.topic.service;

import com.reaction.topic.model.dto.request.RegisterDto;
import com.reaction.topic.model.dto.response.ResponseDto;
import jakarta.servlet.http.HttpServletRequest;

public interface AccountService {

    ResponseDto doRegister(RegisterDto account);

    ResponseDto detail(HttpServletRequest request);
}
