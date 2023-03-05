package com.apprendre.bootSpring.Repository;

import com.apprendre.bootSpring.Models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {
    @Query("SELECT c FROM UserModel c WHERE c.name = ?1")
    Optional<UserModel> findByName(String name);
}
