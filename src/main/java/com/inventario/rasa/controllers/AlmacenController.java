package com.inventario.rasa.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inventario.rasa.models.Almacen;
import com.inventario.rasa.models.dto.AlmacenDTO;
import com.inventario.rasa.service.AlmacenService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/almacen")
@CrossOrigin(maxAge = 3600, origins = "*")
public class AlmacenController {
    
    private final AlmacenService almacenService;

    //LISTAR ALMACEN
    @GetMapping("/listar")
    public ResponseEntity<?> listarAlmacen(){
        
        Map<String, Object> response = new HashMap<>();

        try{

            response.put("almacen", almacenService.listarAlmacen());
            response.put("message", "Almacenes listados correctamente");
            return ResponseEntity.ok(response);

        }catch(Exception e){

            response.put("message", "Error al listar almacenes");
            response.put("exception", e.getMessage());
            
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
    
    @GetMapping("/listar/disponible")
    public ResponseEntity<?> listarAlmacenDisponible(){
        
        Map<String, Object> response = new HashMap<>();

        try{

            response.put("almacen", almacenService.listarAlmacenDisponible());
            response.put("message", "Almacenes listados correctamente");
            return ResponseEntity.ok(response);

        }catch(Exception e){

            response.put("message", "Error al listar almacenes");
            response.put("exception", e.getMessage());
            
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    //CREAR ALMACEN
    @PostMapping("/crear")
    public ResponseEntity<?> crearAlmacen(@Valid @RequestBody Almacen almacen, BindingResult result){

        Map<String, Object> response = new HashMap<>();

        try{

            if(result.hasErrors()){

                result.getFieldErrors().forEach(error -> {
                    response.put(error.getField(), error.getDefaultMessage());
                });

                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.UNPROCESSABLE_ENTITY);
            }

            almacenService.crearAlmacen(almacen);
            response.put("message", "Almacén creado correctamente");
            return ResponseEntity.ok(response);

        }catch(Exception e){
            response.put("message", "Error al crear el almacén");
            response.put("exception", e.getMessage());

            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
    
    }

    //ENCONTRAR ALMACEN POR ID
    @GetMapping("/encontrar/{id}")
    public ResponseEntity<?> encontrarAlmacen(@PathVariable long id){
        
        Map<String, Object> response = new HashMap<>();

        try{

            response.put("almacen", almacenService.encontrarAlmacenById(id));
            response.put("message", "Almacen encontrado");
            return ResponseEntity.ok(response);

        }catch(Exception e){

            response.put("message", "Error al encontrar almacen");
            response.put("exception", e.getMessage());
            
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
    }

    //EDITAR ALMACEN
    @PutMapping("/editar/{id}")
    public ResponseEntity<?> editarAlmacen(@PathVariable Long id, @Valid @RequestBody Almacen almacen, BindingResult result){
        
        Map<String, Object> response = new HashMap<>();

        try{

            if(result.hasErrors()){

                result.getFieldErrors().forEach(error -> {
                    response.put(error.getField(), error.getDefaultMessage());
                });

                return new ResponseEntity<>(response, HttpStatus.UNPROCESSABLE_ENTITY);
            }

            response.put("almacen", almacenService.editarAlmacen(almacen, id));
            response.put("message", "Almacén modificado correctamente");
            return ResponseEntity.ok(response);

        }catch(Exception e){

            response.put("message", "Error al editar almacén");
            response.put("exception", e.getMessage());
            
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
    }

    //ELIMINAR ALMACEN
    @DeleteMapping("/eliminar/logico/{id}")
    public ResponseEntity<?> eliminarAlmacen(@PathVariable Long id){
        
        Map<String, Object> response = new HashMap<>();

        try{

            response.put("almacen", almacenService.eliminarAlmacenByIdLogico(id));
            response.put("message", "Almacen eliminado correctamente");
            return ResponseEntity.ok(response);

        }catch(Exception e){

            response.put("message", "Error al elimnar almacen");
            response.put("exception", e.getMessage());
            
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    } 
}
