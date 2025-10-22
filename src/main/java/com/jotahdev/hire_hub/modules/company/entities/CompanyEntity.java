package com.jotahdev.hire_hub.modules.company.entities;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "companies")
public class CompanyEntity {

    @Id
    @GeneratedValue
    private UUID id;

    @NotBlank(message = "O Usuário da empresa é obrigatório.")
    @Column(unique = true, nullable = false)
    private String username;

    @NotBlank(message = "A Senha é obrigatória.")
    @Column(nullable = false)
    private String password;

    @NotBlank(message = "O Email é obrigatório.")
    @Email(message = "O Email inválido.")
    @Column(unique = true, nullable = false)
    private String email;

    @NotBlank(message = "O Nome é obrigatório.")
    private String name;

    @NotBlank(message = "A URL é obrigatória")
    @Column(unique = true, nullable = false)
    private String url;

    @NotBlank(message = "O CNPJ é obrigatório.")
    @Size(min = 14, max = 14, message = "O CNPJ deve ter 14 dígitos sem pontuação")
    @Column(unique = true, nullable = false)
    private String taxId;

    private String description;

    // ======== GETTERS E SETTERS ========
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }

    public String getTaxId() { return taxId; }
    public void setTaxId(String taxId) { this.taxId = taxId; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
