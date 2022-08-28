package com.example.retokrugercorporation.repository;

import com.example.retokrugercorporation.model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolRepository extends JpaRepository<Rol, Long> {

    Rol findByNombre(String nombre);

}
