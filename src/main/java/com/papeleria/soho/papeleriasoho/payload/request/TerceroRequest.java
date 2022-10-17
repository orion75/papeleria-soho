package com.papeleria.soho.papeleriasoho.payload.request;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class TerceroRequest {
    @NotBlank(message = "El campo es requerido.")
    private String primerNombre;
    private String segundoNombre;
    @NotBlank(message = "El campo es requerido.")
    private String primerApellido;
    private String segundoApellido;
    @NotBlank(message = "El campo es requerido.")
    private String celular;
    @NotBlank(message = "El campo es requerido.")
    private String direccion;
}
