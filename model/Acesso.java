package com.system.piratas.oak.model;

import com.system.piratas.oak.enums.TipoAcesso;
import jakarta.persistence.*;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "tipo_acessos")
public class Acesso implements Serializable, GrantedAuthority {
    private static final long serializableVersionID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "acesso_id")
    // columnDefinition = "char(36)"
    private UUID acessoId;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_acesso", nullable = false, unique = true)
    //,columnDefinition = "varchar(30)"
    private TipoAcesso tiposDeAcessos;

    @Override
    public String getAuthority() {
        return this.tiposDeAcessos.name();
    }
}
