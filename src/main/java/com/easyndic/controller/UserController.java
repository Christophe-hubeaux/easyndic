package com.easyndic.controller;

import com.easyndic.model.User;
import com.easyndic.repository.UserRepository;
import com.easyndic.model.Role;
import com.easyndic.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public UserController(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @GetMapping
    public Iterable<User> getAllUser() {
        return this.userRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable("id") Integer id) {
        return this.userRepository.findById(id);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        userRepository.save(user);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") Integer id, @RequestBody User user) {
        return userRepository.findById(id)
                .map(updatingUser -> {
                    updatingUser.setFirstName(user.getFirstName());
                    updatingUser.setLastName(user.getLastName());
                    updatingUser.setEmail(user.getEmail());
                    updatingUser.setPassword(user.getPassword());
                    User saved = userRepository.save(updatingUser);
                    return ResponseEntity.ok(saved);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/{user_id}/role/{role_id}")
    public ResponseEntity<String> addRoleToUser(@PathVariable Integer userId, @PathVariable Integer roleId) {
        Optional<User> userOpt = userRepository.findById(userId);
        Optional<Role> roleOpt = roleRepository.findById(roleId);

        if (userOpt.isPresent() && roleOpt.isPresent()) {
            User user = userOpt.get();
            user.getRoles().add(roleOpt.get());
            userRepository.save(user);
            return ResponseEntity.ok("Role added to user");

        } else {
            return ResponseEntity.badRequest().body("Role has not been added to the user");
        }
    }
}
