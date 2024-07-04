package com.inventario.rasa.models.dto;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.inventario.rasa.auth.UsuarioDTO;
import com.inventario.rasa.models.Producto;


import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrdenSalidaDTO {
    
    private Long id;
    
    private String FechaSalida;

    @NotNull
    private UsuarioDTO usuario;

    @NotNull
    @Length(min= 3)
    private String destino;

    private String observacion;

    @NotNull
    private List<ProductoDTO> productos;
}
