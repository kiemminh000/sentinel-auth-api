package com.kiemminh.sentinel;

import com.kiemminh.sentinel.domain.user.entity.UserInfo;
import com.kiemminh.sentinel.domain.user.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class SentinelApplication {

    public static void main(String[] args) {
        SpringApplication.run(SentinelApplication.class, args);
    }

    /**
     * This Bean runs automatically when the application starts.
     * It injects a test admin user into the PostgreSQL database if it's empty.
     */
    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            // Check if the user "admin" already exists to prevent duplicate entries
            if (userRepository.findByUsername("admin").isEmpty()) {
                
                // Create a new user with BCrypt hashed password
                UserInfo admin = new UserInfo(
                        "admin",
                        passwordEncoder.encode("123456"),
                        "ROLE_ADMIN"
                );
                
                userRepository.save(admin);
                System.out.println("INITIALIZATION: Test user 'admin' generated with password '123456'");
            }
        };
    }
}