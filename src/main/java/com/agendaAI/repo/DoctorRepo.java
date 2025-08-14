package com.agendaAI.repo;

import com.agendaAI.model.entities.DoctorEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface DoctorRepo extends JpaRepository<DoctorEntity, UUID> {
    Optional<DoctorEntity> findByName(String name);
    Optional<DoctorEntity> findByCrm(String crm);

    @Transactional
    void deleteByName(String name);

    @Transactional
    void deleteByCrm(String crm);
}
