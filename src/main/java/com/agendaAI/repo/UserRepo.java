package com.agendaAI.repo;

import com.agendaAI.model.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.UUID;

public interface UserRepo extends JpaRepository<UserEntity, UUID> {
    UserDetails findByLogin(String login);
}
