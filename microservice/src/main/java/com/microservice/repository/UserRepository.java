package com.microservice.repository;


import com.microservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findByUsernameAndPassword(String username, String password);
    User findByFirstNameAndLastNameAndPhoneNumber(String first_name, String last_name, String phone_number);
    User findByAccountNumber(String account_number);

}
