package com.papeleria.soho.papeleriasoho.services;

import java.util.List;

import com.papeleria.soho.papeleriasoho.payload.request.ProductoRequest;
import com.papeleria.soho.papeleriasoho.payload.response.ProductoResponse;

public interface IProductoService {

    List<ProductoResponse> findAll();
    ProductoResponse findById(Long id);
    ProductoResponse save(ProductoRequest productoDto);
    ProductoResponse update(Long id, ProductoRequest productoDto);
    void delete(Long id);
}
