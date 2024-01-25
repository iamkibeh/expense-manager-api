package tech.kibetimmanuel.expensemanagerapi.service;

import tech.kibetimmanuel.expensemanagerapi.dao.UserDao;
import tech.kibetimmanuel.expensemanagerapi.model.User;

public interface UserService {
    User createUser(UserDao user);

    User readUser();

    User updateUser(UserDao user);

    void deleteUser();

    User getLoggedInUser();
}
