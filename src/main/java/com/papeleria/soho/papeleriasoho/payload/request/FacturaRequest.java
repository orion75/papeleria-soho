package com.papeleria.soho.papeleriasoho.payload.request;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class FacturaRequest {
    Long clienteId;
    Long vendedorId;
    Date fecha;
    List<ItemRequest> items;
}
