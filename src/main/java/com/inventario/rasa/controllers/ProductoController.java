package com.inventario.rasa.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inventario.rasa.models.dto.ProductoDTO;
import com.inventario.rasa.models.dto.ProductoRetiroDTO;
import com.inventario.rasa.service.ProductoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/producto")
@CrossOrigin(maxAge = 3600, origins = "*")
public class ProductoController {

    private final ProductoService productoService;

    @GetMapping("/listar")
    public ResponseEntity<?> listarProductos(){

        Map<String, Object> response = new HashMap<>();

        try{
            response.put("message", "Productos listados correctamente");
            response.put("productos", productoService.listarProductos());
            return ResponseEntity.ok(response);

        }catch(Exception e){
            
            response.put("message", "No se pudo listar productos");
            response.put("exception", e.getMessage());
            
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
    
    @GetMapping("/listar/elegibles")
    public ResponseEntity<?> listarProductosMayorcero(){

        Map<String, Object> response = new HashMap<>();

        try{
            response.put("message", "Productos listados correctamente");
            response.put("productos", productoService.listarProductosGreaterThanZero());
            return ResponseEntity.ok(response);

        }catch(Exception e){
            
            response.put("message", "No se pudo listar productos");
            response.put("exception", e.getMessage());
            
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
    
    // @PutMapping("/editar/cantidades")
    // public ResponseEntity<?> restarCantidadesProductos(@Valid @RequestBody List<ProductoRetiroDTO> productos, BindingResult result){

    //     Map<String, Object> response = new HashMap<>();

    //     try{

    //         if(result.hasErrors()){

    //             result.getFieldErrors().forEach(error -> {
    //                 response.put(error.getField(), error.getDefaultMessage());
    //             });

    //             return new ResponseEntity<Map<String, Object>>(response, HttpStatus.UNPROCESSABLE_ENTITY);
    //         }

    //         response.put("message", "Productos restados correctamente");
    //         response.put("productos", productoService.restarCantidades(productos));
    //         return ResponseEntity.ok(response);

    //     }catch(Exception e){
            
    //         response.put("message", "Error al restar las cantidades de los productos");
    //         response.put("exception", e.getMessage());
            
    //         return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    //     }
    // }

    @PostMapping("/crear")
    public ResponseEntity<?> crearProductos(@Valid @RequestBody List<ProductoDTO> productos, BindingResult result){

        Map<String, Object> response = new HashMap<>();

        try{

            if(result.hasErrors()){

                result.getFieldErrors().forEach(error -> {
                    response.put(error.getField(), error.getDefaultMessage());
                });

                return new ResponseEntity<>(response, HttpStatus.UNPROCESSABLE_ENTITY);
            }

            response.put("message", "Productos creados correctamente");
            response.put("productos", productoService.crearProductos(productos));
            return ResponseEntity.ok(response);

        }catch(Exception e){
            
            response.put("message", "No se pudieron crear los productos");
            response.put("exception", e.getMessage());
            
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

}
