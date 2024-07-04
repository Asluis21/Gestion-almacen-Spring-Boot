package com.inventario.rasa.models.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductoRetiroDTO {
    
    @NotNull
    private Long id;
    
    @NotNull
    @Min(1)
    private int cantidadRetiro;
}
