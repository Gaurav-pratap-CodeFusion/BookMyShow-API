package com.gpcf.BookMyShow.bootstrap;

import com.gpcf.BookMyShow.Repository.RoleRepository;
import com.gpcf.BookMyShow.Repository.UserRepository;
import com.gpcf.BookMyShow.model.Role;
import com.gpcf.BookMyShow.model.UserModel;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class DataInitializer {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    public void createAdminIfNotExists() {

        Role adminRole = roleRepository.findByrole("ADMIN")
                .orElseThrow(() -> new RuntimeException("ADMIN role not found"));

        if (!userRepository.existsByEmail("gaurav.pratap.admin@bookmyshow.com")) {

            UserModel admin = new UserModel();
            admin.setName("Gaurav Pratap");
            admin.setEmail("gaurav.pratap.admin@bookmyshow.com");
            admin.setPassword(passwordEncoder.encode("Admin@9555"));
            admin.setPhoneNumber("9555189224");
            admin.setRoles(Set.of(adminRole));

            userRepository.save(admin);

            System.out.println("✅ Default ADMIN created");
        }
    }
}
