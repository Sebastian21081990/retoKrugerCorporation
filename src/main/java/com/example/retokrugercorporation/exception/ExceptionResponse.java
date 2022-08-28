package com.example.retokrugercorporation.exception;

import lombok.*;

import java.util.Date;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExceptionResponse {

    private Date timestamp;
    private String mensaje;
    private String detalles;
    private Map<String, String> erroresValidacion;

}
