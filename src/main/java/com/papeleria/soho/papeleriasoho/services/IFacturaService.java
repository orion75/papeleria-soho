package com.papeleria.soho.papeleriasoho.services;

import java.util.List;

import com.papeleria.soho.papeleriasoho.payload.request.FacturaRequest;
import com.papeleria.soho.papeleriasoho.payload.response.FacturaResponse;

public interface IFacturaService {
    List<FacturaResponse> findAll();
    FacturaResponse findById(Long id);
    FacturaResponse save(FacturaRequest model);
    FacturaResponse update(Long id, FacturaRequest model);
    void delete(Long id);
}
