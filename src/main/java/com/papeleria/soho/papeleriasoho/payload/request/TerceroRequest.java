package com.papeleria.soho.papeleriasoho.payload.request;

import lombok.Data;

@Data
public class TerceroRequest {
    private String primerNombre;
    private String segundoNombre;
    private String primerApellido;
    private String segundoApellido;
    private String celular;
    private String direccion;
}
