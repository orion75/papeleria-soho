package com.papeleria.soho.papeleriasoho.payload.response;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class ProductoResponse {
    private Long id;
    private String nombre;
    private String descripcion;
    private Double precioventa;
}
