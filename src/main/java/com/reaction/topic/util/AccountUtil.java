package com.reaction.topic.util;

import com.reaction.topic.model.entity.Account;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class AccountUtil {

    public AccountUtil() {
        super();
    }

    public static String getCurrentAccountEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            Account user = (Account) authentication.getPrincipal();
            return user.getEmail();
        }

        return "System";
    }
}
