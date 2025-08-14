package com.agendaAI.model.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

public record AppointmentResponse(
        UUID appointmentID,
        LocalDateTime scheduledAt,
        String reason,
        String doctorName,
        String doctorSpeciality,
        String patientName,
        String patientCpf
) {
}
