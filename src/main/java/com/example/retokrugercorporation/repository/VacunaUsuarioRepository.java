package com.example.retokrugercorporation.repository;

import com.example.retokrugercorporation.model.Empleado;
import com.example.retokrugercorporation.model.TipoVacuna;
import com.example.retokrugercorporation.model.VacunaUsuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface VacunaUsuarioRepository extends CrudRepository<VacunaUsuario, Long> {

    List<VacunaUsuario> findByEmpleado(Empleado empleado);

    @Query("select v from VacunaUsuario v where v.tipoVacuna=?1")
    List<VacunaUsuario> filterByVaccinationType(TipoVacuna tipoVacuna);

    @Query("select v from VacunaUsuario v where v.fechaVacunacion between ?1 and ?2")
    List<VacunaUsuario> filterByVaccinationDateRange(Date fechaInicio, Date fechaFin);

}
