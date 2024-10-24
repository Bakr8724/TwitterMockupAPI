package com.cooksys.TwitterMockupAPI.controllers;

import com.cooksys.TwitterMockupAPI.dtos.*;
import com.cooksys.TwitterMockupAPI.entities.User;
import com.cooksys.TwitterMockupAPI.entities.embeddables.Credentials;
import com.cooksys.TwitterMockupAPI.mappers.UserMapper;
import com.cooksys.TwitterMockupAPI.repositories.UserRepository;
import com.cooksys.TwitterMockupAPI.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<UserResponseDto> getAllUsers() {
        return userService.getAllActiveUsers();
    }

    @PostMapping
    public UserResponseDto createUser(@RequestBody UserRequestDto userRequestDto) {
        return userService.createUser(userRequestDto);
    }

    @PatchMapping("/{username}")
    public UserResponseDto updateUser(@PathVariable String username, @RequestBody UserRequestDto userRequestDto){
        return userService.updateUser(username, userRequestDto);

    }

    @DeleteMapping("/{username}")
    public UserResponseDto deleteUser(@PathVariable String username, CredentialsDto credentialsDto){
        return userService.deleteUser(username, credentialsDto);
        }


    @PostMapping("/{username}/follow")
    public UserResponseDto followUser(@PathVariable String username, @RequestBody CredentialsDto credentialsDto){
        return userService.followUser(username, credentialsDto);
    }

//    @GetMapping("/{username}/feed")
//    public List<TweetResponseDto> getFeed(@PathVariable String username){
//    return tweetService.getFeed(username);
//    }

    //    @GetMapping("/{username}/tweets")
//    public List<UserResponseDto> getUserTweets(@PathVariable String username){
//        return userService.getUserTweets(username);
//    }
//
}
