package com.spring.user.Controller;

import com.spring.user.Entity.Compte;
import com.spring.user.Entity.User;
import com.spring.user.FullResponse.FullUserResponse;
import com.spring.user.FullResponse.FullUserResponseForNotif;
import com.spring.user.Repository.UserRepository;
import com.spring.user.Service.FileStorageService;
import com.spring.user.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/User")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    private final UserService userService;

    @Autowired
    private FileStorageService fileStorageService;
  
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    private List<User> getUser() {
        return userService.getAllUsers();
    }

    @GetMapping("/cin/{cin}")
    public ResponseEntity<User> getUserByCin(@PathVariable Long cin) {
        return ResponseEntity.ok(userService.getUserByCin(cin));
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> userOptional = userService.getUserById(id);
        return userOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PostMapping
    public User createUser(@RequestBody User u) {
        return userService.createUser(u);
    }

    @PostMapping("/compte")
    public Compte createCompte(@RequestBody Compte c) {
        return userService.createCompte(c);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<User> updateUserPartial(@PathVariable Long id, @RequestBody User user) {
        Optional<User> updatedUser = userService.updateUser(id, user);
        return updatedUser.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/with-demandesCredit/{userId}")
    public ResponseEntity<FullUserResponse> getAllUsersWithDemandes(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(userService.findUserbyDemandeCredit(userId));
    }

    @GetMapping("/with-notifications/{userId}")
    public ResponseEntity<FullUserResponseForNotif> getAllUsersWithNotif(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(userService.findUserbyNotif(userId));
    }
    @PostMapping("/uploadProfilePicture")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            String message = fileStorageService.uploadImage(file);
            return ResponseEntity.status(HttpStatus.OK).body(message);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload file: " + e.getMessage());
        }
    }


    @GetMapping("/current-user")
    public ResponseEntity<User> getCurrentUser(Authentication authentication) {
        User currentUser = (User) authentication.getPrincipal();
        return ResponseEntity.ok(currentUser);
    }
}
