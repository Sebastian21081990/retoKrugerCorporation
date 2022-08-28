package com.example.retokrugercorporation.services;

import com.example.retokrugercorporation.model.Empleado;
import com.example.retokrugercorporation.model.RolUsuario;
import com.example.retokrugercorporation.model.Usuario;

import java.util.List;

public interface IRolUsuarioService {

    List<RolUsuario> findAll();

    RolUsuario findById(Long id);

    List<RolUsuario> findByIdEmpleado(Empleado empleado);

    RolUsuario save(RolUsuario rolUsuario);

    void delete(RolUsuario rolUsuario);

    void deleteById(Long id);

    List<RolUsuario> crearRolesByUsuario();

    void eliminarRolesByUsuario(List<RolUsuario> rolList);

}
