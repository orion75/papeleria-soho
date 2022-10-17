package com.papeleria.soho.papeleriasoho.payload.request;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductoRequest {

    @NotBlank(message = "El campo es requerido.")
    private String nombre;
    private String descripcion;
    private Double precioventa;
}
