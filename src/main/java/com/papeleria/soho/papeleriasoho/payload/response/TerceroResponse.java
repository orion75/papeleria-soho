package com.papeleria.soho.papeleriasoho.payload.response;

import lombok.Data;

@Data
public class TerceroResponse {
    private Long id;
    private String primerNombre;
    private String segundoNombre;
    private String primerApellido;
    private String segundoApellido;
    private String celular;
    private String direccion;
}
