package com.inventario.rasa.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inventario.rasa.models.Rol;
import com.inventario.rasa.models.dto.RolDTO;
import com.inventario.rasa.service.RolService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;


@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/rol")
@CrossOrigin(maxAge = 3600, origins = "*")
public class RolController {
    

    private final RolService rolService;

    //LISTAR ROLES
    @GetMapping("/listar")
    public ResponseEntity<?> listarRol(){
        
        Map<String, Object> response = new HashMap<>();

        try{

            response.put("roles", rolService.listarRoles());
            response.put("message", "Roles listados correctamente");
            return ResponseEntity.ok(response);

        }catch(Exception e){

            response.put("message", "Error al listar roles");
            response.put("exception", e.getMessage());
            
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
    
    //LISTAR ROLES (ESTADO TRUE)
    @GetMapping("/listar/disponible")
    public ResponseEntity<?> listarRolDisponible(){
        
        Map<String, Object> response = new HashMap<>();

        try{

            response.put("roles", rolService.listarRolesEstadoTrue());
            response.put("message", "Roles disponibles listados correctamente");
            return ResponseEntity.ok(response);

        }catch(Exception e){

            response.put("message", "Error al listar roles");
            response.put("exception", e.getMessage());
            
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    //CREAR ROL
    @PostMapping("/crear")
    public ResponseEntity<?> crearRol(@Valid @RequestBody Rol rol, BindingResult result){

        Map<String, Object> response = new HashMap<>();

        try{

            if(result.hasErrors()){

                result.getFieldErrors().forEach(error -> {
                    response.put(error.getField(), error.getDefaultMessage());
                });

                return new ResponseEntity<>(response, HttpStatus.UNPROCESSABLE_ENTITY);
            }

            rolService.crearRol(rol);
            response.put("message", "Rol creado correctamente");
            return ResponseEntity.ok(response);

        }catch(Exception e){
            response.put("message", "Error al crear el rol");
            response.put("exception", e.getMessage());

            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
    
    }

    //ENCONTRAR ROL POR ID
    @GetMapping("/encontrar/{id}")
    public ResponseEntity<?> encontrarRol(@PathVariable long id){
        
        Map<String, Object> response = new HashMap<>();

        try{

            response.put("rol", rolService.encontraRolById(id));
            response.put("message", "Rol encontrado");
            return ResponseEntity.ok(response);

        }catch(Exception e){

            response.put("message", "Error al encontrar rol");
            response.put("exception", e.getMessage());
            
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
    }

    //EDITAR ROL
    @PutMapping("/editar/{id}")
    public ResponseEntity<?> editarRol(@PathVariable Long id, @Valid @RequestBody Rol rol, BindingResult result){
        
        Map<String, Object> response = new HashMap<>();

        try{

            if(result.hasErrors()){

                result.getFieldErrors().forEach(error -> {
                    response.put(error.getField(), error.getDefaultMessage());
                });

                return new ResponseEntity<>(response, HttpStatus.UNPROCESSABLE_ENTITY);
            }

            response.put("rol", rolService.editarRol(id, rol));
            response.put("message", "Rol modificado correctamente");
            return ResponseEntity.ok(response);

        }catch(Exception e){

            response.put("message", "Error al editar rol");
            response.put("exception", e.getMessage());
            
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
    }

    //ELIMINAR ROL
    @PutMapping("/eliminar/logico/{id}")
    public ResponseEntity<?> eliminarRolLogico(@PathVariable Long id){
        
        Map<String, Object> response = new HashMap<>();

        try{

            response.put("rol", rolService.eliminarRolByIdLogico(id));
            response.put("message", "Rol eliminado correctamente");
            return ResponseEntity.ok(response);

        }catch(Exception e){

            response.put("message", "Error al elimnar rol");
            response.put("exception", e.getMessage());
            
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

}
