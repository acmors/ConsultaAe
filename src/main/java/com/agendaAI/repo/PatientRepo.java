package com.agendaAI.repo;


import com.agendaAI.model.entities.PatientEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PatientRepo extends JpaRepository<PatientEntity, UUID> {
    Optional<PatientEntity> findByCpf(String cpf);
    @Transactional
    void deleteByCpf(String cpf);
}
