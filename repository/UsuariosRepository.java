package com.system.piratas.oak.repository;

import com.system.piratas.oak.model.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UsuariosRepository extends JpaRepository<Usuarios, UUID> {
    Optional<Usuarios> findByName(String name);
}
