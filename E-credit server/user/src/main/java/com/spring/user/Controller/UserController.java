package com.spring.user.Controller;

import com.spring.user.DTO.UpdateUserDTO;
import com.spring.user.Entity.Compte;
import com.spring.user.Entity.User;
import com.spring.user.FullResponse.FullUserResponse;
import com.spring.user.FullResponse.FullUserResponseForNotif;
import com.spring.user.Repository.UserRepository;
import com.spring.user.Service.FileStorageService;
import com.spring.user.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/User")
public class UserController {
    @Value("${upload.directory}") // Read from application.properties
    private String uploadDir;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FileStorageService fileStorageService;

    private final ResourceLoader resourceLoader;
    private final UserService userService;

    @Autowired
    public UserController(UserService userService, ResourceLoader resourceLoader)  {
        this.resourceLoader = resourceLoader;
        this.userService = userService;
    }

    @GetMapping("/")
    private List<User> getUser() {
        return userService.getAllUsers();
    }

    @GetMapping("/clients")
    public List<User> getAllClients() {
        return userService.getAllClients();
    }

    @GetMapping("/cin/{cin}")
    public ResponseEntity<User> getUserByCin(@PathVariable Long cin) {
        return ResponseEntity.ok(userService.getUserByCin(cin));
    }
    @GetMapping("/id/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }
    @PostMapping
    public User createUser(@RequestBody User u) {
        return userService.createUser(u);
    }

    @PostMapping("/compte/{userId}")
    public Compte addCompteToUser(@PathVariable Long userId, @RequestBody Compte compte) {
        return userService.creerComptePourClient(userId, compte);
    }

    @PatchMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody UpdateUserDTO updateUserDTO) {
        return userService.updateUser(id, updateUserDTO);
    }

    @GetMapping("/with-demandesCredit/{userId}")
    public ResponseEntity<FullUserResponse> getAllUsersWithDemandes(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(userService.findUserbyDemandeCredit(userId));
    }

    @GetMapping("/with-notifications/{userId}")
    public ResponseEntity<FullUserResponseForNotif> getAllUsersWithNotif(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(userService.findUserbyNotif(userId));
    }
  /*  @PostMapping("/{userId}/UploadPhotoProfil")
    public ResponseEntity<String> uploadUserProfileImage(
            @PathVariable Long userId,
            @RequestParam("file") MultipartFile file) {
        userService.uploadUserProfileImage(userId, file);
        return ResponseEntity.ok( "Photo uploaded successfully");
    }*/

    @PostMapping("/uploadProfilePicture/{userId}")
    public ResponseEntity<String> uploadImage(@PathVariable ("userId") Long userId ,@RequestParam("file") MultipartFile file) {
        try {
            String message = fileStorageService.uploadImage(userId,file);
            return ResponseEntity.status(HttpStatus.OK).body(message);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload file: " + e.getMessage());
        }
    }

    @GetMapping("/profile-picture/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getProfilePicture(@PathVariable String filename) {
        try {
            Path filePath = Paths.get(uploadDir).resolve(filename).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists() || resource.isReadable()) {
                return ResponseEntity.ok()
                        .body(resource);
            } else {
                throw new RuntimeException("File not found " + filename);
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }


    @DeleteMapping("/delete/{idU}")
    public ResponseEntity<String> deleteUser(@PathVariable("idU") Long idU) {
        boolean deleted = userService.deleteUser(idU);
        if (deleted) {
            return ResponseEntity.ok("User deleted!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with ID " + idU + " does not exist.");
        }
    }
    @GetMapping("/current-user")
    public ResponseEntity<User> getCurrentUser(Authentication authentication) {
        User currentUser = (User) authentication.getPrincipal();
        return ResponseEntity.ok(currentUser);
    }

    @PostMapping("/CalculRatioDendettement")
    public double CalculRatioDendettement(@RequestBody User user) {
        return userService.CalculRatioDendettement(user);
    }
    @PostMapping("/calculateRepaymentCapacity")
    public double calculateRepaymentCapacity(@RequestBody User user) {
        return userService.calculateRepaymentCapacity(user);
    }
    @GetMapping("/nbreClients")
    public ResponseEntity<Integer> getNbreClients() {
        Integer nbreClients = userService.calculateNbreClients();
        return ResponseEntity.ok(nbreClients);
    }
}
