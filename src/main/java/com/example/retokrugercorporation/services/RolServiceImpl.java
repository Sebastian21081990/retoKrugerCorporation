package com.example.retokrugercorporation.services;

import com.example.retokrugercorporation.model.Rol;
import com.example.retokrugercorporation.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RolServiceImpl implements IRolService{

    @Autowired
    private RolRepository rolRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Rol> findAll() {
        return rolRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Rol findById(Long id) {
        return rolRepository.findById(id)
                .orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public Rol findByNombre(String nombre){
        return rolRepository.findByNombre(nombre);
    }

    @Override
    @Transactional
    public Rol save(Rol rol) {
        return rolRepository.save(rol);
    }

    @Override
    @Transactional
    public void delete(Rol rol) {
        rolRepository.delete(rol);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        rolRepository.deleteById(id);
    }

}
