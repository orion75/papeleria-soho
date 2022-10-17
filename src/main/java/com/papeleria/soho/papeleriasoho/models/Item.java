package com.papeleria.soho.papeleriasoho.models;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    Long id;

    @Column
    Integer cantidad;

    @ManyToOne
    @JoinColumn(name = "producto_id", nullable = false)
    Producto producto;

    public String getDescripcion () {
        return producto.getDescripcion();
    }

    public double getValorUnitaro () {
        return producto.getPrecioventa();
    }

    public double getValorTotal () {
        return producto.getPrecioventa() * cantidad;
    }

}
