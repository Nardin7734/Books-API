package com.interact.repositories;

import com.interact.data.dto.UserDTO;
import com.interact.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>
{
    public Optional<User> findByEmail( String email );
}

