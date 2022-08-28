package com.example.retokrugercorporation.dto;

import com.example.retokrugercorporation.model.Empleado;
import com.example.retokrugercorporation.model.VacunaUsuario;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(description = "Este Dto representa los datos del cliente y las vacunas que le corresponden")
public class EmpleadoDTO {

    @ApiModelProperty(name = "Empleado", required = true, value = "Objeto empleado representa los datos")
    private Empleado empleado;
    @ApiModelProperty(name = "List<VacunaUsuario>", required = false, value = "Esta lista representa todas las vacunas")
    private List<VacunaUsuario> vacunaUsuarioList;

}
