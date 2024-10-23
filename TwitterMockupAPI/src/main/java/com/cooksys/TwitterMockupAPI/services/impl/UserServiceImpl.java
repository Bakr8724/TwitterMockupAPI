package com.cooksys.TwitterMockupAPI.services.impl;

import com.cooksys.TwitterMockupAPI.dtos.UserRequestDto;
import com.cooksys.TwitterMockupAPI.dtos.UserResponseDto;
import com.cooksys.TwitterMockupAPI.entities.User;
import com.cooksys.TwitterMockupAPI.entities.embeddables.Credentials;
import com.cooksys.TwitterMockupAPI.exceptions.BadRequestException;
import com.cooksys.TwitterMockupAPI.mappers.CredentialsMapper;
import com.cooksys.TwitterMockupAPI.mappers.UserMapper;
import com.cooksys.TwitterMockupAPI.repositories.UserRepository;
import com.cooksys.TwitterMockupAPI.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final CredentialsMapper credentialsMapper;

//    @Override
//List<UserResponseDto> getAllActiveUsers(){
//        throw new UnsupportedOperationException("Unimplemented method");
//    }

@Override
public UserResponseDto createUser(UserRequestDto userRequestDto){

    if(userRequestDto.getProfile() == null || userRequestDto.getCredentials() == null){
        throw new BadRequestException("Profile or Credentials cannot be null");
    }

    if(userRequestDto.getCredentials().getUsername() == null ||
            userRequestDto.getCredentials().getPassword() == null ||
            userRequestDto.getProfile().getEmail() == null ||
            userRequestDto.getCredentials().getPassword().equals("")){
        throw new BadRequestException("Username, password, or email cannot be null");
    }

    User createdUser = new User();
//    Credentials userCreds = new Credentials();
//    userCreds.setUsername(userRequestDto.getCredentials().getUsername());
//    userCreds.setPassword(userRequestDto.getCredentials().getPassword());
    Optional<User> existingUser = userRepository.findByCredentials(credentialsMapper.dtoToEntity(userRequestDto.getCredentials()));

    if(existingUser.isPresent()){
        createdUser = existingUser.get();
        createdUser.setDeleted(false);
    } else{
        createdUser = userMapper.requestDtoToEntity(userRequestDto);
    }
    userRepository.saveAndFlush(createdUser);
    return userMapper.entityToDto(createdUser);
}

//@Override
//UserResponseDto getSpecificUser(){
//    throw new UnsupportedOperationException("Unimplemented method");;
//}

}
