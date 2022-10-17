package com.papeleria.soho.papeleriasoho.models;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "terceros")
public class Tercero {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 12, nullable = false)
    private ETipoTercero tipo;

    @Column(length = 30, nullable = false)
    private String primerNombre;

    @Column(length = 30)
    private String segundoNombre;

    @Column(length = 30, nullable = false)
    private String primerApellido;

    @Column(length = 30)
    private String segundoApellido;

    @Column(length = 10)
    private String celular;
    
    @Column(length = 160)
    private String direccion;
}
