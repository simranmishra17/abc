package com.banking.application.service;


import com.banking.application.dto.SignUpDto;
import com.banking.application.entity.User;

import java.util.Optional;

public interface UserService {

    User addUser(SignUpDto signUpDto);

    String login(String username, String password);

    String changePassword(String accountNumber, String password);

//    User getUser();

}
