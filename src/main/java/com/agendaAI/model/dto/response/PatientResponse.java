package com.agendaAI.model.dto.response;

import java.time.LocalDate;
import java.util.UUID;

public record PatientResponse(
        UUID patientID,
        String name,
        LocalDate birthDate,
        String cpf
) {
}
