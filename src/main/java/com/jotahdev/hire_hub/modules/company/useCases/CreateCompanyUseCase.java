package com.jotahdev.hire_hub.modules.company.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jotahdev.hire_hub.modules.candidate.UserFoundException;
import com.jotahdev.hire_hub.modules.company.entities.CompanyEntity;
import com.jotahdev.hire_hub.modules.company.repositories.CompanyRepository;

@Service
public class CreateCompanyUseCase {

    @Autowired
    private CompanyRepository companyRepository;

    public CompanyEntity execute(CompanyEntity companyEntity) {

        // Valida username duplicado
        companyRepository.findByUsernameOrEmail(companyEntity.getUsername(), companyEntity.getUsername())
                .ifPresent(existingUser -> {
                    throw new UserFoundException("Escolha outro nome de usuário, este já está em uso.");
                });

        // Valida email duplicado
        companyRepository.findByEmail(companyEntity.getEmail())
                .ifPresent(existingUser -> {
                    throw new UserFoundException("Este e-mail já está cadastrado. Tente outro.");
                });

        // Salva se estiver tudo certo
        return companyRepository.save(companyEntity);
    }
}
