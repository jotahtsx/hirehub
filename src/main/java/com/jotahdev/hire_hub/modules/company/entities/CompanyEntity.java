package com.jotahdev.hire_hub.modules.company.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity(name = "company")
public class CompanyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "O nome de usuário é obrigatório.")
    @Size(min = 3, max = 20, message = "O nome de usuário deve ter entre {min} e {max} caracteres.")
    @Pattern(
        regexp = "^[a-zA-Z0-9._]+$",
        message = "O nome de usuário pode conter apenas letras, números, pontos e underlines, sem espaços."
    )
    private String username;

    @NotBlank(message = "O e-mail é obrigatório.")
    @Email(message = "Insira um e-mail válido.")
    private String email;

    @NotBlank(message = "A senha é obrigatória.")
    @Size(min = 8, max = 20, message = "A senha deve ter entre {min} e {max} caracteres.")
    private String password;

    @Pattern(
        regexp = "^(https?:\\/\\/)?([\\w\\-])+\\.([\\w\\-])+([\\w\\-\\./?%&=]*)?$",
        message = "A URL deve ser válida."
    )
    private String url;

    @NotBlank(message = "O CNPJ é obrigatório.")
    @Pattern(
        regexp = "^[0-9]{14}$",
        message = "O CNPJ deve conter 14 dígitos numéricos, sem pontos ou traços."
    )
    private String taxId;

    @NotBlank(message = "O nome da empresa é obrigatório.")
    @Size(min = 3, max = 100, message = "O nome da empresa deve ter entre {min} e {max} caracteres.")
    private String name;

    @Size(max = 500, message = "A descrição deve ter no máximo {max} caracteres.")
    private String description;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
