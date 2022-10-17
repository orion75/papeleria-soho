package com.papeleria.soho.papeleriasoho.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.papeleria.soho.papeleriasoho.models.Factura;

@Repository
public interface FacturaRepository extends JpaRepository<Factura, Long> {
    
}
