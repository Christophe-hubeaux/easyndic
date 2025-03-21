package com.easyndic.controller;

import com.easyndic.dto.CondominiumAcquisitionDto;
import com.easyndic.model.Condominium;
import com.easyndic.model.CondominiumOwner;
import com.easyndic.model.User;
import com.easyndic.repository.CondominiumOwnerRepository;
import com.easyndic.repository.CondominiumRepository;
import com.easyndic.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/newAcquisition")
public class CondominiumOwnerController {
    private final CondominiumOwnerRepository condominiumOwnerRepository;
    private final CondominiumRepository condominiumRepository;
    private final UserRepository userRepository;

    public CondominiumOwnerController(CondominiumOwnerRepository condominiumOwnerRepository, CondominiumRepository condominiumRepository, UserRepository userRepository) {
        this.condominiumOwnerRepository = condominiumOwnerRepository;
        this.condominiumRepository = condominiumRepository;
        this.userRepository = userRepository;
    }

    @PostMapping
    public ResponseEntity<String> addCondominiumToUser(@RequestBody CondominiumAcquisitionDto request) {
        Optional<User> userOpt = userRepository.findById(request.getUserId());
        Optional<Condominium> condominiumOpt = condominiumRepository.findById(request.getCondominiumId());

        if (userOpt.isPresent() && condominiumOpt.isPresent()) {
            User user = userOpt.get();
            Condominium condominium = condominiumOpt.get();

            // Logic to save the acquisition
            CondominiumOwner newAcquisition = new CondominiumOwner();
            newAcquisition.setUser(user);
            newAcquisition.setCondominium(condominium);
            newAcquisition.setAcquisitionDate(request.getAcquisitionDate());
            newAcquisition.setUnitShare(request.getUnitShare());
            condominiumOwnerRepository.save(newAcquisition);

            return ResponseEntity.ok("Acquisition successfully registered");
        } else {
            ResponseEntity.badRequest().build();
        }
        return null;
    }
}
