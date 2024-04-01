package com.amida.cloudStorage.mapper;

import com.amida.cloudStorage.dto.UserDto;
import com.amida.cloudStorage.model.User;
import org.springframework.stereotype.Component;

import java.util.function.Function;
@Component
public class UserDtoMapper implements Function<UserDto, User> {
    @Override
    public User apply(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());

        return user;
    }
}
