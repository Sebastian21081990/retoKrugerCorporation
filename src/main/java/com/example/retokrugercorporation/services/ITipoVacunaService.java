package com.example.retokrugercorporation.services;

import com.example.retokrugercorporation.model.TipoVacuna;

import java.util.List;

public interface ITipoVacunaService {

    List<TipoVacuna> findAll();

    TipoVacuna findById(Long id);

    void save(TipoVacuna tipoVacuna);

    void delete(TipoVacuna tipoVacuna);

    void deleteById(Long id);

}
