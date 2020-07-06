package com.example.mobileappws.userservice.impl;

import com.example.mobileappws.shared.Util;
import com.example.mobileappws.ui.model.request.UserDetailsRequest;
import com.example.mobileappws.ui.model.response.UserDetailsResponse;
import com.example.mobileappws.userservice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    private Map<String, UserDetailsResponse> newUserMap;
    private Util util;

    @Autowired
    public UserServiceImpl(Util util) {
        this.util = util;
    }

    @Override
    public UserDetailsResponse createUser(UserDetailsRequest userDetailsRequest) {
        if (newUserMap == null) newUserMap = new HashMap<>();

        UserDetailsResponse usr = new UserDetailsResponse(
                userDetailsRequest.getFirstName(),
                userDetailsRequest.getLastName(),
                userDetailsRequest.getEmail(), util.generateUUID()
        );
        newUserMap.put(usr.getUserId(), usr);
        return usr;

    }
}
