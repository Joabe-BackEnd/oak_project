
package com.system.piratas.oak.infra;

import com.system.piratas.oak.enums.TipoAcesso;
import com.system.piratas.oak.model.Acesso;
import com.system.piratas.oak.model.Users;
import com.system.piratas.oak.repository.AccessRepository;
import com.system.piratas.oak.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final AccessRepository accessRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(
            UserRepository userRepository,
            AccessRepository accessRepository,
            PasswordEncoder passwordEncoder) {

        this.userRepository = userRepository;
        this.accessRepository = accessRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void run(String... args) {

        createRoles();
        createAdmin();
    }

    private void createRoles() {

        for (TipoAcesso role : TipoAcesso.values()) {

            if (accessRepository.findByRoles(role).isEmpty()) {

                Acesso acesso = new Acesso();
                acesso.setRoles(role);

                accessRepository.save(acesso);
            }
        }
    }

    private void createAdmin() {

        if (userRepository.findByName("jobs").isPresent()) {
            return;
        }

        Acesso adminRole = accessRepository
                .findByRoles(TipoAcesso.ROLE_ADMIN)
                .orElseThrow(() ->
                        new IllegalStateException(
                                "ROLE_ADMIN não encontrada!"
                        )
                );

        Users admin = new Users();

        admin.setName("jobs");
        admin.setPassword(passwordEncoder.encode("jobs"));
        admin.setAcessos(List.of(adminRole));

        userRepository.save(admin);
    }
}
