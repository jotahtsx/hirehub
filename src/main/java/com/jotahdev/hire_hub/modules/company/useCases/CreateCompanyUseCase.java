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

        this.companyRepository
        .findByUsernameOrEmail(companyEntity.getUsername(), companyEntity.getEmail())
        .ifPresent((user) -> {
            throw new UserFoundException();
        });

        return this.companyRepository.save(companyEntity);
    }



}
