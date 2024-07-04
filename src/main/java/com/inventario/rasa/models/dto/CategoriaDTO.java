package com.inventario.rasa.models.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CategoriaDTO{
    
    @NotNull
    private String nombre;
}
