/*package com.nahara.toka.controller;

import com.nahara.toka.error.ResourceNotFoundException;
import com.nahara.toka.model.User;
import com.nahara.toka.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1.0")*/
/*@CrossOrigin(origins = "")
public class UserController {

    public UserController() {}

    @Autowired
    private UserRepository userRepository;

    //@Autowired
    //private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/users")
    public Object getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUsersById(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        User user =
                userRepository
                        .findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("user not found on -" + id));
        return ResponseEntity.ok().body(user);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(
            @PathVariable(value = "id") Long id, @Valid @RequestBody User userDetails)
            throws ResourceNotFoundException {

        User user =
                userRepository
                        .findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("user not found - " + id));

        user.setEmail(userDetails.getEmail());
        user.setFirstname(userDetails.getFirstname());
        user.setLastname(userDetails.getLastname());
        user.setAccountid(userDetails.getAccountid());
        //user.setPublickey(userDetails.getPublickey());
        user.setPrivateKey();
        user.setPublickey();

        final User updatedUser = userRepository.save(user);
        return ResponseEntity.ok(updatedUser);
    }



    @DeleteMapping("/user/{id}")
    public Map<String, Boolean> deleteUser(@PathVariable(value = "id") Long id) throws Exception {
        User user =
                userRepository
                        .findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("user not found - " + id));

        userRepository.delete(user);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}*/
