package com.cooksys.TwitterMockupAPI.services.impl;

import com.cooksys.TwitterMockupAPI.dtos.*;
import com.cooksys.TwitterMockupAPI.entities.Tweet;
import com.cooksys.TwitterMockupAPI.entities.User;
import com.cooksys.TwitterMockupAPI.entities.embeddables.Credentials;
import com.cooksys.TwitterMockupAPI.exceptions.BadRequestException;
import com.cooksys.TwitterMockupAPI.exceptions.NotFoundException;
import com.cooksys.TwitterMockupAPI.mappers.CredentialsMapper;
import com.cooksys.TwitterMockupAPI.mappers.HashTagMapper;
import com.cooksys.TwitterMockupAPI.mappers.TweetMapper;
import com.cooksys.TwitterMockupAPI.mappers.UserMapper;
import com.cooksys.TwitterMockupAPI.repositories.TweetRepository;
import com.cooksys.TwitterMockupAPI.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import com.cooksys.TwitterMockupAPI.services.TweetService;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TweetServiceImpl implements TweetService {

    private TweetRepository tweetRepository;
    private TweetMapper tweetMapper;
    private UserRepository userRepository;
    private UserMapper userMapper;
    private CredentialsMapper credentialsMapper;
    private HashTagMapper hashTagMapper;

    private Tweet tweetId(Long id){
        Optional<Tweet> optionalTweet = tweetRepository.nonDeletedTweetsByID(id);

        if(optionalTweet.isEmpty()){
            throw new NotFoundException("Tweet not found or deleted with id: " + id);
        }

        return optionalTweet.get();
    }

    @Override
    public List<TweetResponseDto> getAllTweets() {
        List<Tweet> tweets = tweetRepository.nonDeletedTweets();

        return tweetMapper.entitiesToResponseDtos(tweets);
    }

    @Override
    public TweetResponseDto getTweetById(Long id) {
        Optional<Tweet> optionalTweet = tweetRepository.findById(id);

        if (optionalTweet.isEmpty()) {
            throw new NotFoundException("No user found with id: " + id);
        }
        return tweetMapper.entityToResponseDto(optionalTweet.get());
    }

    @Override
    public List<UserResponseDto> mentionedUsers(Long id) {

        List<User> users = userRepository.findMentions(id);

        if (users.isEmpty()) {
            throw new NotFoundException("Tweet not found");
        }

        return userMapper.entitiesToResponseDtos(users);
    }

    @Override
    public List<TweetResponseDto> getTweetReplies(Long id){

        Optional<Tweet> tweetRepliedTo = tweetRepository.nonDeletedTweetsByID(id);

        if(tweetRepliedTo.isEmpty() || tweetRepliedTo.get().isDeleted()){
            throw new NotFoundException("Tweet not found");
        }
        List<Tweet> tweets = tweetRepository.findByInReplyToIdAndDeletedFalse(id);

        return tweetMapper.entitiesToResponseDtos(tweets);
    }

    @Override
    public TweetResponseDto repostTweet(Long id, TweetRequestDto tweetRequestDto){
        Optional<Tweet> optionalTweet = tweetRepository.findById(id);

        if(optionalTweet.isEmpty() || optionalTweet.get().isDeleted()){
            throw new NotFoundException("Tweet not found");
        }

        Credentials credentials = credentialsMapper.dtoToEntity(tweetRequestDto.getCredentials());
        Optional<User> optionalUser = userRepository.findByCredentials(credentials);

        if(optionalUser.isEmpty()){
            throw new NotFoundException("User not found");
        }

        User user = optionalUser.get();
        Tweet repostTweet = new Tweet();
        repostTweet.setContent(null);
        repostTweet.setAuthor(user);
        repostTweet.setRepostOf(optionalTweet.get());

        tweetRepository.saveAndFlush(repostTweet);

        return tweetMapper.entityToResponseDto(repostTweet);
    }

    @Override
    public TweetResponseDto postTweet(TweetRequestDto tweetRequestDto) {
        //check if credentials match an active user
        Credentials credentials = credentialsMapper.dtoToEntity(tweetRequestDto.getCredentials());


        Optional<User> optionalUser = userRepository.findByCredentialsUsername(credentials.getUsername());
        if(optionalUser.isEmpty()){
            throw new NotFoundException("No active user found");
        }

        User author = optionalUser.get();

        //create the new tweet
        Tweet newTweet = new Tweet();
        newTweet.setAuthor(author);
        newTweet.setContent(tweetRequestDto.getContent());

        //save
        tweetRepository.saveAndFlush(newTweet);

        //get the mentions and hashtags

        //return
        return tweetMapper.entityToResponseDto(newTweet);
    }

    @Override
    public void postLike(Long id, CredentialsDto credentialsDto) {
        Tweet tweet =tweetId(id);

        //validate credentials
        Credentials credentials = credentialsMapper.dtoToEntity(credentialsDto);

        Optional<User> optionalUser = userRepository.findByCredentials(credentials);

        if(optionalUser.isEmpty()){
            throw new NotFoundException("no user found with this info");
        }

        User user = optionalUser.get();

        tweet.getLikedByUsers().add(user);
        tweetRepository.saveAndFlush(tweet);

    }

    @Override
    public TweetResponseDto postReply(Long id, TweetRequestDto tweetRequestDto) {
        Tweet tweet = tweetId(id);

        Credentials credentials = credentialsMapper.dtoToEntity(tweetRequestDto.getCredentials());
        Optional<User> optionalUser = userRepository.findByCredentials(credentials);

        if(optionalUser.isEmpty()){
            throw new NotFoundException("no user found with this info");
        }

        User user = optionalUser.get();

        //create the new tweet and populate it with the info
        Tweet postReply = new Tweet();
        postReply.setContent(tweetRequestDto.getContent());
        postReply.setAuthor(user);
        postReply.setInReplyTo(tweet);

        //save the reply
        tweetRepository.saveAndFlush(postReply);

        //gotta process the hashtag and mentions

        //return it
        return tweetMapper.entityToResponseDto(postReply);

    }

    @Override
    public TweetResponseDto deleteTweet(Long id, CredentialsDto credentialsDto) {
        //if no tweet exists or provide credentials do not match author of the tweet
        Tweet tweet = tweetId(id);

        Credentials authorTweet = tweet.getAuthor().getCredentials();

        if(!authorTweet.getUsername().equals(credentialsDto.getUsername()) || !authorTweet.getPassword().equals((credentialsDto.getPassword()))){
            throw new NotFoundException("credentials does not match tweet id:" + id);
        }

        tweet.setDeleted(true);
        tweetRepository.saveAndFlush(tweet);

        return tweetMapper.entityToResponseDto(tweet);

    }

    @Override
    public List<TweetResponseDto> getReposts(Long id) {
        Tweet tweet = tweetId(id); //used to check if tweet is available using the private method above
        List<Tweet> reposts = tweetRepository.nonDeletedRepostsByID(id);
        if(reposts.isEmpty()){
            throw new NotFoundException("No reposts are available of tweet id: " + id);
        }

        return tweetMapper.entitiesToResponseDtos(reposts);
    }

    @Override
    public List<UserResponseDto> getLikes(Long id) {
        Tweet tweet = tweetId(id);

        List<User> users = userRepository.findUsersByLikedTweet(id);

        if(users.isEmpty()){
            throw new NotFoundException("No users liked this tweet id: " + id);
        }

        return userMapper.entitiesToResponseDtos(users);

    }

    @Override
    public ContextDto getContext(Long id) {
        //retrieve the context of the target tweet
        //if that tweet doesnt exist throw an error
        Tweet tweet = tweetId(id);

        // Find tweets before the target
        List<Tweet> beforeTweets = tweetRepository.findTweetsBefore(id);

        // Find replies after the target
        List<Tweet> afterTweets = tweetRepository.findRepliesAfter(id);

        // Map entities to DTOs
        TweetResponseDto target = tweetMapper.entityToResponseDto(tweet);
        List<TweetResponseDto> before = tweetMapper.entitiesToResponseDtos(beforeTweets);
        List<TweetResponseDto> after = tweetMapper.entitiesToResponseDtos(afterTweets);

        // Build and return the context DTO
        ContextDto context = new ContextDto();
        context.setTarget(target);
        context.setBefore(before);
        context.setAfter(after);

        return context;

    }

    @Override
    public List<HashtagDto> getTagById(Long id) {
        Tweet tweet = tweetId(id);
        if(tweet.getHashtags().isEmpty()){
            throw new NotFoundException("No hashtag found with tweet id: " + id);
        }
        return hashTagMapper.entitiesToDtos(tweet.getHashtags());

    }

}
