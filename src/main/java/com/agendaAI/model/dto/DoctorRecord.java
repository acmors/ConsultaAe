package com.agendaAI.model.dto;

import com.agendaAI.model.enums.MedicalSpecialty;

public record DoctorRecord(String nome, MedicalSpecialty medicalSpecialty, String crm) {
}
