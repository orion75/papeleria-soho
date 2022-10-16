package com.papeleria.soho.papeleriasoho.payload.response;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class ProductoResponse {
    private Long id;

    @NotBlank(message = "El campo es requerido.")
    private String nombre;

    @NotBlank(message = "El campo es requerido.")
    private String descripcion;
    private Double precioventa;
}
