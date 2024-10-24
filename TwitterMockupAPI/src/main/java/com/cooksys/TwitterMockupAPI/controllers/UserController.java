package com.cooksys.TwitterMockupAPI.controllers;

import com.cooksys.TwitterMockupAPI.dtos.UserRequestDto;
import com.cooksys.TwitterMockupAPI.dtos.UserResponseDto;
import com.cooksys.TwitterMockupAPI.entities.User;
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
    public UserResponseDto deleteUser(@PathVariable String username){
        return userService.deleteUser(username);
        }


//    @PostMapping("/{username}/follow"){
//
//    }
//
//    @PostMapping("/{username}/unfollow"){
//
//    }
//
//    @GetMapping("/{username}/feed"){
//
//    }
//
//    @GetMapping("/{username}/tweets"){
//
//    }
//
//    @GetMapping("/{username}/mentions"){
//
//    }
//
//    @GetMapping("/{username}/followers"){
//
//    }
//
//    @GetMapping("/{username}/following"){
//
//    }

}
