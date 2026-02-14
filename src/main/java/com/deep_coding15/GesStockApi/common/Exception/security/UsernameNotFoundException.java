package com.deep_coding15.GesStockApi.common.exception.security;

import com.deep_coding15.GesStockApi.common.exception.BusinessException;

public class UsernameNotFoundException extends BusinessException {

    public UsernameNotFoundException(String username) {
        super("User not found with name: " + username, "USERNAME_NOT_FOUND_EXCEPTION");
    }
}