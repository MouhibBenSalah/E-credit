package com.spring.user.Config;

import com.spring.user.Entity.User;
import com.spring.user.Enum.Role;
import com.spring.user.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    
    @Override
    public void run(String... args) throws Exception {
        // Create default admin account if it doesn't exist
        if (userRepository.findByRole(Role.Admin).isEmpty()) {
            User defaultAdmin = User.builder()
                .nom("Admin")
                .prenom("System")
                .email("admin@ecredit.com")
                .password(passwordEncoder.encode("admin123"))
                .role(Role.Admin)
                .numCin(1000000000L)
                .build();
            
            userRepository.save(defaultAdmin);
            System.out.println("✅ Default admin account created:");
            System.out.println("   Email: admin@ecredit.com");
            System.out.println("   Password: admin123");
            System.out.println("   Please change the password after first login!");
        } else {
            System.out.println("✅ Admin account already exists");
        }
    }
}