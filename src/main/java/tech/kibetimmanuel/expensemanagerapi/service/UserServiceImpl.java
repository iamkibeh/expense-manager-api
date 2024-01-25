package tech.kibetimmanuel.expensemanagerapi.service;

import org.springframework.stereotype.Service;
import tech.kibetimmanuel.expensemanagerapi.dao.UserDao;
import tech.kibetimmanuel.expensemanagerapi.model.User;

@Service
public class UserServiceImpl implements UserService{
    @Override
    public User createUser(UserDao user) {
        return null;
    }

    @Override
    public User readUser() {
        return null;
    }

    @Override
    public User updateUser(UserDao user) {
        return null;
    }

    @Override
    public void deleteUser() {

    }

    @Override
    public User getLoggedInUser() {
        return null;
    }
}
