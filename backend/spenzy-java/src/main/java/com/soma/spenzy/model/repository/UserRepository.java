package com.soma.spenzy.model.repository;

import com.soma.spenzy.model.SpenzyUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<SpenzyUser, Long> {
    Optional<SpenzyUser> findByEmail(String email);
    SpenzyUser deleteByEmail(String email);
}
