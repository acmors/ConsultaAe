package com.agendaAI.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import jdk.jshell.Snippet;


public enum MedicalSpecialty {
    CARDIOLOGY,
    DERMATOLOGY,
    PEDIATRICS,
    NEUROLOGY,
    ORTHOPEDICS,
    GENERAL_PRACTICE;

    @JsonCreator
    public static MedicalSpecialty fromString(String value){
        return MedicalSpecialty.valueOf(value.toUpperCase());
    }

    @JsonValue
    public String toValue(){
        return this.name().toLowerCase();
    }
}
