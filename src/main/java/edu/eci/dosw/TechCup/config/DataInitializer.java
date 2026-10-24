package edu.eci.dosw.TechCup.config;

import edu.eci.dosw.TechCup.entity.RoleEntity;
import edu.eci.dosw.TechCup.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initRoles(RoleRepository roleRepository) {
        return args -> {
            if (roleRepository.findByName("ROLE_JUGADOR").isEmpty()) {
                RoleEntity jugador = new RoleEntity();
                jugador.setName("ROLE_JUGADOR");
                roleRepository.save(jugador);
            }

            if (roleRepository.findByName("ROLE_ADMIN").isEmpty()) {
                RoleEntity admin = new RoleEntity();
                admin.setName("ROLE_ADMIN");
                roleRepository.save(admin);
            }
        };
    }
}