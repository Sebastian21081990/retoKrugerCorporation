package com.example.retokrugercorporation.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Roles {

    ADMINISTRADOR("ADMINISTRADOR"),
    EMPLEADO("EMPLEADO");

    private String nombre;

}
