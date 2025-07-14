package com.portfolio.portfolio.service.user;

import com.portfolio.portfolio.dto.UserDto;
import com.portfolio.portfolio.model.User;
import com.portfolio.portfolio.request.CreateUserRequest;
import com.portfolio.portfolio.request.UpdateUserRequest;

public interface IUserService {

    User createUser(CreateUserRequest request);

    User updateUser(UpdateUserRequest request, Long userId);

    User getUserById(Long userId);

    void deleteUser(Long userId);

    UserDto convertToDto(User user);
}
