package com.inventario.rasa.models;

import java.util.Date;
import java.util.List;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class OrdenEntrada {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date fechaEntrada = new Date();

    @Length(min= 3)
    @NotNull
    private String puntoLlegada;

    private String observacion;

    @NotNull
    private Boolean estado;

    @NotNull
    @ManyToOne
    private Proveedor proveedor;

    @NotNull
    @ManyToOne
    private Usuario usuario;

    @NotNull
    @OneToMany
    private List<Producto> productos;

}
