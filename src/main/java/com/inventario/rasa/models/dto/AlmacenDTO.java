package com.inventario.rasa.models.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AlmacenDTO {
    
    @NotNull
    private String nombre;

    @NotNull
    private String ubicacion;
}
