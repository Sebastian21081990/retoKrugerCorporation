package com.example.retokrugercorporation.controllers;

import com.example.retokrugercorporation.dto.EmpleadoByEstadoVacunacionDTO;
import com.example.retokrugercorporation.dto.EmpleadoByRangoFechasDTO;
import com.example.retokrugercorporation.dto.EmpleadoByTipoVacunaDTO;
import com.example.retokrugercorporation.dto.EmpleadoDTO;
import com.example.retokrugercorporation.model.Empleado;
import com.example.retokrugercorporation.services.IEmpleadoService;
import com.example.retokrugercorporation.services.IVacunaUsuarioService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Api(tags = "Empleado API")
@RestController
@RequestMapping("/api/empleado")
public class EmpleadoController {

    @Autowired
    private IEmpleadoService empleadoService;
    @Autowired
    private IVacunaUsuarioService vacunaUsuarioService;

    private static final Logger LOGGER = LoggerFactory.getLogger(EmpleadoController.class);

    @ApiOperation(value = "Lista todos los empleados registrados",
            notes = "Retorna 204 si no hay datos")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "No hay empleados registrados"),
            @ApiResponse(code = 500, message = "Internal error")
    })
    @GetMapping("/listar")
    public ResponseEntity<List<EmpleadoDTO>> findAll() {
        return ResponseEntity.ok(empleadoService.listarTodos());
    }

    @ApiOperation(value = "Retorna el empleado y la lista de vacunas",
            notes = "Retorna 204 si no hay datos")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "No existe ese empleado"),
            @ApiResponse(code = 500, message = "Internal error")
    })
    @GetMapping("/listar-por-id/{id}")
    public ResponseEntity<EmpleadoDTO> findById(@PathVariable Long id) {
        return Optional.ofNullable(empleadoService.listarById(id))
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @ApiOperation(value = "Crea el empleado y retorna los valores",
            notes = "Retorna 204 si no hay datos")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "No se pudo crear el empleado"),
            @ApiResponse(code = 500, message = "Internal error"),
            @ApiResponse(code = 200, message = "Se guardo correctamente")
    })

    @PostMapping("/guardar")
    public ResponseEntity<EmpleadoDTO> guardar(@RequestBody EmpleadoDTO empleadoDTO) {
        return new ResponseEntity<>(empleadoService.crear(empleadoDTO.getEmpleado()), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Actualiza los datos del empleado",
            notes = "Retorna 204 si no hay datos")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "No se pudo actualizar los datos del empleado"),
            @ApiResponse(code = 500, message = "Internal error")
    })
    @PutMapping("/actualizar")
    public ResponseEntity<EmpleadoDTO> actualizar(@Valid @RequestBody EmpleadoDTO empleadoDTO) {

        return Optional.ofNullable(empleadoService.actualizar(empleadoDTO))
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());

    }

    @ApiOperation(value = "Elimina el empleado",
            notes = "Retorna 204 si no hay datos")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "No se pudo eliminar el empleado"),
            @ApiResponse(code = 500, message = "Internal error")
    })
    @DeleteMapping("/eliminar")
    public ResponseEntity<?> delete(@RequestBody EmpleadoDTO empleadoDTO) {

        try {
            Empleado empleado = empleadoDTO.getEmpleado();
            empleadoService.eliminar(empleado);
        } catch (NullPointerException e) {
            LOGGER.error("No se pudo eliminar el empleado");
        }

        return ResponseEntity.ok().build();

    }

    @ApiOperation(value = "Lista los empleados por el estado de vacunación",
            notes = "Retorna 204 si no hay datos")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "No se encontro datos con esos filtros"),
            @ApiResponse(code = 500, message = "Internal error")
    })
    @GetMapping("/filtrar-por-estado-vacunacion/{estadoVacunacion}")
    public ResponseEntity<List<EmpleadoByEstadoVacunacionDTO>> filterByVaccinationState(@PathVariable Boolean estadoVacunacion) {
        List<EmpleadoByEstadoVacunacionDTO> empleadoList = empleadoService.filterByVaccinationState(estadoVacunacion);
        return ResponseEntity.ok(empleadoList);
    }

    @ApiOperation(value = "Lista lso empleados por el tipo de vacuna",
            notes = "Retorna 204 si no hay datos")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "No se encontro datos con esos filtros"),
            @ApiResponse(code = 500, message = "Internal error")
    })
    @GetMapping("/filtrar-por-tipo-vacunacion/{idTipoVacunacion}")
    public ResponseEntity<List<EmpleadoByTipoVacunaDTO>> filterByVaccinationType(@PathVariable Long idTipoVacunacion) {
        List<EmpleadoByTipoVacunaDTO> empleadoList = vacunaUsuarioService.filterByVaccinationType(idTipoVacunacion);
        return ResponseEntity.ok(empleadoList);
    }

    @ApiOperation(value = "Lista los empleados en un rango de fechas",
            notes = "Para el envio de fecha usar el formato dd-MM-yyyy")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "No se encontro datos con esos filtros"),
            @ApiResponse(code = 500, message = "Internal error")
    })
    @GetMapping("/filtrar-por-rango-fechas-vacunacion/{fInicio}/{fFin}")
    public ResponseEntity<List<EmpleadoByRangoFechasDTO>> filterByVaccinationDateRange(@PathVariable String fInicio,
                                                                                       @PathVariable String fFin) {
        try {
            Date fechaInicio = new SimpleDateFormat("dd-MM-yyyy").parse(fInicio);
            LocalDate localDateFechaFin = (new SimpleDateFormat("dd-MM-yyyy").parse(fFin)).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            Date fechaFin = Date.from(LocalDateTime.of(localDateFechaFin, LocalTime.of(23, 59, 59)).atZone(ZoneId.systemDefault()).toInstant());
            List<EmpleadoByRangoFechasDTO> empleadoList = vacunaUsuarioService.filterByVaccinationDateRange(fechaInicio, fechaFin);
            return ResponseEntity.ok(empleadoList);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}
