package com.reaction.topic.util;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;

public class TokenUtil {

    private TokenUtil() {
        super();
    }

    public static String getTokenFromHeader(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        return StringUtils.isEmpty(authorization) ? null : authorization.replace("Bearer ", "");
    }
}
