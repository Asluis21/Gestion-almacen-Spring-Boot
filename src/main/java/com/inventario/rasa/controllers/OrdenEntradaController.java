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

import com.inventario.rasa.models.dto.OrdenEntradaDTO;
import com.inventario.rasa.service.OrdenEntradaService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/orden/entrada")
@CrossOrigin(maxAge = 3600, origins = "*")
public class OrdenEntradaController {
    
    private final OrdenEntradaService ordenEntradaService;

    @GetMapping("/listar")
    public ResponseEntity<?> listarOrdenEntrada(){
        
        Map<String, Object> response = new HashMap<>();

        try{

            response.put("ordenes", ordenEntradaService.listarOrdenes());
            response.put("message", "Ordenes de entrada listadas correctamente");
            return ResponseEntity.ok(response);

        }catch(Exception e){

            response.put("message", "Error al listar las ordenes de entrada");
            response.put("exception", e.getMessage());
            
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/crear")
    public ResponseEntity<?> crearOrdenEntrada(@Valid @RequestBody OrdenEntradaDTO ordenEntrada, BindingResult result){

        Map<String, Object> response = new HashMap<>();

        try{

            if(result.hasErrors()){

                result.getFieldErrors().forEach(error -> {
                    response.put(error.getField(), error.getDefaultMessage());
                });

                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.UNPROCESSABLE_ENTITY);
            }

            ordenEntradaService.crearOrdenEntrada(ordenEntrada);
            response.put("message", "Orden de entrada creada correctamente");
            return ResponseEntity.ok(response);

        }catch(Exception e){
            response.put("message", "Error al crear la orden de entrada");
            response.put("exception", e.getMessage());

            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    
    }
    
    @PutMapping("/editar/{id}")
    public ResponseEntity<?> editarOrdenEntrada(@PathVariable Long id, @Valid @RequestBody OrdenEntradaDTO ordenEntrada, BindingResult result){

        Map<String, Object> response = new HashMap<>();

        try{

            if(result.hasErrors()){

                result.getFieldErrors().forEach(error -> {
                    response.put(error.getField(), error.getDefaultMessage());
                });

                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.UNPROCESSABLE_ENTITY);
            }

            ordenEntradaService.editarOrdenEntrada(id, ordenEntrada);
            response.put("message", "Orden de entrada editada correctamente");
            return ResponseEntity.ok(response);

        }catch(Exception e){
            response.put("message", "Error al editar la orden de entrada");
            response.put("exception", e.getMessage());

            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    
    }

    @GetMapping("/encontrar/{id}")
    public ResponseEntity<?> encontrarOrdenEntrada(@PathVariable Long id){

        Map<String, Object> response = new HashMap<>();

        try{

            response.put("message", "Orden de entrada encontrada correctamente");
            response.put("orden", ordenEntradaService.encontrarOrdenEntradaById(id));
            return ResponseEntity.ok(response);

        }catch(Exception e){
            
            response.put("message", "No se pudo encontrar la orden de entrada");
            response.put("exception", e.getMessage());
            
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
}
