package com.inventario.rasa.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class ProductoRetiro {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String descripcion;

    @NotNull
    private String ubicacion;

    @NotNull
    private String peso;

    private String serie;

    @Min(0)
    @NotNull
    private Integer cantidad;

    @NotNull
    private Boolean estadoProducto;

    @NotNull
    private Boolean estado;

    @NotNull
    @ManyToOne
    private Categoria categoria;

    @NotNull
    @ManyToOne
    private Almacen almacen;
}
