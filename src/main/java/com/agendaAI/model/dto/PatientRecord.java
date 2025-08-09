package com.agendaAI.model.dto;

import java.time.LocalDate;

public record PatientRecord(String name, LocalDate birthDate, String cpf) {
}
