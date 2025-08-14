package com.agendaAI.service;

import com.agendaAI.model.dto.request.DoctorRequest;
import com.agendaAI.model.dto.response.DoctorResponse;
import com.agendaAI.model.entities.DoctorEntity;
import com.agendaAI.repo.DoctorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepo doctorRepo;

    public DoctorResponse createDoctor(DoctorRequest request){

        if(doctorRepo.findByCrm(request.crm()).isPresent()){
            throw new RuntimeException("CRM já existente nesta unidaede");
        }

        DoctorEntity doctor = DoctorEntity.builder()
                .name(request.name())
                .medicalSpecialty(request.medicalSpecialty())
                .crm(request.crm())
                .build();

        DoctorEntity savedDoctor = doctorRepo.save(doctor);

        return new DoctorResponse(
                savedDoctor.getId(),
                savedDoctor.getName(),
                savedDoctor.getMedicalSpecialty(),
                savedDoctor.getCrm()

        );
    }

    public DoctorResponse findDoctorByCrm(String crm){
        DoctorEntity doctorEntity = doctorRepo.findByCrm(crm)
                .orElseThrow(() -> new RuntimeException("There is not a Doctor with this crm."));
        return new DoctorResponse(
                doctorEntity.getId(),
                doctorEntity.getName(),
                doctorEntity.getMedicalSpecialty(),
                doctorEntity.getCrm());
    }

    public List<DoctorResponse> findAllDoctors(){
        List<DoctorEntity> doctors = doctorRepo.findAll();
        return doctors.stream()
                .map(doctor -> new DoctorResponse(
                        doctor.getId(),
                        doctor.getName(),
                        doctor.getMedicalSpecialty(),
                        doctor.getCrm()))
                .collect(Collectors.toList());

    }

    public DoctorResponse updateDoctorByCrm(String crm, DoctorRequest request){
        DoctorEntity existingDoctor = doctorRepo.findByCrm(crm)
                .orElseThrow(() -> new RuntimeException("Médico com CRM " + request.crm() + " não encontrado"));

        if(!request.crm().equals(existingDoctor.getCrm()) && doctorRepo.findByCrm(request.crm()).isPresent()){
            throw new RuntimeException("Médico com CRM " + request.crm() + " já cadastrado.");
        }

        existingDoctor.setName(request.name());
        existingDoctor.setMedicalSpecialty(request.medicalSpecialty());
        existingDoctor.setCrm(request.crm());

        DoctorEntity updatedDoctor = doctorRepo.save(existingDoctor);

        return new DoctorResponse(
                updatedDoctor.getId(),
                updatedDoctor.getName(),
                updatedDoctor.getMedicalSpecialty(),
                updatedDoctor.getCrm()
        );
    }

    public void deleteDoctorByCrm(String crm){
        DoctorEntity doctor = doctorRepo.findByCrm(crm)
                .orElseThrow(() -> new RuntimeException("Médico com CRM " + crm + " não encontrado."));

        doctorRepo.delete(doctor);
    }


}
