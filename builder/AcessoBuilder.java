package com.system.piratas.oak.builder;

import com.system.piratas.oak.enums.TipoAcesso;
import com.system.piratas.oak.model.Acesso;

import java.util.UUID;

public class AcessoBuilder {

    private UUID accessId;
    private TipoAcesso roles;

    public static AcessoBuilder create(){
        return new AcessoBuilder();
    }

    public AcessoBuilder accessId(UUID accessId){
        this.accessId = accessId;
        return this;
    }

    public AcessoBuilder roles(TipoAcesso roles){
        this.roles = roles;
        return this;
    }

    public com.system.piratas.oak.model.Acesso build(){
        return new Acesso(accessId,roles);
    }
}
