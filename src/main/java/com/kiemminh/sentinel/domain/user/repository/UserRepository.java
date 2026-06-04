package com.kiemminh.sentinel.domain.user.repository;

import com.kiemminh.sentinel.domain.user.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserInfo, Long> {
    
    // Spring Boot automatically translate to: SELECT * FROM users WHERE username = ?
    Optional<UserInfo> findByUsername(String username);
}