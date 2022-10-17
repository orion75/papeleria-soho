package com.papeleria.soho.papeleriasoho.services;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.papeleria.soho.papeleriasoho.Exceptions.NotFoundException;
import com.papeleria.soho.papeleriasoho.models.ETipoTercero;
import com.papeleria.soho.papeleriasoho.models.Factura;
import com.papeleria.soho.papeleriasoho.models.Item;
import com.papeleria.soho.papeleriasoho.payload.request.FacturaRequest;
import com.papeleria.soho.papeleriasoho.payload.request.ItemRequest;
import com.papeleria.soho.papeleriasoho.payload.response.FacturaResponse;
import com.papeleria.soho.papeleriasoho.repository.FacturaRepository;
import com.papeleria.soho.papeleriasoho.repository.ProductoRepository;
import com.papeleria.soho.papeleriasoho.repository.TerceroRepository;

@Service
public class FacturaService implements IFacturaService {

    @Autowired
    FacturaRepository facturaRepository;
    @Autowired
    TerceroRepository terceroRepository;
    @Autowired
    ProductoRepository productoRepository;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<FacturaResponse> findAll() {
        var items = facturaRepository.findAll();
        Type listType = new TypeToken<List<FacturaResponse>>() {
        }.getType();
        return modelMapper.map(items, listType);
    }

    @Override
    public FacturaResponse findById(Long id) {
        var item = facturaRepository.findById(id);
        return modelMapper.map(item, FacturaResponse.class);
    }

    @Override
    public FacturaResponse save(FacturaRequest model) {
        var entitymodel = modelMapper.map(model, Factura.class);

        var cliente = terceroRepository.findById(model.getClienteId());
        if (!cliente.isPresent())
            throw new NotFoundException("Factura con id: " + model.getClienteId() + " no existe");
        if (cliente.get().getTipo() != ETipoTercero.CLIENTE)
            throw new NotFoundException("Factura con id: " + model.getClienteId() + " no existe");
        entitymodel.setCliente(cliente.get());

        var vendedor = terceroRepository.findById(model.getVendedorId());
        if (!vendedor.isPresent())
            throw new NotFoundException("Vendedor con id: " + model.getClienteId() + " no existe");
        if (vendedor.get().getTipo() != ETipoTercero.VENDEDOR)
            throw new NotFoundException("Vendedor con id: " + model.getClienteId() + " no existe");
        entitymodel.setVendedor(vendedor.get());

        if (model.getItems() == null)
            throw new NotFoundException("Factura no tiene productos");

        List<Item> itemsFactura = new ArrayList<>();
        for (ItemRequest item : model.getItems()) {
            item.setId(null);
            var itemFactura = modelMapper.map(item, Item.class);
            var producto = productoRepository.findById(item.getProductoId());
            if (!producto.isPresent())
                throw new NotFoundException("Producto con id: " + model.getClienteId() + " no existe");
            itemFactura.setProducto(producto.get());
            itemsFactura.add(itemFactura);
        }

        entitymodel.setItems(itemsFactura);
        var item = facturaRepository.save(entitymodel);
        return modelMapper.map(item, FacturaResponse.class);
    }

    @Override
    public FacturaResponse update(Long id, FacturaRequest model) {
        var entitymodel = facturaRepository.findById(id);
        if (!entitymodel.isPresent())
            throw new NotFoundException("Factura con id: " + id + " no existe");

        if (model.getClienteId() != entitymodel.get().getCliente().getId()) {
            var cliente = terceroRepository.findById(model.getClienteId());
            if (!cliente.isPresent())
                throw new NotFoundException("Cliente con id: " + model.getClienteId() + " no existe");
            if (cliente.get().getTipo() != ETipoTercero.CLIENTE)
                throw new NotFoundException("Cliente con id: " + model.getClienteId() + " no existe");
            entitymodel.get().setCliente(cliente.get());
        }

        if (model.getVendedorId() != entitymodel.get().getVendedor().getId()) {
            var vendedor = terceroRepository.findById(model.getVendedorId());
            if (!vendedor.isPresent())
                throw new NotFoundException("Vendedor con id: " + model.getClienteId() + " no existe");
            if (vendedor.get().getTipo() != ETipoTercero.VENDEDOR)
                throw new NotFoundException("Vendedor con id: " + model.getClienteId() + " no existe");
            entitymodel.get().setVendedor(vendedor.get());
        }

        var item = facturaRepository.save(entitymodel.get());
        return modelMapper.map(item, FacturaResponse.class);
    }

    @Override
    public void delete(Long id) {
        var item = facturaRepository.findById(id);
        if (!item.isPresent())
            throw new NotFoundException("Factura con id: " + id + " no existe");
        facturaRepository.deleteById(id);
    }

}
