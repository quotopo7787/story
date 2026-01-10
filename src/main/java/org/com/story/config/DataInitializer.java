package org.com.story.config;

import lombok.RequiredArgsConstructor;
import org.com.story.entity.Role;
import org.com.story.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class DataInitializer {

    private final RoleRepository roleRepository;

    @Bean
    CommandLineRunner initRoles() {
        return args -> {
            createRoleIfNotExists("ADMIN");
            createRoleIfNotExists("AUTHOR");
            createRoleIfNotExists("READER");
        };
    }

    private void createRoleIfNotExists(String roleName) {
        roleRepository.findByName(roleName)
                .orElseGet(() -> roleRepository.save(new Role(null, roleName)));
    }
}
