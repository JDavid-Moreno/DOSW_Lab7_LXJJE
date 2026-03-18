package edu.eci.dosw.TechCup.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class AuthenticationException extends RuntimeException {
    public static final String EMAIL_IN_USE = "Email already in use.";
    public AuthenticationException(String message) {
        super(message);
    }
}
