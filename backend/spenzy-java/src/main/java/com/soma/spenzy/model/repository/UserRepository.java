package com.soma.spenzy.model.repository;

import com.soma.spenzy.model.SpenzyUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<SpenzyUser, Long> {
}
