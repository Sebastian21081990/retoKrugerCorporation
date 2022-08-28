package com.example.retokrugercorporation.services;

import com.example.retokrugercorporation.dto.EmpleadoByEstadoVacunacionDTO;
import com.example.retokrugercorporation.dto.EmpleadoDTO;
import com.example.retokrugercorporation.enums.EstadoVacuna;
import com.example.retokrugercorporation.model.Empleado;
import com.example.retokrugercorporation.model.Usuario;
import com.example.retokrugercorporation.model.VacunaUsuario;
import com.example.retokrugercorporation.repository.EmpleadoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class EmpleadoServiceImpl implements IEmpleadoService {

    @Autowired
    private EmpleadoRepository empleadoRepository;
    @Autowired
    private IUsuarioService usuarioService;
    @Autowired
    private IVacunaUsuarioService vacunaUsuarioService;

    private static final Logger LOGGER = LoggerFactory.getLogger(EmpleadoServiceImpl.class);

    @Override
    @Transactional(readOnly = true)
    public List<Empleado> findAll() {
        return (List<Empleado>) empleadoRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Empleado findById(Long id) {
        return empleadoRepository.findById(id)
                .orElse(null);
    }

    @Override
    @Transactional
    public Empleado save(Empleado empleado) {
        return empleadoRepository.save(empleado);
    }

    @Override
    @Transactional
    public void delete(Empleado empleado) {
        empleadoRepository.delete(empleado);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        empleadoRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return empleadoRepository.existsById(id);
    }

    @Override
    @Transactional
    public EmpleadoDTO crear(@Valid Empleado empleado) {

        Empleado nuevoEmpleado = save(empleado);
        Usuario usuario = usuarioService.crear(nuevoEmpleado);

        nuevoEmpleado.setUsuario(usuario);
        nuevoEmpleado = save(nuevoEmpleado);

        return EmpleadoDTO.builder()
                .empleado(nuevoEmpleado)
                .vacunaUsuarioList(new ArrayList<>())
                .build();

    }

    @Override
    @Transactional
    public void eliminar(Empleado empleado) {

        if (Objects.nonNull(empleado) && existsById(empleado.getId())) {
            if (Objects.nonNull(empleado.getUsuario()) && usuarioService.existsById(empleado.getUsuario().getId())) {
                usuarioService.eliminar(empleado);
            }
            deleteById(empleado.getId());
            LOGGER.info("Empleado: {} eliminado", empleado.getId());
        } else {
            LOGGER.error("No se pudo eliminar, por que ese id no existe");
        }

    }

    @Override
    @Transactional
    public EmpleadoDTO actualizar(EmpleadoDTO empleadoDTO) {

        Empleado empleado = empleadoDTO.getEmpleado();

        if (Objects.nonNull(empleado)) {
            empleado = save(empleado);
            vacunaUsuarioService.actualizar(empleadoDTO);
            LOGGER.info("Empleado: {} actualizado", empleado.getId());
            return listarById(empleado.getId());
        } else {
            LOGGER.error("No se pudo actualizar, por que ese empleado es null");
            return null;
        }

    }

    @Override
    @Transactional(readOnly = true)
    public List<EmpleadoDTO> listarTodos() {

        List<Empleado> empleadoList = findAll();
        List<EmpleadoDTO> empleadoDTOList = new ArrayList<>();

        if (Objects.nonNull(empleadoList) && !empleadoList.isEmpty()) {

            empleadoList
                    .forEach(empleado -> {

                        EmpleadoDTO empleadoDTO = EmpleadoDTO.builder()
                                .empleado(empleado)
                                .vacunaUsuarioList(vacunaUsuarioService.findByIdEmpleado(empleado))
                                .build();

                        empleadoDTOList.add(empleadoDTO);

                    });

        }

        return empleadoDTOList;

    }

    @Override
    @Transactional
    public EmpleadoDTO listarById(Long id) {

        try {

            Empleado empleado = findById(id);

            return EmpleadoDTO.builder()
                    .empleado(empleado)
                    .vacunaUsuarioList(vacunaUsuarioService.findByIdEmpleado(empleado))
                    .build();

        } catch (Exception e) {
            LOGGER.error("No se encontro el empleado");
            return null;
        }

    }

    @Override
    @Transactional(readOnly = true)
    public List<EmpleadoByEstadoVacunacionDTO> filterByVaccinationState(Boolean estadoVacunacion) {

        List<Empleado> empleadoList = empleadoRepository.filterByVaccinationState(estadoVacunacion);
        List<EmpleadoByEstadoVacunacionDTO> empleadoByEstadoVacunacionDTOList = new ArrayList<>();

        empleadoList
                .forEach(empleado -> empleadoByEstadoVacunacionDTOList.add(convertEmpleadoToEmpleadoByEstadoVacunacionDTO(empleado)));

        return empleadoByEstadoVacunacionDTOList;

    }

    private EmpleadoByEstadoVacunacionDTO convertEmpleadoToEmpleadoByEstadoVacunacionDTO(Empleado empleado) {

        List<VacunaUsuario> vacunaUsuarioList = vacunaUsuarioService.findByIdEmpleado(empleado);

        return EmpleadoByEstadoVacunacionDTO.builder()
                .cedula(empleado.getCedula())
                .nombres(empleado.getNombres())
                .apellidos(empleado.getApellidos())
                .correoElectronico(empleado.getCorreoElectronico())
                .fechaNacimiento(empleado.getFechaNacimiento())
                .direccionDomicilio(empleado.getDireccionDomicilio())
                .vacunado(Boolean.TRUE.equals(empleado.getVacunado()) ? EstadoVacuna.VACUNADO.getEstado() : EstadoVacuna.SIN_VACUNA.getEstado())
                .nroDosis(vacunaUsuarioList.size())
                .build();

    }

}
