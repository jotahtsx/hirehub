package com.jotahdev.hire_hub.modules.candidate;

public class UserFoundException extends RuntimeException {

    public UserFoundException() {
        super("Usuário já cadastrado. Escolha outro nome de usuário ou e-mail.");
    }

    public UserFoundException(String message) {
        super(message);
    }
}
