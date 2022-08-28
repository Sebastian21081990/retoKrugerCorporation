package com.example.retokrugercorporation.services;

import com.example.retokrugercorporation.enums.Roles;
import com.example.retokrugercorporation.model.Empleado;
import com.example.retokrugercorporation.model.Rol;
import com.example.retokrugercorporation.model.RolUsuario;
import com.example.retokrugercorporation.model.Usuario;
import com.example.retokrugercorporation.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private IRolUsuarioService rolUsuarioService;

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> findAll() {
        return (List<Usuario>) usuarioRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario findById(Long id) {
        return usuarioRepository.findById(id)
                .orElse(null);
    }

    @Override
    @Transactional
    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    @Transactional
    public void delete(Usuario usuario) {
        usuarioRepository.delete(usuario);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return usuarioRepository.existsById(id);
    }

    @Override
    @Transactional
    public Usuario crear(Empleado empleado) {

        List<RolUsuario> rolUsuarioList = rolUsuarioService.crearRolesByUsuario();

        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setUsername(empleado.getCedula());
        nuevoUsuario.setPassword(empleado.getCedula());
        nuevoUsuario.setEstado(true);
        nuevoUsuario.setRolList(rolUsuarioList);

        return save(nuevoUsuario);

    }

    @Override
    @Transactional
    public void eliminar(Empleado empleado) {

        Usuario usuario = empleado.getUsuario();
        /**List<RolUsuario> rolList = usuario.getRolList();

        if(!usuario.getRolList().isEmpty()){
            rolUsuarioService.eliminarRolesByUsuario(rolList);
        }

        deleteById(usuario.getId());*/

        delete(usuario);

    }

}
