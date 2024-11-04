package com.benson.app.bank.Service;

import com.benson.app.bank.Entity.Roles;
import com.benson.app.bank.Entity.Users;
import com.benson.app.bank.Repo.UserRepo;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class DataInitializer {

    @Autowired
    private final UserRepo userRepo;

    @Autowired
    private final PasswordEncoder passwordEncoder;


    @PostConstruct
    public void init() {
        if (userRepo.findByUserName("admin") == null) {
            Users admin = new Users();
            admin.setUserName("admin");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRoles(new HashSet<>(Set.of(Roles.ROLE_ADMIN)));
            userRepo.save(admin);
        }

        if (userRepo.findByUserName("user") == null) {
            Users user = new Users();
            user.setUserName("user");
            user.setPassword(passwordEncoder.encode("user123"));
            user.setRoles(new HashSet<>(Set.of(Roles.ROLE_USER)));
            userRepo.save(user);
        }
    }
}

