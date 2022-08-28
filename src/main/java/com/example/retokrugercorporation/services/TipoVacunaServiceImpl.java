package com.example.retokrugercorporation.services;

import com.example.retokrugercorporation.model.TipoVacuna;
import com.example.retokrugercorporation.repository.TipoVacunaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoVacunaServiceImpl implements ITipoVacunaService{

    @Autowired
    private TipoVacunaRepository tipoVacunaRepository;

    @Override
    public List<TipoVacuna> findAll() {
        return (List<TipoVacuna>) tipoVacunaRepository.findAll();
    }

    @Override
    public TipoVacuna findById(Long id) {
        return tipoVacunaRepository.findById(id)
                .orElse(null);
    }

    @Override
    public void save(TipoVacuna tipoVacuna) {
        tipoVacunaRepository.save(tipoVacuna);
    }

    @Override
    public void delete(TipoVacuna tipoVacuna) {
        tipoVacunaRepository.delete(tipoVacuna);
    }

    @Override
    public void deleteById(Long id) {
        tipoVacunaRepository.deleteById(id);
    }

}
