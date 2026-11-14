package com.system.piratas.oak.repository;

import com.system.piratas.oak.enums.TipoAcesso;
import com.system.piratas.oak.model.Acesso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AccessRepository extends JpaRepository<Acesso,UUID> {
    Optional<Acesso> findByRoles(TipoAcesso roles);
}
