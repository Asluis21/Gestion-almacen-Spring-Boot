package com.inventario.rasa.models.dto;

import com.inventario.rasa.models.Almacen;
import com.inventario.rasa.models.Categoria;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProductoDTO {
    
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

    @NotNull
    private Boolean estadoProducto;

    private Integer ultimaCantidadRetirada;

    @NotNull
    private Categoria categoria;

    @NotNull
    private Almacen almacen;

    private Boolean estado;
}
