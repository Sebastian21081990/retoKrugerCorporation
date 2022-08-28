package com.example.retokrugercorporation.repository;

import com.example.retokrugercorporation.model.Empleado;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmpleadoRepository extends CrudRepository<Empleado, Long> {

    @Query("select e from Empleado e where e.vacunado=?1")
    List<Empleado> filterByVaccinationState(Boolean estadoVacunacion);

    boolean existsById(Long id);

}
