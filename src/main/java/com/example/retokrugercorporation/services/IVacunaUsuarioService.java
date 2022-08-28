package com.example.retokrugercorporation.services;

import com.example.retokrugercorporation.dto.EmpleadoByRangoFechasDTO;
import com.example.retokrugercorporation.dto.EmpleadoByTipoVacunaDTO;
import com.example.retokrugercorporation.model.Empleado;
import com.example.retokrugercorporation.model.VacunaUsuario;

import java.util.Date;
import java.util.List;

public interface IVacunaUsuarioService {

    List<VacunaUsuario> findAll();

    VacunaUsuario findById(Long id);

    List<VacunaUsuario> findByIdEmpleado(Empleado empleado);

    VacunaUsuario save(VacunaUsuario vacunaUsuario);

    void delete(VacunaUsuario vacunaUsuario);

    void deleteById(Long id);

    void actualizar(List<VacunaUsuario> vacunaUsuarioList);

    List<EmpleadoByTipoVacunaDTO> filterByVaccinationType(Long idTipoVacuna);

    List<EmpleadoByRangoFechasDTO> filterByVaccinationDateRange(Date fechaInicio, Date fechaFin);

}
