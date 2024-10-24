package com.cooksys.TwitterMockupAPI.services.impl;

import com.cooksys.TwitterMockupAPI.dtos.CredentialsDto;
import com.cooksys.TwitterMockupAPI.dtos.TweetRequestDto;
import com.cooksys.TwitterMockupAPI.dtos.UserRequestDto;
import com.cooksys.TwitterMockupAPI.dtos.UserResponseDto;
import com.cooksys.TwitterMockupAPI.entities.User;
import com.cooksys.TwitterMockupAPI.entities.embeddables.Credentials;
import com.cooksys.TwitterMockupAPI.entities.embeddables.Profile;
import com.cooksys.TwitterMockupAPI.exceptions.BadRequestException;
import com.cooksys.TwitterMockupAPI.exceptions.NotFoundException;
import com.cooksys.TwitterMockupAPI.mappers.CredentialsMapper;
import com.cooksys.TwitterMockupAPI.mappers.ProfileMapper;
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
    private final ProfileMapper profileMapper;

    @Override
    public List<UserResponseDto> getAllActiveUsers(){
        List<User> users = userRepository.getActiveUsers();

        return userMapper.entitiesToResponseDtos(users);
    }

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

@Override
public UserResponseDto updateUser(String username, UserRequestDto userRequestDto){

        Optional<User> optionalUser = userRepository.findByCredentialsUsername(username);

   if(optionalUser.isEmpty() || optionalUser.get().isDeleted()){
       throw new NotFoundException("User does not exist or is deleted.");
   }

   User existingUser = optionalUser.get();

    if (userRequestDto.getCredentials() == null ||
            !userRequestDto.getCredentials().getUsername().equals(existingUser.getCredentials().getUsername()) ||
            !userRequestDto.getCredentials().getPassword().equals(existingUser.getCredentials().getPassword())) {
        throw new BadRequestException("Credentials do not match.");
    }


   Profile updatedProfile = profileMapper.dtoToEntity(userRequestDto.getProfile());
   existingUser.setProfile(updatedProfile);

   User updatedUser = userRepository.save(existingUser);
   return userMapper.entityToDto(updatedUser);
}

@Override
public UserResponseDto deleteUser(String username, CredentialsDto credentialsDto){

    Optional<User> optionalUser = userRepository.findByCredentialsUsername(credentialsDto.getUsername());

    if(optionalUser.isEmpty() || optionalUser.get().isDeleted()){
        throw new NotFoundException("User does not exist");
    }

    if(!credentialsDto.getPassword().equals(optionalUser.get().getCredentials().getPassword())){
        throw new BadRequestException("Credentials do not match");
    }

    User toDeleteUser = optionalUser.get();
    toDeleteUser.setDeleted(true);
    User deleteUser = userRepository.save(toDeleteUser);
    return userMapper.entityToDto(deleteUser);
}

@Override
public UserResponseDto followUser(String username, CredentialsDto credentialsDto){

Optional<User> optionalUser = userRepository.findByCredentialsUsername(credentialsDto.getUsername());

if(optionalUser.isEmpty() || optionalUser.get().isDeleted()){
    throw new NotFoundException("User does not exist");
}

if(!credentialsDto.getPassword().equals(optionalUser.get().getCredentials().getPassword())){
        throw new BadRequestException("Credentials do not match");
    }

Optional<User> userToFollow = userRepository.findByCredentialsUsername(username);

if(userToFollow.isEmpty() || userToFollow.get().isDeleted()){
    throw new NotFoundException("Can't follow non-existent user");
}

User follower = optionalUser.get();
User followingThisPerson= userToFollow.get();

if(follower.getFollowing().contains(followingThisPerson)){
    throw new BadRequestException("Already following");
}

follower.getFollowers().add(followingThisPerson);
followingThisPerson.getFollowers().add(follower);
userRepository.save(follower);
userRepository.save(followingThisPerson);

return userMapper.entityToDto(follower);

}

//    @Override
//    public List<TweetResponseDto> getFeed(String username){
//
//    }

    //@Override
//public List<UserResponseDto> getUserTweets(String username){
//
//return userMapper.entityToDto();
//}


}
