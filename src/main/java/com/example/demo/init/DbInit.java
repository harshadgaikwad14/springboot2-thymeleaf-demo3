package com.example.demo.init;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.model.entity.User;
import com.example.demo.repository.UserRepository;

@Service
public class DbInit implements CommandLineRunner {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public DbInit(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        // Delete all
        this.userRepository.deleteAll();

        // Crete users
        User user = new User("user",passwordEncoder.encode("user"),"USER","");
        User admin = new User("admin",passwordEncoder.encode("admin"),"ADMIN","ACCESS_TEST1,ACCESS_TEST2");
        User manager = new User("manager",passwordEncoder.encode("manager"),"MANAGER","ACCESS_TEST1");
        User read = new User("read",passwordEncoder.encode("read"),"READ","ACCESS_READ");
        User write = new User("write",passwordEncoder.encode("write"),"WRITE","ACCESS_READ,ACCESS_WRITE");
        User delete = new User("delete",passwordEncoder.encode("delete"),"DELETE","ACCESS_READ,ACCESS_WRITE,ACCESS_DELETE");

        List<User> users = Arrays.asList(user,admin,manager,read,write,delete);

        // Save to db
        this.userRepository.saveAll(users);
    }
}
