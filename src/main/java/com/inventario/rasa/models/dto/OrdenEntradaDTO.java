package com.inventario.rasa.models.dto;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.inventario.rasa.auth.UsuarioDTO;
import com.inventario.rasa.models.Producto;
import com.inventario.rasa.models.ProductoEntrada;
import com.inventario.rasa.models.Proveedor;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrdenEntradaDTO {
    
    private Long id;
    
    private String fechaEntrada;

    @Length(min= 3)
    @NotNull
    private String puntoLlegada;

    private String observacion;

    @NotNull
    private Proveedor proveedor;

    @NotNull
    private UsuarioDTO usuario;

    
    @NotNull
    private List<ProductoEntrada> productos;

}
