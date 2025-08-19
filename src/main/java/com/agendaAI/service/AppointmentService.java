package com.agendaAI.service;

import com.agendaAI.exception.RecordNotFoundException;
import com.agendaAI.model.dto.request.AppointmentRequest;
import com.agendaAI.model.dto.response.AppointmentResponse;
import com.agendaAI.model.entities.AppointmentEntity;
import com.agendaAI.model.entities.DoctorEntity;
import com.agendaAI.model.entities.PatientEntity;
import com.agendaAI.repo.AppointmentRepo;
import com.agendaAI.repo.DoctorRepo;
import com.agendaAI.repo.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepo appointmentRepo;

    @Autowired
    private DoctorRepo doctorRepo;

    @Autowired
    private PatientRepo patientRepo;

    public AppointmentResponse createAppointment(AppointmentRequest request){
        DoctorEntity doctor = doctorRepo.findById(request.doctorId())
                .orElseThrow(() -> new RecordNotFoundException("Médico não encontrado"));

        PatientEntity patient = patientRepo.findById(request.patientId())
                .orElseThrow(() -> new RecordNotFoundException("Paciente não encontrado"));

        AppointmentEntity appointment = AppointmentEntity.builder()
                .scheduledAt(request.localDateTime())
                .doctor(doctor)
                .patient(patient)
                .reason(request.reason())
                .build();

        appointmentRepo.save(appointment);

        return new AppointmentResponse(
                appointment.getId(),
                appointment.getScheduledAt(),
                appointment.getReason(),
                doctor.getName(),
                doctor.getCrm(),
                patient.getName(),
                patient.getCpf()
        );

    }

    public AppointmentResponse findAppointmentByPatient(String cpf){
        AppointmentEntity appointment = appointmentRepo.findByPatientCpf(cpf)
                .orElseThrow(() -> new RecordNotFoundException("Agendamento não encontrado"));

        return new AppointmentResponse(
                appointment.getId(),
                appointment.getScheduledAt(),
                appointment.getReason(),
                appointment.getDoctor().getName(),
                appointment.getDoctor().getCrm(),
                appointment.getPatient().getName(),
                appointment.getPatient().getCpf()
        );
    }

    public AppointmentResponse getAppointmentByID(UUID id){
        AppointmentEntity appointment = appointmentRepo.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Agendamento nao encontrado"));

        return new AppointmentResponse(
                appointment.getId(),
                appointment.getScheduledAt(),
                appointment.getReason(),
                appointment.getDoctor().getName(),
                appointment.getDoctor().getCrm(),
                appointment.getPatient().getName(),
                appointment.getPatient().getCpf()
        );
    }

    public void deleteAppointment(UUID id){
        AppointmentEntity appointment = appointmentRepo.findById(id)
                    .orElseThrow(() -> new RecordNotFoundException("Consulta não encontrada."));

        appointmentRepo.delete(appointment);
    }

    public AppointmentResponse updateAppointment(UUID id, AppointmentRequest request){
        AppointmentEntity appointmentExisting = appointmentRepo.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Consulta não encontrada."));

        DoctorEntity doctor = doctorRepo.findById(request.doctorId())
                .orElseThrow(() -> new RecordNotFoundException("Médico não encontrado."));

        PatientEntity patient = patientRepo.findById(request.patientId())
                .orElseThrow(() -> new RecordNotFoundException("Paciente não encontrado."));

        appointmentExisting.setScheduledAt(request.localDateTime());
        appointmentExisting.setDoctor(doctor);
        appointmentExisting.setPatient(patient);
        appointmentExisting.setReason(request.reason());

        appointmentRepo.save(appointmentExisting);

        return new AppointmentResponse(
                appointmentExisting.getId(),
                appointmentExisting.getScheduledAt(),
                appointmentExisting.getReason(),
                doctor.getName(),
                doctor.getCrm(),
                patient.getName(),
                patient.getCpf()
        );
    }
}
