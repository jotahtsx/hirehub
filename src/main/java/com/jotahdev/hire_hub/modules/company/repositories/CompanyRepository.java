package com.jotahdev.hire_hub.modules.company.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import com.jotahdev.hire_hub.modules.company.entities.CompanyEntity;

public interface CompanyRepository extends JpaRepository<CompanyEntity, UUID> {

    Optional<CompanyEntity> findByUsername(String username);

    Optional<CompanyEntity> findByEmail(String email);

    Optional<CompanyEntity> findByTaxId(String taxId);
}
