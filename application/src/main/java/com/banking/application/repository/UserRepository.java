package com.banking.application.repository;


import com.banking.application.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findByUsernameAndPassword(String username, String password);
    User findByFirstNameAndLastNameAndPhoneNumber(String first_name, String last_name, String phone_number);
    User findByAccountNumber(String account_number);
    @Transactional
    @Modifying
    @Query("update User u set u.password = ?1 where u.accountNumber = ?2")
    int updatePasswordByAccountNumber(String password, String accountNumber);
}
