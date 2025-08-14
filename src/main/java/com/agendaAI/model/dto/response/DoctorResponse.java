package com.agendaAI.model.dto.response;

import com.agendaAI.model.enums.MedicalSpecialty;

import java.util.UUID;

public record DoctorResponse(
        UUID doctorID,
        String nome,
        MedicalSpecialty medicalSpecialty,
        String crm
) {
}
