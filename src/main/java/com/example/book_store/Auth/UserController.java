package com.example.book_store.Auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api/v1/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/signup")
    public ResponseEntity<User> signupUser(@RequestBody User user){
        return ResponseEntity.ok(
                userRepository.save(user)
        );
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signinUser(@RequestBody User user){
        User user1 = userRepository.findUsersByEmail(user.getEmail());
        if(user1.getPassword().equals(user.getPassword())){
            return ResponseEntity.ok(
                    user1
            );
        }else {
            System.out.println("user email/password incorrect");
        }
        return (ResponseEntity<?>) ResponseEntity.internalServerError();
    }

}
