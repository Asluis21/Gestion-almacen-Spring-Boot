package com.inventario.rasa.models.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class RolDTO {

    private Long id;
    
    @NotNull
    private String nombre;
}
