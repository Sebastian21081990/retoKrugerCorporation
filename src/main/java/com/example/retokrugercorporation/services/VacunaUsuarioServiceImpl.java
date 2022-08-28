package com.example.retokrugercorporation.services;

import com.example.retokrugercorporation.dto.EmpleadoByRangoFechasDTO;
import com.example.retokrugercorporation.dto.EmpleadoByTipoVacunaDTO;
import com.example.retokrugercorporation.enums.EstadoVacuna;
import com.example.retokrugercorporation.model.Empleado;
import com.example.retokrugercorporation.model.TipoVacuna;
import com.example.retokrugercorporation.model.VacunaUsuario;
import com.example.retokrugercorporation.repository.VacunaUsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class VacunaUsuarioServiceImpl implements IVacunaUsuarioService {

    @Autowired
    private VacunaUsuarioRepository vacunaUsuarioRepository;
    @Autowired
    private ITipoVacunaService tipoVacunaService;

    private static final Logger LOGGER = LoggerFactory.getLogger(VacunaUsuarioServiceImpl.class);

    @Override
    @Transactional(readOnly = true)
    public List<VacunaUsuario> findAll() {
        return (List<VacunaUsuario>) vacunaUsuarioRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public VacunaUsuario findById(Long id) {
        return vacunaUsuarioRepository.findById(id)
                .orElse(null);
    }

    @Override
    @Transactional
    public VacunaUsuario save(VacunaUsuario vacunaUsuario) {
        return vacunaUsuarioRepository.save(vacunaUsuario);
    }

    @Override
    @Transactional
    public void delete(VacunaUsuario vacunaUsuario) {
        vacunaUsuarioRepository.delete(vacunaUsuario);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        vacunaUsuarioRepository.deleteById(id);
    }

    @Override
    @Transactional
    public List<VacunaUsuario> findByIdEmpleado(Empleado empleado) {

        try{
            return vacunaUsuarioRepository.findByEmpleado(empleado);
        }catch (Exception e){
            LOGGER.error("No se encontro registros");
            return new ArrayList<>();
        }

    }

    @Override
    @Transactional
    public void actualizar(List<VacunaUsuario> vacunaUsuarioList) {

        if(Objects.nonNull(vacunaUsuarioList) && !vacunaUsuarioList.isEmpty()){
            vacunaUsuarioList.forEach(this::save);
            LOGGER.info("Se actualizo las vacunas del empleado");
        }else {
            LOGGER.error("No se pudo actualizar la lista de vacunas del empleado");
        }

    }

    @Override
    @Transactional(readOnly = true)
    public List<EmpleadoByTipoVacunaDTO> filterByVaccinationType(Long idTipoVacuna) {

        TipoVacuna tipoVacuna = tipoVacunaService.findById(idTipoVacuna);
        List<VacunaUsuario> vacunaList = vacunaUsuarioRepository.filterByVaccinationType(tipoVacuna);

        List<EmpleadoByTipoVacunaDTO> empleadoByTipoVacunaDTOList = new ArrayList<>();

        vacunaList
                .forEach(vacuna -> empleadoByTipoVacunaDTOList.add(convertEmpleadoToEmpleadoByTipoVacunaDTO(vacuna)));

        return empleadoByTipoVacunaDTOList;

    }

    @Override
    @Transactional(readOnly = true)
    public List<EmpleadoByRangoFechasDTO> filterByVaccinationDateRange(Date fechaInicio, Date fechaFin) {

        List<VacunaUsuario> vacunaList = vacunaUsuarioRepository.filterByVaccinationDateRange(fechaInicio, fechaFin);

        List<EmpleadoByRangoFechasDTO> empleadoByTipoVacunaDTOList = new ArrayList<>();

        vacunaList
                .forEach(vacuna -> empleadoByTipoVacunaDTOList.add(convertEmpleadoToEmpleadoByRangoFechasDTO(vacuna)));

        return empleadoByTipoVacunaDTOList;

    }

    private EmpleadoByTipoVacunaDTO convertEmpleadoToEmpleadoByTipoVacunaDTO(VacunaUsuario vacunaUsuario) {

        Empleado empleado = vacunaUsuario.getEmpleado();
        TipoVacuna tipoVacuna = vacunaUsuario.getTipoVacuna();
        String estado = Boolean.TRUE.equals(empleado.getVacunado()) ?
                EstadoVacuna.VACUNADO.getEstado() : EstadoVacuna.SIN_VACUNA.getEstado();
        List<VacunaUsuario> vacunaUsuarioList = findByIdEmpleado(empleado);

        return EmpleadoByTipoVacunaDTO.builder()
                .cedula(empleado.getCedula())
                .nombres(empleado.getNombres())
                .apellidos(empleado.getApellidos())
                .correoElectronico(empleado.getCorreoElectronico())
                .fechaNacimiento(empleado.getFechaNacimiento())
                .direccionDomicilio(empleado.getDireccionDomicilio())
                .vacunado(estado)
                .tipoVacuna(tipoVacuna.getNombre())
                .nroDosis(vacunaUsuarioList.size())
                .build();

    }

    private EmpleadoByRangoFechasDTO convertEmpleadoToEmpleadoByRangoFechasDTO(VacunaUsuario vacunaUsuario) {

        Empleado empleado = vacunaUsuario.getEmpleado();
        TipoVacuna tipoVacuna = vacunaUsuario.getTipoVacuna();
        String estado = Boolean.TRUE.equals(empleado.getVacunado()) ?
                EstadoVacuna.VACUNADO.getEstado() : EstadoVacuna.SIN_VACUNA.getEstado();
        List<VacunaUsuario> vacunaUsuarioList = findByIdEmpleado(empleado);

        return EmpleadoByRangoFechasDTO.builder()
                .cedula(empleado.getCedula())
                .nombres(empleado.getNombres())
                .apellidos(empleado.getApellidos())
                .correoElectronico(empleado.getCorreoElectronico())
                .fechaNacimiento(empleado.getFechaNacimiento())
                .direccionDomicilio(empleado.getDireccionDomicilio())
                .vacunado(estado)
                .tipoVacuna(tipoVacuna.getNombre())
                .fechaVacunacion(vacunaUsuario.getFechaVacunacion())
                .nroDosis(vacunaUsuarioList.size())
                .build();

    }

}
