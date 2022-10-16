package com.papeleria.soho.papeleriasoho.models;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "productos")
public class Producto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(length = 60, nullable = false)
    private String nombre;

    @Column(length = 160, nullable = true)
    private String descripcion;

    private Double precioventa;

    public Producto(String nombre, Double precioventa) {
        this.nombre = nombre;
        this.precioventa = precioventa;
    }

    public Producto() {}
}
