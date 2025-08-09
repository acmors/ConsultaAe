package com.agendaAI.model.entities;

import com.agendaAI.model.enums.MedicalSpecialty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@Table(name = "tb_doctor")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DoctorEntity {

    @Id
    @GeneratedValue
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    private String name;

    @Enumerated(EnumType.STRING)
    private MedicalSpecialty medicalSpecialty;

    @Column(unique = true, nullable = false)
    private String crm;
}
