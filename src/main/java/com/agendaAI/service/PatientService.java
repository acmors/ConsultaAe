package com.agendaAI.service;

import com.agendaAI.model.dto.PatientRecord;
import com.agendaAI.model.entities.PatientEntity;
import com.agendaAI.repo.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientService {

    @Autowired
    private PatientRepo patientRepo;

    public PatientEntity savePatient(PatientRecord dto){
        PatientEntity patient = new PatientEntity();
        patient.setName(dto.name());
        patient.setCpf(dto.cpf());
        patient.setBirthDate(dto.birthDate());

        return patientRepo.save(patient);
    }

    public PatientRecord findPatientByCpf(String cpf){
        PatientEntity patientEntity = patientRepo.findByCpf(cpf)
                .orElseThrow(() -> new RuntimeException("CPF não encontrado"));

        return new PatientRecord(patientEntity.getName(), patientEntity.getBirthDate(), patientEntity.getCpf());
    }

    public void deletePatientByCpf(String cpf){
        patientRepo.deleteByCpf(cpf);
    }

    public PatientRecord updatePatientByCpf(String cpf, PatientRecord dto){

        PatientEntity existingPatient = patientRepo.findByCpf(cpf)
                .orElseThrow(() -> new RuntimeException("CPF não encontrado"));

        PatientEntity updatedPatient = PatientEntity.builder()
                .id(existingPatient.getId())
                .cpf(dto.cpf() != null ? dto.cpf() : existingPatient.getCpf())
                .name(dto.name() != null ? dto.name() : existingPatient.getName())
                .birthDate(dto.birthDate() != null ? dto.birthDate() : existingPatient.getBirthDate())
                .build();

        PatientEntity saved = patientRepo.save(updatedPatient);
        return new PatientRecord(saved.getName(), saved.getBirthDate(), saved.getCpf());
    }
}
