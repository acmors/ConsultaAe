package com.agendaAI.controller;
import com.agendaAI.model.dto.request.DoctorRequest;
import com.agendaAI.model.dto.response.DoctorResponse;
import com.agendaAI.service.DoctorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctors")
@RequiredArgsConstructor

public class DoctorController {

    private final DoctorService doctorService;

    @PostMapping
    public ResponseEntity<DoctorResponse> createDoctor(@Valid @RequestBody DoctorRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(doctorService.createDoctor(request));
    }

    @GetMapping("/{crm}")
    public ResponseEntity<DoctorResponse> getDoctorByCrm(@PathVariable String crm){
        return ResponseEntity.ok(doctorService.findDoctorByCrm(crm));
    }

    @GetMapping
    public ResponseEntity<List<DoctorResponse>> getAllDoctors(){
        return ResponseEntity.ok(doctorService.findAllDoctors());
    }

    @PutMapping("/{crm}")
    public ResponseEntity<DoctorResponse> updateDoctorByName(@PathVariable String crm, @Valid @RequestBody DoctorRequest request){
        return ResponseEntity.ok(doctorService.updateDoctorByCrm(crm, request));
    }

    @DeleteMapping("/{crm}")
    public ResponseEntity<Void> deleteDoctorByCrm(@PathVariable String crm){
        doctorService.deleteDoctorByCrm(crm);
        return ResponseEntity.ok().build();
    }



}
