package com.jotahdev.hire_hub.modules.candidate.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jotahdev.hire_hub.modules.candidate.entities.CandidateEntity;
import com.jotahdev.hire_hub.modules.candidate.repositories.CandidateRepository;
import com.jotahdev.hire_hub.modules.candidate.UserFoundException;

@Service
public class CreateCandidateUseCase {

    @Autowired
    private CandidateRepository candidateRepository;

    public CandidateEntity execute(CandidateEntity candidateEntity) {
        candidateRepository.findByUsername(candidateEntity.getUsername())
            .ifPresent(user -> { 
                throw new UserFoundException("Escolha outro nome de usuário, este já está em uso."); 
            });

        candidateRepository.findByEmail(candidateEntity.getEmail())
            .ifPresent(user -> { 
                throw new UserFoundException("Este e-mail já está cadastrado. Tente outro."); 
            });

        return candidateRepository.save(candidateEntity);
    }
}
