package com.finance.dashboard;

import com.finance.dashboard.model.Role;
import com.finance.dashboard.model.User;
import com.finance.dashboard.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataSeeder(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.count() == 0) {
            User admin = new User("admin", passwordEncoder.encode("admin123"), Role.ADMIN);
            User analyst = new User("analyst", passwordEncoder.encode("analyst123"), Role.ANALYST);
            User viewer = new User("viewer", passwordEncoder.encode("viewer123"), Role.VIEWER);

            userRepository.save(admin);
            userRepository.save(analyst);
            userRepository.save(viewer);
        }
    }
}
