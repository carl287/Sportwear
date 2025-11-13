package com.example.Sportwear.Repository;


import com.example.Sportwear.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {
    Optional<Users> findByUsernameAndPassword(String username, String password);
}
