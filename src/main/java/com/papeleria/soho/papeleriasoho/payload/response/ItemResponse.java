package com.papeleria.soho.papeleriasoho.payload.response;

import lombok.Data;

@Data
public class ItemResponse {
    
    Long id;
    Integer cantidad;
    String descripcion;
    double valorUnitaro;
    double valorTotal;
}
