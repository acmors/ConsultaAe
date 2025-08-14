package com.agendaAI.model.dto.request;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

public record AppointmentRequest(
        @NotNull(message = "A data do agendamento é obrigatória")
        @Future(message = "A data do agendamento deve estar no futuro")
        LocalDateTime localDateTime,
        @NotBlank(message = "O ID do médico não pode estar vazio.")
        UUID doctorId,
        @NotBlank(message = "O ID do paciente não pode estar vazio.")
        UUID patientId,
        String reason) {
}
