package com.freemarket.app.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class CadastroException extends RuntimeException {

    public CadastroException(String message) {
        super(message);
    }

}
