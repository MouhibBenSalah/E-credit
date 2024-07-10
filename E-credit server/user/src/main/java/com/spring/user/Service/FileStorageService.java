package com.spring.user.Service;

import java.io.File;
import com.spring.user.Entity.User;
import com.spring.user.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;



@Service
public class FileStorageService {

    @Value("${upload.directory}") // Read from application.properties or application.yml
    private String uploadDir;
    private final UserRepository userRepository;

    @Autowired
    public FileStorageService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String uploadImage(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("File is empty. Please provide a valid file.");
        }

        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if (fileName.contains("..")) {
                throw new IllegalArgumentException("Filename contains invalid path sequence " + fileName);
            }

            // Build the path to save the file
            String filePath = uploadDir + fileName;

            // Transfer the file to the upload directory
            File destFile = new File(filePath);
            file.transferTo(destFile);

            // Build the user object with the profile picture path
            User user = new User();
            user.setProfilePicture(filePath);

            // Save the user to the repository
            User savedUser = userRepository.save(user);

            if (savedUser != null) {
                return "File uploaded successfully: " + filePath;
            } else {
                return "Failed to save user or upload file";
            }
        } catch (IOException | IllegalArgumentException ex) {
            throw new IOException("Failed to upload file: " + ex.getMessage(), ex);
        }
    }
}
