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

import com.inventario.rasa.models.Categoria;
import com.inventario.rasa.models.dto.CategoriaDTO;
import com.inventario.rasa.service.CategoriaService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/categoria")
@CrossOrigin(maxAge = 3600, origins = "*")
public class CategoriaController {
    
    private final CategoriaService categoriaService;

    @GetMapping("/listar")
    public ResponseEntity<?> listarCategoria(){

        Map<String, Object> response = new HashMap<>();

        try{
            response.put("message", "Categorias listadas correctamente");
            response.put("categorias", categoriaService.listarCategoria());
            return ResponseEntity.ok(response);

        }catch(Exception e){
            
            response.put("message", "No se pudo listar categoría");
            response.put("exception", e.getMessage());
            
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
    
    @GetMapping("/listar/disponible")
    public ResponseEntity<?> listarCategoriaDisponible(){

        Map<String, Object> response = new HashMap<>();

        try{
            response.put("message", "Categorias disponibles listadas correctamente");
            response.put("categorias", categoriaService.listarCategoriaDisponible());
            return ResponseEntity.ok(response);

        }catch(Exception e){
            
            response.put("message", "No se pudo listar categoría");
            response.put("exception", e.getMessage());
            
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/crear")
    public ResponseEntity<?> crearCategoria(@Valid @RequestBody Categoria categoria, BindingResult result){

        Map<String, Object> response = new HashMap<>();

        try{

            if (result.hasErrors()) {
				
				Map<String, String> errors = new HashMap<>();
				
				
				result.getFieldErrors().forEach(err ->{
					errors.put(err.getField(), err.getDefaultMessage());
				});
				
				
				// response.put("message", errors);

                return new ResponseEntity<>(errors, HttpStatus.UNPROCESSABLE_ENTITY);
			}

            response.put("message", "Categoría creada correctamente");
            response.put("categoría", categoriaService.crearCategoria(categoria));
            return ResponseEntity.ok(response);

        }catch(Exception e){
            
            response.put("message", "No se pudo crear la categoría");
            response.put("exception", e.getMessage());
            
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<?> editarCategoria(@PathVariable Long id, @Valid @RequestBody Categoria categoria, BindingResult result){

        Map<String, Object> response = new HashMap<>();

        try{

            if(result.hasErrors()){

                result.getFieldErrors().forEach(error -> {
                    response.put(error.getField(), error.getDefaultMessage());
                });

                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.UNPROCESSABLE_ENTITY);
            }

            response.put("message", "Categoría editada correctamente");
            response.put("categoría", categoriaService.editarCategoria(categoria, id));
            return ResponseEntity.ok(response);

        }catch(Exception e){
            
            response.put("message", "No se pudo editar la categoría");
            response.put("exception", e.getMessage());
            
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/encontrar/{id}")
    public ResponseEntity<?> encontrarCategoria(@PathVariable Long id){

        Map<String, Object> response = new HashMap<>();

        try{

            response.put("message", "Categoría encontrada correctamente");
            response.put("categoría", categoriaService.encontrarCategoriaById(id));
            return ResponseEntity.ok(response);

        }catch(Exception e){
            
            response.put("message", "No se pudo encontrar la categoría");
            response.put("exception", e.getMessage());
            
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/eliminar/logico/{id}")
    public ResponseEntity<?> eliminarCategoriaLogico(@PathVariable Long id){

        Map<String, Object> response = new HashMap<>();

        try{

            response.put("message", "Categoría eliminar correctamente");
            response.put("categoría", categoriaService.eliminarCategoriaByIdLogico(id));
            return ResponseEntity.ok(response);

        }catch(Exception e){
            
            response.put("message", "No se pudo eliminar la categoría");
            response.put("exception", e.getMessage());
            
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
}
