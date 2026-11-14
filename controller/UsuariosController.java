package com.system.piratas.oak.controller;

import com.system.piratas.oak.dtos.UsuarioRequestDTO;
import com.system.piratas.oak.service.UsuariosService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("oak/system/users")
public class UsuariosController {

    private final UsuariosService usuariosService;

    public UsuariosController(UsuariosService usuariosService) {
        this.usuariosService = usuariosService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/register")
    public ResponseEntity<String> createUser(@RequestBody UsuarioRequestDTO dto){
        usuariosService.createUser(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Usuario "+dto.name() +" cadastrado com sucesso.");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id){
        usuariosService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
