package com.example.retokrugercorporation.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "vacunas_usuarios")
public class VacunaUsuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "empleado_id")
    private Empleado empleado;

    @ManyToOne
    @JoinColumn(name = "tipoVacuna_id")
    private TipoVacuna tipoVacuna;

    @Column(name = "fecha_vacunacion")
    @Temporal(TemporalType.DATE)
    private Date fechaVacunacion;

    @Column(name = "nro_dosis")
    private Integer nroDosis;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        VacunaUsuario vacunaUsuario = (VacunaUsuario) o;
        return id != null && Objects.equals(id, vacunaUsuario.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
