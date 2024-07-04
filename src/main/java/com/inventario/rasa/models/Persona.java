package com.inventario.rasa.models;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
// @AllArgsConstructor
@MappedSuperclass
public abstract class Persona {
    
    @NotNull
    private String nombre;
    
    @NotNull
    private String apellidoPaterno;
            
    @NotNull
    private String apellidoMaterno;

    @NotNull
    @Column(unique = true)
    private String dni;
    
}
