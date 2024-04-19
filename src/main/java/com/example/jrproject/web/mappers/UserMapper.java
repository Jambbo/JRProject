package com.example.jrproject.web.mappers;

import com.example.jrproject.domain.user.User;
import com.example.jrproject.web.dto.user.UserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends Mappable<User, UserDto>{
}
