package com.example.mobileappws.shared;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class Util {
    public String generateUUID(){
        return UUID.randomUUID().toString();
    }
}
