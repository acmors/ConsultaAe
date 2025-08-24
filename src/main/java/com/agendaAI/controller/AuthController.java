package com.agendaAI.controller;

import com.agendaAI.model.dto.AuthenticationDTO;
import com.agendaAI.model.dto.LoginResponseDTO;
import com.agendaAI.model.dto.RegisterDTO;
import com.agendaAI.model.entities.UserEntity;
import com.agendaAI.model.enums.UserRole;
import com.agendaAI.repo.UserRepo;
import com.agendaAI.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data) {
        var usernamePass = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePass);

        var token = tokenService.generateToken((UserEntity) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data) {
        if (this.userRepo.findByLogin(data.login()) != null) {
            return ResponseEntity.badRequest().body("Usuário já existe");
        }

        String encryptedPass = new BCryptPasswordEncoder().encode(data.password());

        UserEntity newUser = new UserEntity(data.login(), encryptedPass, UserRole.USER);

        this.userRepo.save(newUser);

        return ResponseEntity.ok().build();
    }
}