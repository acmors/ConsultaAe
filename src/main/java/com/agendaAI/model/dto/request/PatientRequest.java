package com.agendaAI.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

public record PatientRequest(
        @NotBlank(message = "O nome do paciente é obrigatório")
        String name,
        @NotNull(message = "A data de nascimento do paciente não pode estar vazia.")
        @Past(message = "A data de nascimento do paciente deve estar no passado.")
        LocalDate birthDate,
        @NotBlank(message = "Campo CPF não pode ser vazio.")
        @CPF(message = "CPF inválido")
        String cpf) {
}
