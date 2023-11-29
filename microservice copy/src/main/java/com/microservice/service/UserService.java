package com.microservice.service;

import com.microservice.dto.SignUpDto;
import com.microservice.entity.User;

import java.util.Optional;

public interface UserService {
    User getBalance(String accountNumber);

    String saveWithdrawAmount(Double amount, String accountNumber);

    String saveAddAmount(Double amount, String accountNumber);

    String transferMoney(Double amount, String senderAccountNumber, String receiverAccountNumber);

//    User addUser(SignUpDto signUpDto);
//
//    String login(String username, String password);

    Optional<User> findById(int id);

    User getUser();

    User findByAccountNumber(String accountNumber);
}
