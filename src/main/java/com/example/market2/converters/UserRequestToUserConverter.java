package com.example.market2.converters;

import com.example.market2.entity.User;
import com.example.market2.entity.UserRequest;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;

@Component
public class UserRequestToUserConverter implements Converter<UserRequest, User> {

    @Override
    @Nullable
    public User convert(UserRequest source) {
            if(source == null) {
                return null;
            }
            final User user = new User();
            user.setBalance(source.getBalance());
            user.setEmail(source.getEmail());
            user.setName(source.getName());
            user.setPhone(source.getPhone());
            return user;
    }

}
