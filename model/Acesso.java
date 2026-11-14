package com.system.piratas.oak.model;

import com.system.piratas.oak.enums.TipoAcesso;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.UUID;

@Entity(name = "access")
@Table(name = "acesso")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Acesso implements Serializable, GrantedAuthority {

    private static final long serializableVersionID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "access_id", columnDefinition = "char(36)")
    private UUID accessId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false,columnDefinition = "varchar(30)", unique = true)
    private TipoAcesso roles;


    @Override
    public String getAuthority() {
        return this.roles.name();
    }

}
