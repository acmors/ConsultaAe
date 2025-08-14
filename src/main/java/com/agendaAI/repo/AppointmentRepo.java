package com.agendaAI.repo;

import com.agendaAI.model.entities.AppointmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AppointmentRepo extends JpaRepository<AppointmentEntity, UUID> {
    Optional<AppointmentEntity> findByPatientCpf(String cpf);
}
