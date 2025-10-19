package com.jotahdev.hire_hub.modules.candidate;

public class UserFoundException extends RuntimeException {
    public UserFoundException() {
        super("Escolha outro nome de usuário, este já está cadastrado.");
    }
}
