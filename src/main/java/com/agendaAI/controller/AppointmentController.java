package com.agendaAI.controller;

import com.agendaAI.model.dto.request.AppointmentRequest;
import com.agendaAI.model.dto.response.AppointmentResponse;
import com.agendaAI.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/appointment")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;

    @PostMapping
    public ResponseEntity<Void> addAppointment(@RequestBody AppointmentRequest dto){
        appointmentService.createAppointment(dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<AppointmentResponse> getAppointmentByCpf(@RequestParam String cpf){
        return ResponseEntity.ok(appointmentService.findAppointmentByPatient(cpf));
    }
}
