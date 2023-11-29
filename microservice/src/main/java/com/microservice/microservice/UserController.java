package com.microservice.microservice;

import com.microservice.dto.SignUpDto;
import com.microservice.entity.User;
import com.microservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    public UserService userService;

    @GetMapping(value = "/balance/{account_number}")
    public ResponseEntity<User> getBalance(@PathVariable("account_number") String account_number) {
        User user = userService.getBalance(account_number);
        user.setPassword("********");
        return ResponseEntity.ok(user);
    }

    @PostMapping("/withdraw/{account_number}/{amount}")
    public ResponseEntity<String> withdrawAmount(@PathVariable("amount") Double amount,@PathVariable("account_number") String account_number){
        String message = userService.saveWithdrawAmount(amount,account_number);
        return ResponseEntity.ok(message);
    }

    @PostMapping("/addAmount/{account_number}/{amount}")
    public ResponseEntity<String> addAmount(@PathVariable("amount") Double amount,@PathVariable("account_number") String account_number){
        String message = userService.saveAddAmount(amount,account_number);
        return ResponseEntity.ok(message);
    }

    @PostMapping("/transfer/{senderAccountNumber}/{receiverAccountNumber}/{amount}")
    public ResponseEntity<String> sendMoney(@PathVariable("amount") Double amount,@PathVariable("senderAccountNumber") String senderAccountNumber,
                                            @PathVariable("receiverAccountNumber") String receiverAccountNumber){
        String message = userService.transferMoney(amount, senderAccountNumber, receiverAccountNumber);
        return ResponseEntity.ok(message);
    }

//    @PostMapping("/signup")
//    public ResponseEntity<User> signUp(@RequestBody SignUpDto signUpDto){
//        User user = userService.addUser(signUpDto);
//        return ResponseEntity.ok(user);
//    }

//    @GetMapping(value = "/login/{username}/{password}")
//    public ResponseEntity<String> getBalance(@PathVariable("username") String username, @PathVariable("password") String password) {
//        String message = userService.login(username,password);
//        return ResponseEntity.ok(message);
//    }

    @GetMapping()
    public String Message() {
        return "hgi";
    }

    @GetMapping("/data")
    public User getUser() {
        return userService.getUser();
    }

    @GetMapping("/detailsByAccountNumber/{accountNumber}")
    public ResponseEntity<User> getUserByAccountNumber(@PathVariable("accountNumber") String accountNumber){
        User user= userService.findByAccountNumber(accountNumber);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/details/{id}")
    public ResponseEntity<Optional<User>> getUser(@PathVariable("id") int id){
        Optional<User> user= userService.findById(id);
        return ResponseEntity.ok(user);
    }
}
