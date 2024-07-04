package com.inventario.rasa.auth;

import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inventario.rasa.models.Rol;
import com.inventario.rasa.models.Usuario;
import com.inventario.rasa.models.dto.UsuarioRequestDTO;
// import com.inventario.rasa.repository.IRolRepository;
import com.inventario.rasa.repository.IUsuarioRepository;
import com.inventario.rasa.security.jwt.JwtService;
import com.inventario.rasa.service.RolService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AuthService {
    
    private final IUsuarioRepository usuarioRepository;
    // private final IRolRepository rolRepository;
    private final RolService rolService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

  

    @Transactional
    // public AuthResponse register(RegisterRequest registerRequest){
    public void register(UsuarioRequestDTO registerRequest){
        
        Rol rolEncontrado = rolService.encontraRolById(registerRequest.getRol().getId());
        // Rol rolEncontrado = rolRepository.findByNombre("ROLE_OPERARIO").orElseThrow();
        System.out.println("dni");
        Usuario usuarioCreado = new Usuario();
        usuarioCreado.setNombre(registerRequest.getNombre());
        usuarioCreado.setApellidoPaterno(registerRequest.getApellidoPaterno());
        usuarioCreado.setApellidoMaterno(registerRequest.getApellidoMaterno());
        usuarioCreado.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        usuarioCreado.setDni(registerRequest.getDni());
        usuarioCreado.setUsername(registerRequest.getUsername());
        usuarioCreado.setRol(rolEncontrado);
        usuarioCreado.setEstado(true);
        
        usuarioRepository.save(usuarioCreado);

        // return AuthResponse.builder()
        //     .token(jwtService.getToken(usuarioCreado))
        //     .build();
    }

    @Transactional
    public void registerAsOperario(UsuarioRequestDTO registerRequest){
        
        Rol rolEncontrado = rolService.encontraRolById(1L);
        // Optional<Rol> rolEncontrado = rolRepository.findByNombre("ROLE_OPERARIO");

        Usuario usuarioCreado = new Usuario();
        usuarioCreado.setNombre(registerRequest.getNombre());
        usuarioCreado.setApellidoPaterno(registerRequest.getApellidoPaterno());
        usuarioCreado.setApellidoMaterno(registerRequest.getApellidoMaterno());
        usuarioCreado.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        usuarioCreado.setDni(registerRequest.getDni());
        usuarioCreado.setUsername(registerRequest.getUsername());
        usuarioCreado.setRol(rolEncontrado);
        usuarioCreado.setEstado(true);

        usuarioRepository.save(usuarioCreado);

        // return AuthResponse.builder()
        //     .token(jwtService.getToken(usuarioCreado))
        //     .build();                
    }

    public AuthResponse login(LoginRequest loginRequest){

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        UserDetails user = null;

        Optional<Usuario> usuarioOptional = usuarioRepository.findByUsername(loginRequest.getUsername());

        if(usuarioOptional.isPresent()){
            user = usuarioRepository.findByUsername(usuarioOptional.get().getUsername()).get();
        }

        String token = jwtService.getToken(user);

        return AuthResponse.builder()
            .token(token)
            .build();
    }

}
