package com.banking.application.service.impl;

import com.banking.application.dto.SignUpDto;
import com.banking.application.entity.User;
import com.banking.application.entity.UserBehaviour;
import com.banking.application.repository.UserBehaviouRepository;
import com.banking.application.repository.UserRepository;
import com.banking.application.service.UserService;
import java.time.LocalDateTime;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

@Service("userService")
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserBehaviouRepository userBehaviouRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public User addUser(SignUpDto signUpDto) {
        User savedUser;
        User user = userRepository.findByFirstNameAndLastNameAndPhoneNumber(signUpDto.getFirst_name(), signUpDto.getLast_name(), signUpDto.getPhone_number());
        if (user != null) {
            savedUser = null;
        } else {
            User newUser = new User();
            newUser.setBalance(0);
            newUser.setEmail(signUpDto.getEmail());
            newUser.setFirstName(signUpDto.getFirst_name());
            newUser.setLastName(signUpDto.getLast_name());
            newUser.setPhoneNumber(signUpDto.getPhone_number());
            newUser.setAddress(signUpDto.getAddress());
            newUser.setPassword("NewPassword1");
            Random random = new Random();
            int uniqueNumber = random.nextInt(900) + 100;
            newUser.setAccountNumber(String.valueOf(uniqueNumber));
            newUser.setUsername(signUpDto.getFirst_name() + "." + signUpDto.getLast_name()+uniqueNumber);
            userRepository.save(newUser);
            savedUser= userRepository.findByAccountNumber(String.valueOf(uniqueNumber));
        }
        return savedUser;
    }

//    public User getUser(){
//        return restTemplate.getForObject("http://localhost:8082/banking/signup",User.class);
//    }

    @Override public String login(String username, String password) {
        User user = userRepository.findByUsernameAndPassword(username,password).get(0);
        return user==null?"Wrong credentials":"success";
    }

    @Override
    public String changePassword(String accountNumber, String password){

        User user = restTemplate.getForObject("http://localhost:8081/user/detailsByAccountNumber/"+ accountNumber,User.class);
//        System.out.println("demo123"+user);
//        User user = userRepository.findByAccountNumber(accountNumber);
        int day= LocalDateTime.now().getDayOfMonth();
        int month= LocalDateTime.now().getDayOfMonth();
        int year= LocalDateTime.now().getDayOfYear();
        String message= "success";

        List<UserBehaviour> userBehaviour= userBehaviouRepository.findByUserAndDayAndMonthAndYear(user,day,month,year);

        if(CollectionUtils.isEmpty(userBehaviour)){
            UserBehaviour newObject= new UserBehaviour();
            newObject.setUser(user);
            newObject.setCount(1);
            newObject.setDay(day);
            newObject.setMonth(month);
            newObject.setYear(year);
            userBehaviouRepository.save(newObject);
            userRepository.updatePasswordByAccountNumber(password,accountNumber);
        }else{
            if(userBehaviour.get(0).getCount()>=3){
                message="Cannot update password more than 3 times in a day";
                LOGGER.error("Cannot update password more than 3 times in a day");
            }else{
                userBehaviouRepository.updateCountById(userBehaviour.get(0).getCount()+1, userBehaviour.get(0).getId());
            }
        }
        return message;
    }

}
