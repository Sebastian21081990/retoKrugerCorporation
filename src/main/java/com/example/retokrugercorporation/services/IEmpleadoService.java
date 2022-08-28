package com.example.retokrugercorporation.services;

import com.example.retokrugercorporation.dto.EmpleadoByEstadoVacunacionDTO;
import com.example.retokrugercorporation.dto.EmpleadoDTO;
import com.example.retokrugercorporation.model.Empleado;

import java.util.List;

public interface IEmpleadoService {

    List<Empleado> findAll();

    Empleado findById(Long id);

    Empleado save(Empleado empleado);

    void delete(Empleado empleado);

    void deleteById(Long id);

    boolean existsById(Long id);

    EmpleadoDTO crear(Empleado empleado);

    void eliminar(Empleado empleado);

    EmpleadoDTO actualizar(EmpleadoDTO empleadoDTO);

    List<EmpleadoDTO> listarTodos();

    EmpleadoDTO listarById(Long id);

    List<EmpleadoByEstadoVacunacionDTO> filterByVaccinationState(Boolean estadoVacunacion);

}
