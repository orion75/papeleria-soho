package com.papeleria.soho.papeleriasoho.models;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "facturas")
public class Factura {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    Tercero cliente;

    @ManyToOne()
    @JoinColumn(name = "vendedor_id", nullable = false)
    Tercero vendedor;
    
    @Temporal(TemporalType.DATE)
    Date fecha;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "factutura_id", nullable = false)
    List<Item> items;

    public Long getClienteId() { return cliente.getId(); }
    public String getClientNombre() {
        String item = cliente.getPrimerNombre();
        item += cliente.getSegundoNombre() == null ? "": " " + cliente.getSegundoNombre();
        item += " " + cliente.getPrimerApellido();
        item += cliente.getSegundoApellido() == null ? "": " " + cliente.getSegundoApellido();
        return item;
    }
    public String getDireccion() { return cliente.getDireccion(); }
    public Long getVendedorId() { return vendedor.getId(); }
    public String getVendedorNombre() { 
        String item = vendedor.getPrimerNombre();
        item += vendedor.getSegundoNombre() == null ? "": " " + vendedor.getSegundoNombre();
        item += " " + vendedor.getPrimerApellido();
        item += vendedor.getSegundoApellido() == null ? "": " " + vendedor.getSegundoApellido();
        return item;
    }


}
