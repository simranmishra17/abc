package com.banking.application.controller;

import com.banking.application.dto.SignUpDto;
import com.banking.application.entity.User;
import com.banking.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/banking")
public class UserController {

    @Autowired
    public UserService userService;


    @PostMapping("/signup")
    public ResponseEntity<User> signUp(@RequestBody SignUpDto signUpDto){
        User user = userService.addUser(signUpDto);
        return ResponseEntity.ok(user);
    }

    @GetMapping(value = "/login/{username}/{password}")
    public ResponseEntity<String> getBalance(@PathVariable("username") String username, @PathVariable("password") String password) {
        String message = userService.login(username,password);
        return ResponseEntity.ok(message);
    }

    @PostMapping("/updatePasword/{accountNumber}/{password}")
    public String changePassword(@PathVariable("accountNumber") String accountNumber, @PathVariable("password") String password) {
        return userService.changePassword(accountNumber, password);
    }

    @GetMapping()
    public String Message() {
        return "hgi";
    }
}
