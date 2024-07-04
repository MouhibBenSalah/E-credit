package com.spring.user.Auth;

import com.spring.user.Entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

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
    public ResponseEntity<String> forgotPassword(@RequestParam("email") String email) {
        Optional<User> optionalUser = authenticationService.findUserByEmail(email);
        if (!optionalUser.isPresent()) {
            return ResponseEntity.badRequest().body("User not found");
        }

        User user = optionalUser.get();
        String token = UUID.randomUUID().toString();
        authenticationService.createPasswordResetTokenForUser(user, token);

        String resetUrl = "http://localhost:4020/auth/reset-password?token=" + token;
        authenticationService.sendEmail(user.getEmail(), "Reset Password", "To reset your password, click the link below:\n" + resetUrl);

        return ResponseEntity.ok("Email sent");
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody Map<String, String> payload) {
        String token = payload.get("token");
        String password = payload.get("password");
        try {
            authenticationService.resetPassword(token, password);
            return ResponseEntity.ok("Password reset successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
