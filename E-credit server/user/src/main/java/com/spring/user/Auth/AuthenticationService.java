package com.spring.user.Auth;


import com.spring.user.Entity.Compte;
import com.spring.user.Entity.PasswordResetToken;
import com.spring.user.Entity.User;
import com.spring.user.Enum.Role;
import com.spring.user.Repository.PasswordResetTokenRepository;
import com.spring.user.Repository.UserRepository;
import com.spring.user.Service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordResetTokenRepository tokenRepository;
    private final JavaMailSender mailSender;

    public AuthenticationResponse register(RegisterRequest request) {
        // Create User entity
        var user = User.builder()
                .nom(request.getNom())
                .prenom(request.getPrenom())
                .numCin(request.getNumCin())
              /*  .sf(request.getSf())*/
                .dateNaiss(request.getDateNaiss())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.Client)
                .build();

    /*    Set<Compte> comptes = request.getComptes().stream()
                .map(compteRequest -> Compte.builder()
                        .numCompte(compteRequest.getNumCompte())
                        .dateOuvCompte(compteRequest.getDateOuvCompte())
                        .deviseC(compteRequest.getDeviseC())
                        .typeC(compteRequest.getTypeC())
                        .etatC(compteRequest.getEtatC())
                        .user(user)
                        .build())
                .collect(Collectors.toSet());

        user.setComptes(comptes);*/

        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public AuthenticationResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + request.getEmail()));
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }
    
    /**
     * Setup password for first-time login (newly created accounts by admin)
     */
    public void setupFirstTimePassword(User user, String newPassword) {
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }

    public void createPasswordResetTokenForUser(User user, String token) {
        PasswordResetToken myToken = new PasswordResetToken(token, user);
        tokenRepository.save(myToken);
    }

    public void sendEmail(String recipientAddress, String subject, String message) {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(message);
        mailSender.send(email);
    }

    public Optional<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void resetPassword(String token, String newPassword) {
        PasswordResetToken resetToken = tokenRepository.findByToken(token);
        if (resetToken == null || resetToken.isExpired()) {
            throw new IllegalArgumentException("Invalid or expired token");
        }

        User user = resetToken.getUser();
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);

        tokenRepository.delete(resetToken);
    }
}
