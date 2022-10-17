package com.papeleria.soho.papeleriasoho.services;

import java.util.List;

import com.papeleria.soho.papeleriasoho.models.ETipoTercero;
import com.papeleria.soho.papeleriasoho.payload.request.TerceroRequest;
import com.papeleria.soho.papeleriasoho.payload.response.TerceroResponse;

public interface ITerceroService {
    List<TerceroResponse> findAll();
    List<TerceroResponse> findAll(ETipoTercero tipo);
    TerceroResponse findById(Long id);
    TerceroResponse findById(Long id, ETipoTercero tipo);
    TerceroResponse save(TerceroRequest terceroRequest, ETipoTercero tipo);
    TerceroResponse update(Long id, TerceroRequest terceroReques);
    void delete(Long id);
}
