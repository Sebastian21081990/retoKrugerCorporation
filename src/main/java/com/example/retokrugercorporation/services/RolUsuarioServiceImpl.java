package com.example.retokrugercorporation.services;
import com.example.retokrugercorporation.enums.Roles;
import com.example.retokrugercorporation.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class RolUsuarioServiceImpl implements IRolUsuarioService {

    @Autowired
    private IRolUsuarioService rolUsuarioService;
    @Autowired
    private IRolService rolService;

    private static final Logger LOGGER = LoggerFactory.getLogger(RolUsuarioServiceImpl.class);

    @Override
    @Transactional(readOnly = true)
    public List<RolUsuario> findAll() {
        return rolUsuarioService.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public RolUsuario findById(Long id) {
        return rolUsuarioService.findById(id);
    }

    @Override
    @Transactional
    public RolUsuario save(RolUsuario rolUsuario) {
        return rolUsuarioService.save(rolUsuario);
    }

    @Override
    @Transactional
    public void delete(RolUsuario rolUsuario) {
        rolUsuarioService.delete(rolUsuario);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        rolUsuarioService.deleteById(id);
    }

    @Override
    @Transactional
    public List<RolUsuario> crearRolesByUsuario() {

        try {

            List<Rol> rolList = Stream.of(rolService.findByNombre(Roles.EMPLEADO.getNombre()))
                    .collect(Collectors.toList());

            List<RolUsuario> rolUsuarioList = new ArrayList<>();

            rolList.forEach(rol -> {

                RolUsuario rolUsuario = new RolUsuario();
                rolUsuario.setRol(rol);
                rolUsuario.setEstado(true);

                rolUsuarioList.add(rolUsuario);

            });

            return rolUsuarioList;

        } catch (Exception e) {
            LOGGER.error("No se pudo registrar los roles del usuario");
            return new ArrayList<>();
        }

    }

    @Override
    public void eliminarRolesByUsuario(List<RolUsuario> rolList) {
        rolList.forEach(this::delete);
    }

    @Override
    @Transactional
    public List<RolUsuario> findByIdEmpleado(Empleado empleado) {

        try {
            return rolUsuarioService.findByIdEmpleado(empleado);
        } catch (Exception e) {
            LOGGER.error("No se encontro registros");
            return new ArrayList<>();
        }

    }

}
