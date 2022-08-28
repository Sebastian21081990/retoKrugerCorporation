package com.example.retokrugercorporation.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@Builder
@Entity
@Table(name = "empleados")
public class Empleado implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "La cédula no puede estar vacio")
    @Pattern(regexp = "\\d{10}", message = "La cédula debe ser un valor númerico de 10 digitos")
    private String cedula;

    @NotEmpty(message = "El nombre no puede estar vacio")
    @Pattern(regexp = "^[A-Za-z0-9 ]+$", message = "El nombre no puede tener caracteres especiales")
    private String nombres;

    @NotEmpty(message = "El apellido no puede estar vacio")
    @Pattern(regexp = "^[A-Za-z0-9 ]+$", message = "El apellido no puede tener caracteres especiales")
    private String apellidos;

    @NotEmpty(message = "El correo no puede estar vacio")
    @Email(message = "El formato del correo no es el correcto")
    @Column(name = "correo")
    private String correoElectronico;

    @Column(name = "fecha_nacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;

    @Column(name = "direccion_domicilio")
    private String direccionDomicilio;

    private Boolean vacunado;

    @ManyToOne
    private Usuario usuario;

    public Empleado(){
      //
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Empleado empleado = (Empleado) o;
        return id != null && Objects.equals(id, empleado.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
