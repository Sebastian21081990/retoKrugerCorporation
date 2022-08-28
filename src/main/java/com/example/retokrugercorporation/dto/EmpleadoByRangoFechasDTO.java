package com.example.retokrugercorporation.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(description = "Este dto representa los datos del empleado, si esta vacunado, cual fue el tipo de vacuna y el nro de dosis")
public class EmpleadoByRangoFechasDTO {

    @ApiModelProperty(name = "cedula", required = true, example = "1728173214", value = "Indica el nro de cédula")
    private String cedula;
    @ApiModelProperty(name = "nombre", required = true, example = "David", value = "Indica los nombres")
    private String nombres;
    @ApiModelProperty(name = "apellido", required = true, example = "Vivanco", value = "Indica los apellidos")
    private String apellidos;
    @ApiModelProperty(name = "correo electrónico", required = true, example = "correo@dominio.com", value = "Indica la dirección de correo")
    private String correoElectronico;
    @ApiModelProperty(name = "fecha nacimiento", required = false, value = "Indica la fecha de nacimiento")
    private Date fechaNacimiento;
    @ApiModelProperty(name = "dirección domicilio", required = false, value = "Indica dirección del domicilio")
    private String direccionDomicilio;
    @ApiModelProperty(name = "vacunado", required = false, value = "Indica si esta vacunado o no")
    private String vacunado;
    @ApiModelProperty(name = "nro dosis", required = false, value = "Indica el nro de dosis")
    private int nroDosis;
    @ApiModelProperty(name = "tipo vacuna", required = false, value = "Indica el tipo de vacuna")
    private String tipoVacuna;
    @ApiModelProperty(name = "fecha de vacuna", required = false, value = "Indica la fecha de la vacuna")
    private Date fechaVacunacion;

}
