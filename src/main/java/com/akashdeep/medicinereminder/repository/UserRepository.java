package com.akashdeep.medicinereminder.repository;

import com.akashdeep.medicinereminder.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository
        extends JpaRepository<User, Long> {
}