package com.easyndic.data;

import com.easyndic.model.Condominium;
import com.easyndic.model.Role;
import com.easyndic.model.User;
import com.easyndic.repository.CondominiumRepository;
import com.easyndic.repository.RoleRepository;
import com.easyndic.repository.UserRepository;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import jakarta.transaction.Transactional;

import java.time.ZoneId;

@Component
public class DataSeeder {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CondominiumRepository condominiumRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    public DataSeeder(UserRepository userRepository, CondominiumRepository condominiumRepository) {
        this.userRepository = userRepository;
        this.condominiumRepository = condominiumRepository;
    }

    @Transactional
    public void seedData() {
        Faker faker = new Faker();
        // Créer 10 utilisateurs fictifs
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setFirstName(faker.name().firstName());
            user.setLastName(faker.name().lastName());
            user.setEmail(faker.internet().emailAddress());
            user.setPassword(faker.pokemon().name());
            userRepository.save(user);
        }

        // Créer 5 condominiums fictifs
        for (int i = 0; i < 5; i++) {
            Condominium condo = new Condominium();
            condo.setName(faker.company().name());
            condo.setStreet(faker.address().streetAddress());
            condo.setStreetNumber(faker.number().numberBetween(1, 200));
            condo.setZipcode(faker.number().numberBetween(10000, 99999));
            condo.setLocality(faker.address().city());
            condo.setCountry(faker.address().country());
            condo.setParcelsNumber(faker.number().numberBetween(5, 20));
            condo.setCreationDate(faker.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            condominiumRepository.save(condo);
        }

        // Créer 2 roles
        for (int i = 0; i < 2; i++) {
            Role role = new Role();
            role.setName(faker.superhero().name());
            role.setDescription(faker.superhero().descriptor());
            roleRepository.save(role);
        }
    }
}
