package com.inventario.rasa.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inventario.rasa.auth.UsuarioDTO;
import com.inventario.rasa.service.UsuarioService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping("/listar")
    public ResponseEntity<?> listarUsuarios(){

        Map<String, Object> response = new HashMap<String, Object>();

        List<UsuarioDTO> usuariosEncontrados = usuarioService.listaUsuarios();
        
        if(usuariosEncontrados.isEmpty()){
            response.put("message", "No se encontraron usuarios");
        }else{
            response.put("message", "Usuarios encontrados");
            response.put("usuarios", usuariosEncontrados);

            return ResponseEntity.ok(response);
        }

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
    }
    
    @GetMapping("/listar/disponible")
    public ResponseEntity<?> listarUsuariosDisponible(){

        Map<String, Object> response = new HashMap<String, Object>();

        List<UsuarioDTO> usuariosEncontrados = usuarioService.listaUsuariosDisponible();
        
        if(usuariosEncontrados.isEmpty()){
            response.put("message", "No se encontraron usuarios");
        }else{
            response.put("message", "Usuarios encontrados");
            response.put("usuarios", usuariosEncontrados);

            return ResponseEntity.ok(response);
        }

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/encontrar/{id}")
    public ResponseEntity<?> encontrarUsuario(@PathVariable Long id){
        
        Map<String, Object> response = new HashMap<>();

        try{

            response.put("usuario", usuarioService.encontrarUsuarioById(id));
            response.put("message", "Usuario encontrado");
            return ResponseEntity.ok(response);

        }catch(Exception e){

            response.put("message", "Error al encontrar usuario");
            response.put("exception", e.getMessage());
            
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("editar/{id}")
    public ResponseEntity<?> editarUsuario(@PathVariable Long id, @Valid @RequestBody UsuarioDTO usuario, BindingResult result){

        Map<String, Object> response = new HashMap<String, Object>();

        try{
            
            if(result.hasErrors()){

                result.getFieldErrors().forEach(error -> {
                    response.put(error.getField(), error.getDefaultMessage());
                });

                return new ResponseEntity<>(response, HttpStatus.UNPROCESSABLE_ENTITY);
            }

            UsuarioDTO usuarioEditado = usuarioService.editarUsuario(id, usuario);

            response.put("message", "Usuario " + usuarioEditado.getUsername() + " editado correctamente");
            return ResponseEntity.ok(response);

        }catch(Exception e){

            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        
    }
    
    @PutMapping("editarRol/{id}")
    public ResponseEntity<?> editarUsuarioRol(@PathVariable Long id, @Valid @RequestBody UsuarioDTO usuario, BindingResult result){

        Map<String, Object> response = new HashMap<String, Object>();

        try{
            
            if(result.hasErrors()){

                result.getFieldErrors().forEach(error -> {
                    response.put(error.getField(), error.getDefaultMessage());
                });

                return new ResponseEntity<>(response, HttpStatus.UNPROCESSABLE_ENTITY);
            }

            UsuarioDTO usuarioEditado = usuarioService.editarUsuarioRol(id, usuario);

            response.put("message", "Usuario " + usuarioEditado.getUsername() + " editado correctamente");
            return ResponseEntity.ok(response);

        }catch(Exception e){

            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        
    }

    @PutMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarUsuario(@PathVariable Long id){

        Map<String, Object> response = new HashMap<String, Object>();
        
        try{
            String name = usuarioService.eliminarUsuarioByIdLogico(id).getNombre();
            response.put("message", "Usuario " +  name + " eliminado correctamente");

            return ResponseEntity.ok(response);

        }catch(IllegalArgumentException e){
            response.put("message", "Usuario inválido");
        }catch(Exception e){
            response.put("message", e.getMessage());
        }

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
