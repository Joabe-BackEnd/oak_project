package com.system.piratas.oak.service;

import com.system.piratas.oak.dtos.UsuarioRequestDTO;
import com.system.piratas.oak.enums.TipoAcesso;
import com.system.piratas.oak.exceptions.UsuarioJaCadastradoException;
import com.system.piratas.oak.exceptions.UsuarioNaoEncontradoException;
import com.system.piratas.oak.model.Acesso;
import com.system.piratas.oak.model.Usuarios;
import com.system.piratas.oak.repository.AcessoRepository;
import com.system.piratas.oak.repository.UsuariosRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
public class UsuariosService implements UserDetailsService {
    private final UsuariosRepository usuarioRepository;
    private final PasswordEncoder encoder;
    private final AcessoRepository acessoRepository;


    public UsuariosService(final UsuariosRepository usuarioRepository,final PasswordEncoder encoder,final AcessoRepository acessoRepository) {
        this.usuarioRepository = usuarioRepository;
        this.encoder = encoder;
        this.acessoRepository = acessoRepository;
    }

    public void createUser(UsuarioRequestDTO dto){
        if(usuarioRepository.findByName(dto.name()).isEmpty()){
            throw new UsuarioJaCadastradoException("Este usuário já está cadastrado");
        }
        Usuarios u = new Usuarios();
        u.setName(dto.name());
        u.setPassword(encoder.encode(dto.password()));
        Acesso a = acessoRepository.findByTiposDeAcessos(TipoAcesso.ROLE_NORMAL).get();
        u.setAcessos(List.of(a));
        usuarioRepository.save(u);
    }

    public void deleteUser(String id){
        if(usuarioRepository.findById(UUID.fromString(id)).isEmpty()){
            throw new UsuarioNaoEncontradoException("Usuario não cadastrado.");
        }
        Usuarios u = usuarioRepository.findById(UUID.fromString(id)).get();
        Acesso a = acessoRepository.findByTiposDeAcessos(TipoAcesso.ROLE_DELETED).get();
        u.getAcessos().clear();
        u.setAcessos(List.of(a));
        usuarioRepository.save(u);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioRepository.findByName(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + username));
    }
}
