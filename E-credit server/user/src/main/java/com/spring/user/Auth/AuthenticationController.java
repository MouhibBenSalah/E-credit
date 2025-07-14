package com.spring.user.Auth;

import com.spring.user.Entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request){
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody LoginRequest request){
        return ResponseEntity.ok(authenticationService.login(request));
    }
    
    @PostMapping("/forgot-password")
    public ResponseEntity<Object> forgotPassword(@RequestParam("email") String email) {
        Optional<User> optionalUser = authenticationService.findUserByEmail(email);
        if (!optionalUser.isPresent()) {
            return ResponseEntity.badRequest().body("User not found");
        }

        User user = optionalUser.get();
        String token = UUID.randomUUID().toString();
        authenticationService.createPasswordResetTokenForUser(user, token);

        String resetUrl = "http://localhost:4200/reset-password/" + token;
        authenticationService.sendEmail(user.getEmail(), "Reset Password", "To reset your password, click the link below:\n" + resetUrl);

        return ResponseEntity.ok().build();
    }
    
    /**
     * First-time password setup for newly created accounts
     * This is used when admin creates an account and user needs to set their password
     */
    @PostMapping("/setup-password-first-time")
    public ResponseEntity<Map<String, String>> setupPasswordFirstTime(@RequestBody Map<String, String> payload) {
        String email = payload.get("email");
        String newPassword = payload.get("password");
        
        try {
            Optional<User> optionalUser = authenticationService.findUserByEmail(email);
            if (!optionalUser.isPresent()) {
                Map<String, String> errorResponse = new HashMap<>();
                errorResponse.put("error", "Utilisateur non trouvé");
                return ResponseEntity.badRequest().body(errorResponse);
            }
            
            User user = optionalUser.get();
            authenticationService.setupFirstTimePassword(user, newPassword);
            
            Map<String, String> response = new HashMap<>();
            response.put("message", "Mot de passe configuré avec succès");
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Erreur lors de la configuration du mot de passe: " + e.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @PostMapping("/reset-password")
    public ResponseEntity<Map<String, String>> resetPassword(@RequestBody Map<String, String> payload) {
        String token = payload.get("token");
        String password = payload.get("password");
        try {
            authenticationService.resetPassword(token, password);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Password reset successfully");
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }
    
    /**
     * Validate user exists by email - used for first-time password setup
     */
    @GetMapping("/validate-email/{email}")
    public ResponseEntity<Map<String, Object>> validateEmail(@PathVariable String email) {
        Optional<User> optionalUser = authenticationService.findUserByEmail(email);
        Map<String, Object> response = new HashMap<>();
        
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            response.put("exists", true);
            response.put("name", user.getPrenom() + " " + user.getNom());
            response.put("role", user.getRole().toString());
        } else {
            response.put("exists", false);
        }
        
        return ResponseEntity.ok(response);
    }
}
