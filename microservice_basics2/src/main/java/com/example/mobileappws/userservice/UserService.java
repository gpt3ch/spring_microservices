package com.example.mobileappws.userservice;

import com.example.mobileappws.ui.model.request.UserDetailsRequest;
import com.example.mobileappws.ui.model.response.UserDetailsResponse;
import org.springframework.stereotype.Service;

public interface UserService {
    public UserDetailsResponse createUser(UserDetailsRequest userDetailsRequest);
}
