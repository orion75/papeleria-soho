package com.papeleria.soho.papeleriasoho.services;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.papeleria.soho.papeleriasoho.Exceptions.NotFoundException;
import com.papeleria.soho.papeleriasoho.models.ETipoTercero;
import com.papeleria.soho.papeleriasoho.models.Tercero;
import com.papeleria.soho.papeleriasoho.payload.request.TerceroRequest;
import com.papeleria.soho.papeleriasoho.payload.response.TerceroResponse;
import com.papeleria.soho.papeleriasoho.repository.TerceroRepository;

@Service
public class TerceroService implements ITerceroService {
    @Autowired
    TerceroRepository terceroRepository;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<TerceroResponse> findAll() {
        var items = terceroRepository.findAll();
        Type listType = new TypeToken<List<TerceroResponse>>(){}.getType();
        return modelMapper.map(items, listType);
    }

    @Override
    public List<TerceroResponse> findAll(ETipoTercero tipo) {
        var items = terceroRepository.findByTipo(tipo);
        Type listType = new TypeToken<List<TerceroResponse>>(){}.getType();
        return modelMapper.map(items, listType);
    }

    @Override
    public TerceroResponse findById(Long id) {
        var item = terceroRepository.findById(id);
        if (!item.isPresent())
            throw new NotFoundException("Tercero con id: " + id  + " no existe");
        return modelMapper.map(item, TerceroResponse.class);
    }

    @Override
    public TerceroResponse findById(Long id, ETipoTercero tipo) {
        var item = terceroRepository.findById(id);
        if (!item.isPresent())
            throw new NotFoundException("Tercero con id: " + id  + " no existe");
        if (item.get().getTipo() != tipo)
            throw new NotFoundException("Tercero con id: " + id  + " no existe");
        return modelMapper.map(item, TerceroResponse.class);
    }

    @Override
    public TerceroResponse save(TerceroRequest terceroReques, ETipoTercero tipo) {
        Tercero item = modelMapper.map(terceroReques, Tercero.class);
        item.setTipo(tipo);
        item = terceroRepository.save(item);
        return modelMapper.map(item, TerceroResponse.class);
    }

    @Override
    public TerceroResponse update(Long id, TerceroRequest terceroReques) {
        var item = terceroRepository.findById(id);
        if (!item.isPresent())
            throw new NotFoundException("Tercero con id: " + id  + " no existe");
        item.get().setPrimerNombre(terceroReques.getPrimerNombre());
        item.get().setSegundoNombre(terceroReques.getSegundoNombre());
        item.get().setPrimerApellido(terceroReques.getPrimerApellido());
        item.get().setSegundoApellido(terceroReques.getSegundoApellido());
        item.get().setCelular(terceroReques.getCelular());
        item.get().setDireccion(terceroReques.getDireccion());
        return modelMapper.map(terceroRepository.save(item.get()), TerceroResponse.class);
    }

    @Override
    public void delete(Long id) {
        var item = Optional.ofNullable(terceroRepository.findById(id).orElse(null));
        if (!item.isPresent())
            throw new NotFoundException("Tercero con id: " + id  + " no existe");
        terceroRepository.delete(item.get());
    }
}