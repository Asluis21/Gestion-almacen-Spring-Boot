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
public class OrdenSalida {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    private Usuario usuario;

    @NotNull
    @Length(min= 3)
    private String destino;

    private Date fechaSalida = new Date();

    private String observacion;

    @NotNull
    private Boolean estado;

    @NotNull
    @OneToMany
    private List<ProductoRetiro> productos;
}
