package com.jotahdev.hire_hub.modules.candidate.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jotahdev.hire_hub.modules.candidate.CandidateEntity;
import com.jotahdev.hire_hub.modules.candidate.CandidateRepository;
import com.jotahdev.hire_hub.modules.candidate.UserFoundException;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/candidato")
public class CandidateController {

    @Autowired
    private CandidateRepository candidateRepository;

    @PostMapping("/")
    public CandidateEntity create(@Valid @RequestBody CandidateEntity candidateEntity) {
        this.candidateRepository.findByUsernameOrEmail(candidateEntity.getUsername(), candidateEntity.getEmail())
        .ifPresent((user) -> {
            throw new UserFoundException();
        });


        return this.candidateRepository.save(candidateEntity);
    }
    
}
