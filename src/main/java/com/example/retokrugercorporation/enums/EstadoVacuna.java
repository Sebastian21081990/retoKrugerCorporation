package com.example.retokrugercorporation.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum EstadoVacuna {

    VACUNADO("Vacunado"),
    SIN_VACUNA("Sin Vacuna");

    private String estado;

}
