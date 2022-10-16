package com.papeleria.soho.papeleriasoho.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.papeleria.soho.papeleriasoho.models.ETipoTercero;
import com.papeleria.soho.papeleriasoho.models.Tercero;

@Repository
public interface TerceroRepository extends JpaRepository<Tercero, Long> {
    List<Tercero> findByTipo(ETipoTercero tipo);

}
