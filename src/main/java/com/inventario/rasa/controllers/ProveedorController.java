package com.inventario.rasa.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inventario.rasa.models.Proveedor;
import com.inventario.rasa.models.dto.ProveedorDTO;
import com.inventario.rasa.service.ProveedorService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/proveedor")
@RequiredArgsConstructor
@CrossOrigin(maxAge = 3600, origins = "*")
public class ProveedorController {
    
    private final ProveedorService proveedorService;

    @GetMapping("/listar")
    public ResponseEntity<?> listarProveedor(){
        
        Map<String, Object> response = new HashMap<>();

        try{

            response.put("proveedor", proveedorService.listar());
            response.put("message", "Proveedores listados correctamente");
            return ResponseEntity.ok(response);

        }catch(Exception e){

            response.put("message", "Error al listar proveedores");
            response.put("exception", e.getMessage());
            
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
    }
    
    @GetMapping("/listar/disponible")
    public ResponseEntity<?> listarProveedorDisponible(){
        
        Map<String, Object> response = new HashMap<>();

        try{

            response.put("proveedor", proveedorService.listarProveedorDisponible());
            response.put("message", "Proveedores listados correctamente");
            return ResponseEntity.ok(response);

        }catch(Exception e){

            response.put("message", "Error al listar proveedores");
            response.put("exception", e.getMessage());
            
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping("/crear")
    public ResponseEntity<?> crearProveedor(@Valid @RequestBody Proveedor proveedor, BindingResult result){

        Map<String, Object> response = new HashMap<>();

        try{

            if(result.hasErrors()){

                result.getFieldErrors().forEach(error -> {
                    response.put(error.getField(), error.getDefaultMessage());
                });

                return new ResponseEntity<>(response, HttpStatus.UNPROCESSABLE_ENTITY);
            }

            proveedorService.crearProveedor(proveedor);
            response.put("message", "Proveedor creado correctamente");
            return ResponseEntity.ok(response);

        }catch(Exception e){
            response.put("message", "Error al crear al proveedor");
            response.put("exception", e.getMessage());

            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
    
    }

    //ENCONTRAR ROL POR ID
    @GetMapping("/encontrar/{id}")
    public ResponseEntity<?> encontrarProveedor(@PathVariable long id){
        
        Map<String, Object> response = new HashMap<>();

        try{

            response.put("proveedor", proveedorService.encontrarProveedorById(id));
            response.put("message", "Proveedor encontrado");
            return ResponseEntity.ok(response);

        }catch(Exception e){

            response.put("message", "Error al encontrar al proveedor");
            response.put("exception", e.getMessage());
            
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
    }

    //MODIFICAR ROL
    @PutMapping("/editar/{id}")
    public ResponseEntity<?> editarProveedor(@PathVariable Long id, @Valid @RequestBody Proveedor proveedor, BindingResult result){
        
        Map<String, Object> response = new HashMap<>();

        try{

            if(result.hasErrors()){

                result.getFieldErrors().forEach(error -> {
                    response.put(error.getField(), error.getDefaultMessage());
                });

                return new ResponseEntity<>(response, HttpStatus.UNPROCESSABLE_ENTITY);
            }

            response.put("proveedor", proveedorService.editarProveedor(proveedor, id));
            response.put("message", "Rol modificado correctamente");
            return ResponseEntity.ok(response);

        }catch(Exception e){

            response.put("message", "Error al editar al proveedor");
            response.put("exception", e.getMessage());
            
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
    }

    //ELIMINAR ROL
    @PutMapping("/eliminar/logico/{id}")
    public ResponseEntity<?> eliminarProveedorLogico(@PathVariable Long id){
        
        Map<String, Object> response = new HashMap<>();

        try{

            response.put("proveedor", proveedorService.eliminarProveedorPorIdLogico(id));
            response.put("message", "Proveedor eliminado correctamente");
            return ResponseEntity.ok(response);

        }catch(Exception e){

            response.put("message", "Error al elimnar proveedor");
            response.put("exception", e.getMessage());
            
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
    }

}
