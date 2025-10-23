package com.jotahdev.hire_hub.modules.company.useCases;

import java.time.Duration;
import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.jotahdev.hire_hub.modules.company.dto.AuthCompanyDTO;
import com.jotahdev.hire_hub.modules.company.repositories.CompanyRepository;

@Service
public class AuthCompanyUseCase {

    @Value("${security.token.secret}")
    private String secretKey;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String execute(AuthCompanyDTO authCompanyDTO) {
        
        var company = this.companyRepository.findByUsername(authCompanyDTO.getUsername())
            .orElseThrow(() -> new UsernameNotFoundException(
                "Nenhuma empresa foi encontrada com este nome de usuário."
            ));
        
        boolean passwordMatches = this.passwordEncoder.matches(
            authCompanyDTO.getPassword(), company.getPassword()
        );

        if (!passwordMatches) {
            throw new BadCredentialsException("Senha incorreta. Verifique suas credenciais e tente novamente.");
        }
        
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        return JWT.create()
            .withIssuer("hirehub")
            .withSubject(company.getId().toString())
            .withClaim("username", company.getUsername())
            .withClaim("email", company.getEmail())
            .withExpiresAt(Instant.now().plus(Duration.ofHours(2)))
            .sign(algorithm);
    }
}
