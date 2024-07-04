package com.inventario.rasa.auth;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inventario.rasa.models.dto.UsuarioRequestDTO;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
public class AuthController {
    
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
        return ResponseEntity.ok(authService.login(loginRequest));
    }

    @PostMapping("/registro/operador")
    public ResponseEntity<?> registrarOperador(@Valid @RequestBody UsuarioRequestDTO registerRequest, BindingResult result){ 
        
        Map<String, Object> response = new HashMap<>();

        try{

            if(result.hasErrors()){

                result.getFieldErrors().forEach(error -> {
                    response.put(error.getField(), error.getDefaultMessage());
                });

                return new ResponseEntity<>(response, HttpStatus.UNPROCESSABLE_ENTITY);
            }

            response.put("message", "Usuario registrado correctamente");
            authService.registerAsOperario(registerRequest);
            return ResponseEntity.ok(response);

        }catch(Exception e){

            response.put("message", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
            
        }
    }

    @GetMapping("/verificar/jefe")
    @PreAuthorize("hasRole('ROLE_JEFE')")
    public ResponseEntity<?> verificarJefe(){

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Jefe autorizado");

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }
    
    @GetMapping("/verificar/operario")
    @PreAuthorize("hasRole('ROLE_OPERARIO')")
    public ResponseEntity<?> verificarOperario(){

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Operario autorizado");

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }
    
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/verificar/usuario")
    public ResponseEntity<?> verificarUsuario(){

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Usuario autorizado");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }



    @PostMapping("/registro")
    public ResponseEntity<?> registrar(@Valid @RequestBody UsuarioRequestDTO registerRequest, BindingResult result){

        Map<String, Object> response = new HashMap<>();

        try{
            
            if(result.hasErrors()){

                result.getFieldErrors().forEach(error -> {
                    response.put(error.getField(), error.getDefaultMessage());
                });

                return new ResponseEntity<>(response, HttpStatus.UNPROCESSABLE_ENTITY);
            }

            authService.register(registerRequest);
            response.put("message", "Usuario registrado correctamente");
            return ResponseEntity.ok(response);


        }catch(Exception e){
            response.put("message", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
    }

    

    
}
