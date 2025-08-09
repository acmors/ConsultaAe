package com.agendaAI.model.dto;

import com.agendaAI.model.entities.DoctorEntity;

import java.time.LocalDateTime;

public record AppoimentRecord(LocalDateTime localDateTime, DoctorEntity doctorEntity, String reason) {
}
