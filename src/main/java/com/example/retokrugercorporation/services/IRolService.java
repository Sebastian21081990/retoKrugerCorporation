package com.example.retokrugercorporation.services;

import com.example.retokrugercorporation.model.Rol;

import java.util.List;

public interface IRolService {

    List<Rol> findAll();

    Rol findById(Long id);

    Rol findByNombre(String nombre);

    Rol save(Rol rol);

    void delete(Rol rol);

    void deleteById(Long id);

}
