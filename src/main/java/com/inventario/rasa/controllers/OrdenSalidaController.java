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

import com.inventario.rasa.models.dto.OrdenSalidaDTO;
import com.inventario.rasa.service.OrdenSalidaService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/orden/salida")
@CrossOrigin(maxAge = 3600, origins = "*")
public class OrdenSalidaController {

        private final OrdenSalidaService ordenSalidaService;

    
    @GetMapping("/listar")
    public ResponseEntity<?> listarOrdenSalida(){
        
        Map<String, Object> response = new HashMap<>();

        try{

            response.put("ordenes", ordenSalidaService.listarOrdenes());
            response.put("message", "Ordenes de salida listadas correctamente");
            return ResponseEntity.ok(response);

        }catch(Exception e){

            response.put("message", "Error al listar las ordenes de salida");
            response.put("exception", e.getMessage());
            
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/encontrar/{id}")
    public ResponseEntity<?> encontrarOrdenSalida(@PathVariable Long id){

        Map<String, Object> response = new HashMap<>();

        try{

            response.put("message", "Orden de salida encontrada correctamente");
            response.put("orden", ordenSalidaService.encontrarOrdenSalidaById(id));
            return ResponseEntity.ok(response);

        }catch(Exception e){
            
            response.put("message", "No se pudo encontrar la orden de salida");
            response.put("exception", e.getMessage());
            
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/crear")
    public ResponseEntity<?> crearOrdenSalida(@Valid @RequestBody OrdenSalidaDTO ordenSalida, BindingResult result){

        Map<String, Object> response = new HashMap<>();

        try{

            if(result.hasErrors()){

                result.getFieldErrors().forEach(error -> {
                    response.put(error.getField(), error.getDefaultMessage());
                });

                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.UNPROCESSABLE_ENTITY);
            }

            ordenSalidaService.crearOrdenSalida(ordenSalida);
            response.put("message", "Orden de salida creada correctamente");
            return ResponseEntity.ok(response);

        }catch(Exception e){
            response.put("message", "Error al crear la orden de salida");
            response.put("exception", e.getMessage());

            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    
    }
    
    @PutMapping("/editar/{id}")
    public ResponseEntity<?> editarOrdenSalida(@PathVariable Long id, @Valid @RequestBody OrdenSalidaDTO ordenSalida, BindingResult result){

        Map<String, Object> response = new HashMap<>();

        try{

            if(result.hasErrors()){

                result.getFieldErrors().forEach(error -> {
                    response.put(error.getField(), error.getDefaultMessage());
                });

                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.UNPROCESSABLE_ENTITY);
            }

            ordenSalidaService.editarOrdenSalida(id, ordenSalida);
            response.put("message", "Orden de salida editada correctamente");
            return ResponseEntity.ok(response);

        }catch(Exception e){
            response.put("message", "Error al editar la orden de salida");
            response.put("exception", e.getMessage());

            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    
    }
}
