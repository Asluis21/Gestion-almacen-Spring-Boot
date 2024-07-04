package com.inventario.rasa.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inventario.rasa.models.Rol;
import com.inventario.rasa.models.dto.RolDTO;
import com.inventario.rasa.repository.IRolRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class RolService {
    
    private final IRolRepository rolRepository;

    //VALIDAR NOMBRE
    // private void validName(String rolName) throws IllegalArgumentException{

        
    //     String sufijoRol = rolName.substring(0, 5);
        
    //     System.out.println(sufijoRol);
    //     if(!sufijoRol.equals("ROLE_")){
    //         throw new IllegalArgumentException("Nombre invalido");
    //     }

    // }

    @Transactional
    public Rol crearRol(Rol rol) throws IllegalArgumentException{

        rol.setNombre("ROLE_" + rol.getNombre().toUpperCase());
        // validName(rol.getNombre());

        return rolRepository.save(rol);
    }

    @Transactional(readOnly = true)
    public List<Rol> listarRoles(){
        
        List<Rol> roles = new ArrayList<>();
        rolRepository.findAll().forEach(rol -> {

            rol.setNombre(rol.getNombre().substring(5, rol.getNombre().length()));
                
            roles.add(rol);
        });

        return roles;
    }
    
    @Transactional(readOnly = true)
    public List<Rol> listarRolesEstadoTrue(){
        // return rolRepository.findAll();
        // List<Rol> roles = new ArrayList<>();
        // rolRepository.findAll().forEach(rol -> {

        //     rol.setNombre(rol.getNombre().substring(5, rol.getNombre().length()));
                
        //     roles.add(rol);
        // });

        return listarRoles()
            .stream()
            .filter(e -> e.getEstado())
            .collect(Collectors.toList());

    }

    @Transactional(readOnly = true)
    public Rol rolByNombre(String nombre){
        return rolRepository.findByNombre(nombre).orElseThrow(() -> new EmptyResultDataAccessException("Rol no encontrado", 1));
    }

    @Transactional(readOnly = true)
    public Rol encontraRolById(Long id){
        return rolRepository.findById(id)
        .orElseThrow(() -> new EmptyResultDataAccessException("Rol no encontrado", 1));
    }

    @Transactional
    public Rol editarRol(long id, Rol rol) throws IllegalArgumentException{

        Rol rolEncontrado = encontraRolById(id);

        rol.setNombre("ROLE_" + rol.getNombre().toUpperCase());
        // validName(rol.getNombre());

        rolEncontrado.setNombre(rol.getNombre());
        rolEncontrado.setEstado(rol.getEstado());

        return rolEncontrado;
    }

    // SOLO SE PUEDE ELIMINAR CUANDO NO SEA PARTE DE UN FOREIGN KEY DE UN USUARIO
    @Transactional
    public Rol eliminarRolBy(Long id){

        Rol rolEncontrado = encontraRolById(id);

        rolRepository.delete(rolEncontrado);

        return rolEncontrado;
    }
    
    // ELIMINACIÓN LÓGICA
    @Transactional
    public Rol eliminarRolByIdLogico(Long id){

        Rol rolEncontrado = encontraRolById(id);

        rolEncontrado.setEstado(false);

        return rolEncontrado;
    }


}
