package com.example.retokrugercorporation.repository;

import com.example.retokrugercorporation.model.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
    boolean existsById(Long idUsuario);
}
