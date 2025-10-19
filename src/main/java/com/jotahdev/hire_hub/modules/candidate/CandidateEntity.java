package com.jotahdev.hire_hub.modules.candidate;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(
    name = "candidates",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = "username"),
        @UniqueConstraint(columnNames = "email")
    }
)
public class CandidateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "O nome é obrigatório.")
    @Size(min = 3, max = 100, message = "O nome deve ter entre {min} e {max} caracteres.")
    @Column(nullable = false, length = 100)
    private String name;

    @NotBlank(message = "O nome de usuário é obrigatório.")
    @Size(min = 3, max = 20, message = "O nome de usuário deve ter entre {min} e {max} caracteres.")
    @Pattern(
        regexp = "^[a-zA-Z0-9._]+$",
        message = "O nome de usuário pode conter apenas letras, números, pontos e underlines, sem espaços."
    )
    @Column(nullable = false, length = 20)
    private String username;

    @NotBlank(message = "O e-mail é obrigatório.")
    @Email(message = "Insira um e-mail válido.")
    @Column(nullable = false)
    private String email;

    @NotBlank(message = "A senha é obrigatória.")
    @Size(min = 8, max = 20, message = "A senha deve ter entre {min} e {max} caracteres.")
    @Column(nullable = false, length = 120)
    private String password;

    @Size(max = 500, message = "A descrição deve ter no máximo {max} caracteres.")
    @Column(length = 500)
    private String description;

    @Column(name = "resume_path")
    private String resume;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
