package com.jotahdev.hire_hub.modules.company.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jotahdev.hire_hub.modules.company.dto.AuthCompanyDTO;
import com.jotahdev.hire_hub.modules.company.repositories.CompanyRepository;

@Service
public class AuthCompanyUseCase {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void execute(AuthCompanyDTO authCompanyDTO) {

        var company = this.companyRepository.findByUsername(authCompanyDTO.getUsername())
            .orElseThrow(() -> new UsernameNotFoundException("A empresa solicitada não foi encontrada."));

        var passwordMatches = this.passwordEncoder.matches(authCompanyDTO.getPassword(), company.getPassword());

        if (!passwordMatches) {
            throw new BadCredentialsException("A senha informada está incorreta. Tente novamente.");
        }

        // Aqui você pode gerar o JWT ou retornar os dados da empresa autenticada
    }
}
