package com.papeleria.soho.papeleriasoho.payload.request;

import lombok.Data;

@Data
public class ItemRequest {
    Long id;
    Integer cantidad;
    Long productoId;
}
