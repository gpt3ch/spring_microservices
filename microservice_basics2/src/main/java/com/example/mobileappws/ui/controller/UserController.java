package com.example.mobileappws.ui.controller;

import com.example.mobileappws.ui.model.request.UserDetailsRequest;
import com.example.mobileappws.ui.model.request.UserUpdateDetailsRequest;
import com.example.mobileappws.ui.model.response.UserDetailsResponse;
import com.example.mobileappws.userservice.UserService;
import com.example.mobileappws.userservice.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Stream;

@RestController
@RequestMapping(value = "users",
        produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
public class UserController {
    Map<String, UserDetailsResponse> newUserMap;

    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity getUsers(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "limit", defaultValue = "50") int limit) {
        if (!newUserMap.isEmpty()) {
            Stream<Map.Entry<String, UserDetailsResponse>> stream = newUserMap.entrySet().stream();
            return null;
        } else {
            return new ResponseEntity("No user found", HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping(path = "/{userId}")
    public ResponseEntity<UserDetailsResponse> getUser(@PathVariable("userId") String userId) {
        if (newUserMap.containsKey(userId)) {
            return new ResponseEntity(newUserMap.get(userId), HttpStatus.OK);
        } else {
            return new ResponseEntity("No user found", HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping(consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UserDetailsResponse> createUser(@Valid @RequestBody UserDetailsRequest userDetailsRequest) {
        return new ResponseEntity<>(userService.createUser(userDetailsRequest), HttpStatus.OK);
    }

    @PutMapping(path = "/{userId}")
    public ResponseEntity<UserDetailsResponse> updateUser(@Valid @RequestBody UserUpdateDetailsRequest userUpdateDetails,
                                                          @PathVariable("userId") String userId) {
        UserDetailsResponse usrDet = newUserMap.get(userId);
        usrDet.setFirstName(userUpdateDetails.getFirstName());
        usrDet.setLastName(userUpdateDetails.getLastName());
        newUserMap.put(userId, usrDet);
        return new ResponseEntity<>(usrDet, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable("userId") String userId) {
        newUserMap.remove(userId);
        return ResponseEntity.noContent().build();
    }
}
