package com.inventario.rasa.auth;

import com.inventario.rasa.models.Rol;
import com.inventario.rasa.models.Usuario;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UsuarioDTO {
    
    private Long id;

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

    // @NotNull
    private Rol rol;

    public static UsuarioDTO usuarioAusuarioDto(Usuario user){

        UsuarioDTO userDTO = new UsuarioDTO();

        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setApellidoMaterno(user.getApellidoMaterno());
        userDTO.setApellidoPaterno(user.getApellidoPaterno());
        userDTO.setDni(user.getDni());
        userDTO.setNombre(user.getNombre());
        userDTO.setRol(user.getRol());

        return userDTO;
    }
}
