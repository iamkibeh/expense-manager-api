package tech.kibetimmanuel.expensemanagerapi.service;

import tech.kibetimmanuel.expensemanagerapi.dto.UserDto;
import tech.kibetimmanuel.expensemanagerapi.model.User;

public interface UserService {
    User createUser(UserDto user);

    User readUser();

    User updateUser(UserDto user);

    void deleteUser();

    User getLoggedInUser();
}
