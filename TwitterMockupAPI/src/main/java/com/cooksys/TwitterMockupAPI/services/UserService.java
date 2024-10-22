package com.cooksys.TwitterMockupAPI.services;

import com.cooksys.TwitterMockupAPI.dtos.UserRequestDto;
import com.cooksys.TwitterMockupAPI.dtos.UserResponseDto;

import java.util.List;

public interface UserService {

   List<UserResponseDto> getAllActiveUsers();

   UserResponseDto createUser(UserRequestDto userRequestDto);

   UserResponseDto getSpecificUser();
}
