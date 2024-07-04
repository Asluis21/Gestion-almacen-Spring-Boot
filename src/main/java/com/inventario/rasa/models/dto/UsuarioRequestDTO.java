package com.inventario.rasa.models.dto;

import com.inventario.rasa.models.Rol;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UsuarioRequestDTO {
    
    @NotNull
    private String nombre;

    @NotNull
    @Size(min = 4)
    private String username;
    
    @Size(min = 8, max = 8)
    private String dni;

    @NotNull
    private String apellidoPaterno;

    @NotNull
    private String apellidoMaterno;
    
    @NotNull
    private Boolean estado;

    @NotNull
    @Size(min = 3)
    private String password;

    private Rol rol;
}
