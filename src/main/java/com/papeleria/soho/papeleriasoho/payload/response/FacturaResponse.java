package com.papeleria.soho.papeleriasoho.payload.response;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class FacturaResponse {

    private Long id;
    private Long clienteId;
    private String clientNombre;
    private String direccion;
    private Long vendedorId;
    private String vendedorNombre;
    // private TerceroResponse cliente;
    // private TerceroResponse vendedor;
    private Date fecha;
    List<ItemResponse> items;
}
