package com.cooksys.TwitterMockupAPI.controllers;

import com.cooksys.TwitterMockupAPI.services.ValidateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/validate")
public class ValidateController {

    private final ValidateService validateService;

//    @GetMapping("/tag/exists/{label}")
//    public validateHashTag(){
//
//    }
//
//    @GetMapping("/username/exists/{username}")
//    public validateUserName(){
//
//    }
//
//    @GetMapping("/username/available/{username}")
//    public isUserNameAvailable(){
//
//    }


}
