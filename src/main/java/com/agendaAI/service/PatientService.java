package com.agendaAI.service;

import com.agendaAI.model.dto.request.PatientRequest;
import com.agendaAI.model.dto.response.PatientResponse;
import com.agendaAI.model.entities.PatientEntity;
import com.agendaAI.repo.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientService {

    @Autowired
    private PatientRepo patientRepo;

    public PatientResponse createPatient(PatientRequest request){
        if(patientRepo.findByCpf(request.cpf()).isPresent()){
            throw new RuntimeException("Paciente já cadastrado.");
        }

        PatientEntity patient = PatientEntity.builder()
                .name(request.name())
                .birthDate(request.birthDate())
                .cpf(request.cpf())
                .build();

        PatientEntity savedPatient = patientRepo.save(patient);

        return new PatientResponse(
                savedPatient.getId(),
                savedPatient.getName(),
                savedPatient.getBirthDate(),
                savedPatient.getCpf()
        );
    }

    public PatientResponse findPatientByCpf(String cpf){
        PatientEntity patientEntity = patientRepo.findByCpf(cpf)
                .orElseThrow(() -> new RuntimeException("CPF não encontrado"));

        return new PatientResponse(
                patientEntity.getId(),
                patientEntity.getName(),
                patientEntity.getBirthDate(),
                patientEntity.getCpf());
    }

    public List<PatientResponse> findAllPacients(){
        List<PatientEntity> patients = patientRepo.findAll();
        return patients.stream()
                .map(patient -> new PatientResponse(
                        patient.getId(),
                        patient.getName(),
                        patient.getBirthDate(),
                        patient.getCpf()))
                .collect(Collectors.toList());
    }

    public PatientResponse updatePatientByCpf(String cpf, PatientRequest request){
        PatientEntity existingPatient = patientRepo.findByCpf(cpf)
                .orElseThrow(() -> new RuntimeException("CPF não encontrado"));

        if(!request.cpf().equals(existingPatient.getCpf()) && patientRepo.findByCpf(request.cpf()).isPresent()){
            throw new RuntimeException("Paciente com CPF" + request.cpf() + " já existe.");
        }

        existingPatient.setName(request.name());
        existingPatient.setBirthDate(request.birthDate());
        existingPatient.setCpf(request.cpf());

        PatientEntity updatePatient = patientRepo.save(existingPatient);

        return new PatientResponse(
                updatePatient.getId(),
                updatePatient.getName(),
                updatePatient.getBirthDate(),
                updatePatient.getCpf()
        );

    }

    public void deletePatientByCpf(String cpf){
        PatientEntity patient = patientRepo.findByCpf(cpf)
                .orElseThrow(() -> new RuntimeException("Paciente com CPF " + cpf + " não encontrado"));

        patientRepo.delete(patient);
    }


}
