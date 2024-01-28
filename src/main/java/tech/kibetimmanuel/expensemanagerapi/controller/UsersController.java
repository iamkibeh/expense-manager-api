package tech.kibetimmanuel.expensemanagerapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.kibetimmanuel.expensemanagerapi.dto.UserDto;
import tech.kibetimmanuel.expensemanagerapi.model.User;
import tech.kibetimmanuel.expensemanagerapi.service.UserService;

@RestController
@RequiredArgsConstructor
public class UsersController {

    private final UserService userService;

    @GetMapping("/hello")
    public ResponseEntity<String> sayHello() {return  new ResponseEntity<>("Hello", HttpStatus.OK);}

    @GetMapping("/profile")
    public ResponseEntity<User> getUser(){
        return new ResponseEntity<>(userService.readUser(), HttpStatus.OK);
    }

    @PutMapping("/profile")
    public ResponseEntity<User> updateUser(@RequestBody UserDto user){
        return new ResponseEntity<>(userService.updateUser(user),HttpStatus.OK);
    }

    @DeleteMapping("/deactivate")
    public ResponseEntity<HttpStatus> deleteUser(){
        userService.deleteUser();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
