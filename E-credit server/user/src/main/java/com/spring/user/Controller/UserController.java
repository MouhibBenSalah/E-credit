package com.spring.user.Controller;

import com.spring.user.DTO.CreateUserByAdminDTO;
import com.spring.user.DTO.UpdateUserDTO;
import com.spring.user.Entity.ChangePasswordRequest;
import com.spring.user.Entity.Compte;
import com.spring.user.Entity.User;
import com.spring.user.Enum.Role;
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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

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
    
    /**
     * Get all Chef d'Agence users - Admin only
     */
    @GetMapping("/chef-agence")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<List<User>> getAllChefAgence() {
        return ResponseEntity.ok(userService.getAllChefAgence());
    }
    
    /**
     * Get all Chargé de Banque users - Admin only  
     */
    @GetMapping("/charge-banque")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<List<User>> getAllChargeBanque() {
        return ResponseEntity.ok(userService.getAllChargeBanque());
    }

    @GetMapping("/cin/{cin}")
    public ResponseEntity<User> getUserByCin(@PathVariable Long cin) {
        return ResponseEntity.ok(userService.getUserByCin(cin));
    }
    @GetMapping("/id/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }
    
    /**
     * Original createUser method - now restricted or deprecated
     * Consider removing this if you want only admin-created accounts
     */
    @PostMapping
    public User createUser(@RequestBody User u) {
        return userService.createUser(u);
    }
    
    /**
     * Admin creates user accounts for Chef d'Agence and Chargé de Banque
     * Sends email notification for password setup
     */
    @PostMapping("/create-by-admin")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<?> createUserByAdmin(@RequestBody CreateUserByAdminDTO createUserDTO, Authentication authentication) {
        try {
            // Check if current user is admin
            User currentUser = (User) authentication.getPrincipal();
            if (!userService.isAdmin(currentUser)) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(Map.of("error", "Seuls les administrateurs peuvent créer des comptes"));
            }
            
            // Validate target role
            if (createUserDTO.getTargetRole() != Role.CHEF_AGENCE && 
                createUserDTO.getTargetRole() != Role.CHARGE_BANQUE) {
                return ResponseEntity.badRequest()
                    .body(Map.of("error", "Rôle non valide. Seuls CHEF_AGENCE et CHARGE_BANQUE sont autorisés"));
            }
            
            // Check if email already exists
            if (userRepository.findByEmail(createUserDTO.getEmail()).isPresent()) {
                return ResponseEntity.badRequest()
                    .body(Map.of("error", "Un utilisateur avec cet email existe déjà"));
            }
            
            // Check if CIN already exists
            if (userRepository.findByNumCin(createUserDTO.getNumCin()).isPresent()) {
                return ResponseEntity.badRequest()
                    .body(Map.of("error", "Un utilisateur avec ce numéro CIN existe déjà"));
            }
            
            // Create user entity
            User newUser = User.builder()
                .nom(createUserDTO.getNom())
                .prenom(createUserDTO.getPrenom())
                .numCin(createUserDTO.getNumCin())
                .dateNaiss(createUserDTO.getDateNaiss())
                .lieuNaiss(createUserDTO.getLieuNaiss())
                .sexe(createUserDTO.getSexe())
                .sf(createUserDTO.getSf())
                .email(createUserDTO.getEmail())
                .revenuMensuel(createUserDTO.getRevenuMensuel())
                .chargesMensuelles(createUserDTO.getChargesMensuelles())
                .salaire(createUserDTO.getSalaire())
                .build();
            
            // Create user with admin service (includes email notification)
            User createdUser = userService.createUserByAdmin(newUser, createUserDTO.getTargetRole());
            
            // Remove password from response for security
            createdUser.setPassword(null);
            
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Compte créé avec succès. Un email a été envoyé pour configurer le mot de passe.");
            response.put("user", createdUser);
            
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of("error", "Erreur lors de la création du compte: " + e.getMessage()));
        }
    }
    
    /**
     * Get default admin account or create if doesn't exist
     */
    @GetMapping("/default-admin")
    public ResponseEntity<User> getDefaultAdmin() {
        User admin = userService.getDefaultAdmin();
        admin.setPassword(null); // Don't return password
        return ResponseEntity.ok(admin);
    }
    
    /**
     * Admin dashboard endpoint - returns dashboard data
     */
    @GetMapping("/admin-dashboard")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<Map<String, Object>> getAdminDashboard() {
        Map<String, Object> dashboardData = new HashMap<>();
        dashboardData.put("totalUsers", userService.getAllUsers().size());
        dashboardData.put("totalClients", userService.getAllClients().size());
        dashboardData.put("totalChefAgence", userService.getAllChefAgence().size());
        dashboardData.put("totalChargeBanque", userService.getAllChargeBanque().size());
        dashboardData.put("chefAgenceList", userService.getAllChefAgence());
        dashboardData.put("chargeBanqueList", userService.getAllChargeBanque());
        
        return ResponseEntity.ok(dashboardData);
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
    @PatchMapping("/changePassword")
    public ResponseEntity<?> changePassword(
            @RequestBody ChangePasswordRequest request,
            Principal connectedUser
    ) {
        userService.changePassword(request, connectedUser);
        return ResponseEntity.ok().build();
    }
}
