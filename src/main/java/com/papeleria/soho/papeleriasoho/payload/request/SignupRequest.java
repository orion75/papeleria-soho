package com.papeleria.soho.papeleriasoho.payload.request;

import java.util.Set;

import javax.validation.constraints.*;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequest {

    @NotBlank(message = "El campo es requerido.")
    @Size(min = 3, max = 20)
    private String username;

    @NotBlank(message = "El campo es requerido.")
    @Size(max = 50)
    @Email
    private String email;

    private Set<String> role;

    @NotBlank(message = "El campo es requerido.")
    @Size(min = 6, max = 40)
    private String password;

}