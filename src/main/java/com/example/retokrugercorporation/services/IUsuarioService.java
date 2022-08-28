package com.example.retokrugercorporation.services;

import com.example.retokrugercorporation.model.Empleado;
import com.example.retokrugercorporation.model.Usuario;

import java.util.List;

public interface IUsuarioService {

    List<Usuario> findAll();

    Usuario findById(Long id);

    Usuario save(Usuario usuario);

    void delete(Usuario usuario);

    void deleteById(Long id);

    boolean existsById(Long id);

    Usuario crear(Empleado empleado);

    void eliminar(Empleado empleado);

}
