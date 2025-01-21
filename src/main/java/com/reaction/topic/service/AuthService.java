package com.reaction.topic.service;

import com.reaction.topic.model.dto.request.LoginDto;
import com.reaction.topic.model.dto.response.ResponseDto;

public interface AuthService {

    ResponseDto doLogin(LoginDto login);
}
