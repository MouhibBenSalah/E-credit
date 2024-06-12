package com.spring.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/User")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    private List<User> getUser() {
        return userService.getAllUsers();
    }
    @PostMapping
    public User createUser(@RequestBody User u) {
        return userService.createUser(u);
    }

    @GetMapping("/with-demandesCredit/{userId}")
    public ResponseEntity<FullUserResponse> getAllUsersWithDemandes(@PathVariable("userId") int userId) {
        return ResponseEntity.ok(userService.findUserbyDemandeCredit(userId));
    }

    @GetMapping("/with-notifications/{userId}")
    public ResponseEntity<FullUserResponseForNotif> getAllUsersWithNotif(@PathVariable("userId") int userId) {
        return ResponseEntity.ok(userService.findUserbyNotif(userId));
    }
}
