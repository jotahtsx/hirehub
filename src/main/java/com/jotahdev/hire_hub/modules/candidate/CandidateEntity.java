package com.jotahdev.hire_hub.modules.candidate;

import java.util.UUID;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CandidateEntity {

    private UUID id;

    @NotBlank(message = "O nome é obrigatório.")
    @Size(min = 3, max = 100, message = "O nome deve ter entre {min} e {max} caracteres.")
    private String name;

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

    @Size(max = 500, message = "A descrição deve ter no máximo {max} caracteres.")
    private String description;

    private String resume;
}
