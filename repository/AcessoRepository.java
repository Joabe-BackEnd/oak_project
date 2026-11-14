package com.system.piratas.oak.repository;

import com.system.piratas.oak.enums.TipoAcesso;
import com.system.piratas.oak.model.Acesso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AcessoRepository extends JpaRepository<Acesso, UUID> {
    Optional<Acesso> findByTiposDeAcessos(TipoAcesso acesso);
}
