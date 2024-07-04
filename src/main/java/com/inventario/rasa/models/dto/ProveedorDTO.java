package com.inventario.rasa.models.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProveedorDTO {
    
    @NotNull
    private String nombre;
    
    @NotNull
    @Size(min = 11, max =11)
    private String ruc;
}
