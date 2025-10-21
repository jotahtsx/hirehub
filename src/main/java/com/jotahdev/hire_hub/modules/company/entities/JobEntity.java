package com.jotahdev.hire_hub.modules.company.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity(name = "jobs")
@Data
public class JobEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "A descrição da vaga é obrigatória.")
    @Size(min = 20, max = 1000, message = "A descrição deve ter entre {min} e {max} caracteres.")
    private String description;

    @NotBlank(message = "Informe os benefícios oferecidos na vaga (ex: vale-refeição, plano de saúde, etc.).")
    @Size(max = 500, message = "Os benefícios devem ter no máximo {max} caracteres.")
    private String benefits;

    @NotBlank(message = "Informe o nível da vaga (ex: Júnior, Pleno ou Sênior).")
    @Size(max = 50, message = "O nível da vaga deve ter no máximo {max} caracteres.")
    private String level;

    
    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private CompanyEntity companyEntity;

    
    @Column(name = "company_id", insertable = false, updatable = false)
    private UUID companyId;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
