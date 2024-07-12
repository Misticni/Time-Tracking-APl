package com.project.demo.users.repositories;

import com.project.demo.users.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    @Query("select u from User u where u.dateDeleted is null and u.email=:email")
    Optional<User> findByEmail(String email);

}
