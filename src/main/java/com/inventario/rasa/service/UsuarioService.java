package com.inventario.rasa.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inventario.rasa.auth.UsuarioDTO;
import com.inventario.rasa.models.Rol;
import com.inventario.rasa.models.Usuario;
import com.inventario.rasa.repository.IRolRepository;
import com.inventario.rasa.repository.IUsuarioRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UsuarioService {
    
    private final IUsuarioRepository usuarioRepository;
    private final IRolRepository rolRepository;

    private UsuarioDTO usuarioAUsuarioDTO(Usuario usuario){

        UsuarioDTO userDTO = new UsuarioDTO();

        userDTO.setId(usuario.getId());
        userDTO.setUsername(usuario.getUsername());
        userDTO.setApellidoMaterno(usuario.getApellidoMaterno());
        userDTO.setApellidoPaterno(usuario.getApellidoPaterno());
        userDTO.setDni(usuario.getDni());
        userDTO.setNombre(usuario.getNombre());
        userDTO.setRol(usuario.getRol());

        return userDTO;
    }

    @Transactional(readOnly = true)
    public List<UsuarioDTO> listaUsuarios(){

        List<UsuarioDTO> usuarios = new ArrayList<>();

        usuarioRepository.findAll().forEach(user -> {
            UsuarioDTO userDTO = new UsuarioDTO();

            userDTO.setId(user.getId());
            userDTO.setUsername(user.getUsername());
            userDTO.setApellidoMaterno(user.getApellidoMaterno());
            userDTO.setApellidoPaterno(user.getApellidoPaterno());
            userDTO.setDni(user.getDni());
            userDTO.setNombre(user.getNombre());
            userDTO.setRol(user.getRol());
            userDTO.setEstado(user.getEstado());

            usuarios.add(userDTO);
        });

        return usuarios;
    }
    
    @Transactional(readOnly = true)
    public List<UsuarioDTO> listaUsuariosDisponible(){

        List<UsuarioDTO> usuarios = new ArrayList<>();

        usuarioRepository.findAll()
            .stream()
            .filter(e -> e.getEstado())
            .collect(Collectors.toList())
            .forEach(user -> {
            UsuarioDTO userDTO = new UsuarioDTO();

            userDTO.setId(user.getId());
            userDTO.setUsername(user.getUsername());
            userDTO.setApellidoMaterno(user.getApellidoMaterno());
            userDTO.setApellidoPaterno(user.getApellidoPaterno());
            userDTO.setDni(user.getDni());
            userDTO.setNombre(user.getNombre());
            userDTO.setRol(user.getRol());

            usuarios.add(userDTO);
        });

        return usuarios;
    }

    @Transactional
    public UsuarioDTO editarUsuarioRol(Long id, UsuarioDTO usuarioEditado){
        Rol rolEncontrado = rolRepository.findById(usuarioEditado.getRol().getId()).orElseThrow(() -> new EmptyResultDataAccessException("Rol no encontrado", 1));

        Usuario usuarioEncontrado = usuarioRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException("Usuario no encontrado", 1));
        usuarioEncontrado.setApellidoMaterno(usuarioEditado.getApellidoMaterno());
        usuarioEncontrado.setApellidoPaterno(usuarioEditado.getApellidoPaterno());
        usuarioEncontrado.setDni(usuarioEditado.getDni());
        usuarioEncontrado.setNombre(usuarioEditado.getNombre());
        usuarioEncontrado.setUsername(usuarioEditado.getUsername());
        usuarioEncontrado.setRol(rolEncontrado);
        usuarioEncontrado.setEstado(usuarioEditado.getEstado());

        return usuarioAUsuarioDTO(usuarioEncontrado);
    }

    @Transactional
    public UsuarioDTO editarUsuario(Long id, UsuarioDTO usuarioEditado){

        Usuario usuarioEncontrado = usuarioRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException("Usuario no encontrado", 1));

        usuarioEncontrado.setApellidoMaterno(usuarioEditado.getApellidoMaterno());
        usuarioEncontrado.setApellidoPaterno(usuarioEditado.getApellidoPaterno());
        usuarioEncontrado.setDni(usuarioEditado.getDni());
        usuarioEncontrado.setNombre(usuarioEditado.getNombre());
        usuarioEncontrado.setUsername(usuarioEditado.getUsername());
        usuarioEncontrado.setEstado(usuarioEditado.getEstado());

        return usuarioAUsuarioDTO(usuarioEncontrado);
    }

    @Transactional(readOnly = true)
    public UsuarioDTO encontrarUsuarioById(Long id){
        
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException("Usuario no encontrado", 1));

        return usuarioAUsuarioDTO(usuario);
    }

    @Transactional
    public UsuarioDTO eliminarUsuarioByIdLogico(Long id){
        
        Usuario usuarioEncontrado = usuarioRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException("Usuario no encontrado", 1));
        usuarioEncontrado.setEstado(false);

        // usuarioRepository.delete(usuarioEncontrado);

        return usuarioAUsuarioDTO(usuarioEncontrado);
    }
}
