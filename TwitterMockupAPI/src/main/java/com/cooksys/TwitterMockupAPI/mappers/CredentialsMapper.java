package com.cooksys.TwitterMockupAPI.mappers;

import com.cooksys.TwitterMockupAPI.dtos.CredentialsDto;
import com.cooksys.TwitterMockupAPI.entities.embeddables.Credentials;
import org.mapstruct.Mapper;

@Mapper(componentModel= "spring")
public interface CredentialsMapper {

    CredentialsDto toEntity(CredentialsDto credentialsDto);

    CredentialsDto toDto(Credentials credentials);
}
