package com.agendaAI.model.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "tb_patient")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PatientEntity {

    @Id
    @GeneratedValue
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "patient_name", nullable = false)
    private String name;

    @Column(name = "patient_birthDate", nullable = false)
    private LocalDate birthDate;

    @CPF
    @Column(unique = true, nullable = false)
    private String cpf;

}
