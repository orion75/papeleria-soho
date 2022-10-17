package com.papeleria.soho.papeleriasoho.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.papeleria.soho.papeleriasoho.Exceptions.NotFoundException;
import com.papeleria.soho.papeleriasoho.models.Producto;
import com.papeleria.soho.papeleriasoho.payload.request.ProductoRequest;
import com.papeleria.soho.papeleriasoho.payload.response.ProductoResponse;
import com.papeleria.soho.papeleriasoho.repository.ProductoRepository;

@Service
public class ProductoService implements IProductoService  {

    @Autowired
    ModelMapper modelMapper;
    @Autowired
    ProductoRepository productoRepository;

    @Override
    public List<ProductoResponse> findAll() {
        var items = productoRepository.findAll();
        List<ProductoResponse> itemsDto = new ArrayList<>();
        for(Producto producto : items) {
            var itemDto = modelMapper.map(producto, ProductoResponse.class);
            itemsDto.add(itemDto);
        }
        return itemsDto;
    }

    @Override
    public ProductoResponse findById(Long id) {
        var item = productoRepository.findById(id);
        if (!item.isPresent())
            throw new NotFoundException("Producto con id: " + id + " no existe");
        return modelMapper.map(item, ProductoResponse.class);
    }

    @Override
    public ProductoResponse save(ProductoRequest productoDto) {
        var item = productoRepository.save(modelMapper.map(productoDto, Producto.class));
        return modelMapper.map(item, ProductoResponse.class);
    }

    @Override
    public ProductoResponse update(Long id, ProductoRequest productoUpdateDto) {
        var item = Optional.ofNullable(productoRepository.findById(id).orElse(null));
        if (!item.isPresent())
            throw new NotFoundException("Producto con id: " + id  + " no existe");
        var productomodel = modelMapper.map(productoUpdateDto, Producto.class);
        productomodel.setId(item.get().getId());
        productomodel = productoRepository.save(productomodel);
        return modelMapper.map(productomodel, ProductoResponse.class);
    }

    @Override
    public void delete(Long id) {
        var item = Optional.ofNullable(productoRepository.findById(id).orElse(null));
        if (!item.isPresent())
            throw new NotFoundException("Producto con id: " + id  + " no existe");
        productoRepository.delete(item.get());
    }
}
