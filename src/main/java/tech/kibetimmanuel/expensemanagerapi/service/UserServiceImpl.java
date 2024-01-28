package tech.kibetimmanuel.expensemanagerapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tech.kibetimmanuel.expensemanagerapi.dto.UserDto;
import tech.kibetimmanuel.expensemanagerapi.exceptions.ItemExistsException;
import tech.kibetimmanuel.expensemanagerapi.exceptions.ResourceNotFoundException;
import tech.kibetimmanuel.expensemanagerapi.model.User;
import tech.kibetimmanuel.expensemanagerapi.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepo;

    private final PasswordEncoder bcryptEncoder;

    @Override
    public User createUser(UserDto user) {
        if (userRepo.existsByEmail(user.getEmail())){
            throw new ItemExistsException("User is already registered with email: "+user.getEmail());
        }

        User newUser = user.buildUser();
        newUser.setPassword(bcryptEncoder.encode(newUser.getPassword()));
        return userRepo.save(newUser);
    }

    @Override
    public User readUser() {
        Long userId = getLoggedInUser().getId();
        return userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found for the id: "+userId));
    }

    @Override
    public User updateUser(UserDto user) {
        User existingUser = readUser();
        existingUser.setName(user.getName() !=null ? user.getName() : existingUser.getName());
        existingUser.setEmail(user.getEmail() != null  ? user.getEmail() : existingUser.getEmail());
        existingUser.setPassword((user.getPassword() != null) ? bcryptEncoder.encode(user.getPassword()) : existingUser.getPassword());
        return userRepo.save(existingUser);
    }

    @Override
    public void deleteUser() {
        User existingUser = readUser();
        userRepo.delete(existingUser);
    }

    @Override
    public User getLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        return userRepo.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found for the email: " + email));
    }

}

