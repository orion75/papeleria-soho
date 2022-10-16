package com.papeleria.soho.papeleriasoho.security;

import java.util.HashMap;
import java.util.Map;

// import javax.el.MethodNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.papeleria.soho.papeleriasoho.payload.response.Response;

@RestControllerAdvice
public class WebControllerConfig {

    private static final Logger logger = LoggerFactory.getLogger(WebControllerConfig.class);

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Response<?> handleValidateExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errores = new HashMap<String, String>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errores.put(fieldName, message);
        });
        var resp = new Response<Map<String, String>>(true, "Error al validar el objeto", errores);
        logger.error(ex.getMessage());
        return resp;
    }
    
    // @ResponseStatus(code = HttpStatus.NOT_FOUND)
    // @ExceptionHandler(MethodNotFoundException.class)
    // public Response<String> handleNotFoundExceptions(MethodNotFoundException ex) {
    //     logger.error(ex.getMessage(), ex);
    //     return new Response<String>(true, ex.getMessage());
    // }
}
