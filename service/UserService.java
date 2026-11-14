package com.system.piratas.oak.service;

import com.system.piratas.oak.dtos.UserRequestDTO;
import com.system.piratas.oak.enums.TipoAcesso;
import com.system.piratas.oak.model.Acesso;
import com.system.piratas.oak.model.Users;
import com.system.piratas.oak.repository.AccessRepository;
import com.system.piratas.oak.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class UserService implements UserDetailsService {
    private final UserRepository repository;
    private final PasswordEncoder encoder;
    private final AccessRepository accessRepository;

    public UserService(final UserRepository repository, final PasswordEncoder encoder, final AccessRepository accessRepository) {
        this.repository = repository;
        this.encoder = encoder;
        this.accessRepository = accessRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByName(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + username));
    }


    public void createUser(UserRequestDTO dto) {

        Users user = new Users();

        user.setName(dto.name());
        user.setPassword(encoder.encode(dto.password()));

        Acesso normalRole = accessRepository
                .findByRoles(TipoAcesso.ROLE_NORMAL)
                .orElseThrow(() ->
                        new RuntimeException("Permissão ROLE_NORMAL não encontrada")
                );

        user.setAcessos(List.of(normalRole));

        repository.save(user);
    }

    /*
    public void createUser(UserRequestDTO dto){
        Users u = new Users();
        u.setName(dto.name());
        u.setPassword(encoder.encode(dto.password()));
        List<String> rolList = new ArrayList<>();
        rolList.add(AccessType.ROLE_ADMIN.name());
        u.setAccess(accessRepository.findAll());
        repository.save(u);
    }

     */

    public void deleteUserById(String id){
        repository.deleteById(UUID.fromString(id));
    }
}
