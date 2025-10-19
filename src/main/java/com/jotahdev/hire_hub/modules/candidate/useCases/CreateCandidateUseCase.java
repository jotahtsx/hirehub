package com.jotahdev.hire_hub.modules.candidate.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jotahdev.hire_hub.modules.candidate.CandidateEntity;
import com.jotahdev.hire_hub.modules.candidate.CandidateRepository;
import com.jotahdev.hire_hub.modules.candidate.UserFoundException;

@Service
public class CreateCandidateUseCase {
    
    @Autowired
    private CandidateRepository candidateRepository;

    public CandidateEntity execute(CandidateEntity candidateEntity) {
        this.candidateRepository.findByUsernameOrEmail(candidateEntity.getUsername(), candidateEntity.getEmail())
        .ifPresent((user) -> {
            throw new UserFoundException();
        });


        return this.candidateRepository.save(candidateEntity);
    }

}
