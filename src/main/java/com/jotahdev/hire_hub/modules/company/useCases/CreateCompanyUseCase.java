package com.jotahdev.hire_hub.modules.company.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jotahdev.hire_hub.modules.candidate.UserFoundException;
import com.jotahdev.hire_hub.modules.company.entities.CompanyEntity;
import com.jotahdev.hire_hub.modules.company.repositories.CompanyRepository;

@Service
public class CreateCompanyUseCase {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public CompanyEntity execute(CompanyEntity companyEntity) {

        companyRepository.findByUsername(companyEntity.getUsername())
                .ifPresent(existingUser -> {
                    throw new UserFoundException("Escolha outro nome de usuário, este já está em uso.");
                });

        companyRepository.findByEmail(companyEntity.getEmail())
                .ifPresent(existingUser -> {
                    throw new UserFoundException("Este e-mail já está cadastrado. Tente outro.");
                });

        companyRepository.findByTaxId(companyEntity.getTaxId())
                .ifPresent(existingCompany -> {
                    throw new UserFoundException("Este CNPJ já está cadastrado. Tente outro.");
                });

        companyRepository.findByUrl(companyEntity.getUrl())
                .ifPresent(existingCompany -> {
                    throw new UserFoundException("Esta URL já está cadastrada. Tente outra.");
                });

        // Criptografa a senha antes de salvar
        String encryptedPassword = passwordEncoder.encode(companyEntity.getPassword());
        companyEntity.setPassword(encryptedPassword);

        // Salva no banco e retorna
        return companyRepository.save(companyEntity);
    }
}
