package com.inventario.rasa.security.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.inventario.rasa.models.Usuario;
import com.inventario.rasa.repository.IUsuarioRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService{
    
    private final IUsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        Usuario user = usuarioRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("Usuario " + username + " no encontrado"));
        
        Usuario usuario = new Usuario();
        usuario.setId(user.getId());
        usuario.setUsername(user.getUsername());
        usuario.setPassword(user.getPassword());
        usuario.setRol(user.getRol());


        return usuario;
    }


}
