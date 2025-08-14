package com.agendaAI.controller;

import com.agendaAI.model.dto.request.PatientRequest;
import com.agendaAI.model.dto.response.PatientResponse;
import com.agendaAI.service.PatientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patient")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    @PostMapping
    public ResponseEntity<PatientResponse> createPatient(@Valid @RequestBody PatientRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(patientService.createPatient(request));
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<PatientResponse> getPatientByCpf(@PathVariable String cpf){
        return ResponseEntity.ok(patientService.findPatientByCpf(cpf));
    }

    @GetMapping
    public ResponseEntity<List<PatientResponse>> getAllPatients(){
        return ResponseEntity.ok(patientService.findAllPacients());
    }

    @PutMapping("/{cpf}")
    public ResponseEntity<PatientResponse> updatePatientByCpf(@PathVariable String cpf, @Valid @RequestBody PatientRequest request){
        return ResponseEntity.ok(patientService.updatePatientByCpf(cpf, request));
    }

    @DeleteMapping("/{cpf}")
    public ResponseEntity<Void> deletePatientByCpf(@PathVariable String cpf){
        patientService.deletePatientByCpf(cpf);
        return ResponseEntity.ok().build();
    }


}
