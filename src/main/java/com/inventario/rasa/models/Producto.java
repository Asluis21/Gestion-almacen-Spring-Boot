package com.inventario.rasa.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
public class Producto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 3)
    private String descripcion;

    @NotNull
    @Size(min = 3)
    private String ubicacion;

    @NotNull
    @Size(min = 3)
    private String peso;

    private String serie;

    @Min(0)
    @NotNull
    private Integer cantidad;
    
    @Min(0)
    private Integer cantidadEntrada;

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
