package com.agendaAI.model.dto.request;

import com.agendaAI.model.enums.MedicalSpecialty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DoctorRequest(
        @NotBlank(message = "O nome do médico é obrigatório")
        String name,
        @NotNull(message = "A especialidade do médico é obrigatória")
        MedicalSpecialty medicalSpecialty,
        @NotBlank(message = "O CRM do médico é obrigatório")
        String crm) {
}
