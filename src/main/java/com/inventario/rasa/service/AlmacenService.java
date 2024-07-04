package com.inventario.rasa.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inventario.rasa.models.Almacen;
import com.inventario.rasa.models.dto.AlmacenDTO;
import com.inventario.rasa.repository.IAlmacenRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AlmacenService {
    
    private final IAlmacenRepository almacenRepository;

    //LISTAR ALMACENES;
    @Transactional(readOnly = true)
    public List<Almacen> listarAlmacen(){

        return almacenRepository.findAll();

        // if(almacenes.size() > 0) return almacenes;

        // throw new EmptyResultDataAccessException("No almacen found", 1);
    }
    
    //LISTAR ALMACENES DISPONIBLES;
    @Transactional(readOnly = true)
    public List<Almacen> listarAlmacenDisponible(){

        return almacenRepository.findAll()
            .stream()
                .filter(e -> e.getEstado())
                .collect(Collectors.toList());


        // if(almacenes.size() > 0) return almacenes;

        // throw new EmptyResultDataAccessException("No almacen found", 1);
    }

    //ENCONTRAR ALMACÉN BY ID
    @Transactional(readOnly = true)
    public Almacen encontrarAlmacenById(Long id) throws EmptyResultDataAccessException{
        return almacenRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException("No almacen found", 1));
    }

    //ENCONTRAR ALMACÉN BY NOMBRE

    @Transactional(readOnly = true)
    public Almacen encontrarAlmacenByNombre(String nombre) throws EmptyResultDataAccessException{
        return almacenRepository.findByNombre(nombre).orElseThrow(() -> new EmptyResultDataAccessException("No almacen found", 1));
    }

    //CREAR ALMACEN
    @Transactional
    public Almacen crearAlmacen(Almacen almacen){

        // Almacen almacen = new Almacen();
        // almacen.setEstado(true);
        // almacen.setNombre(almacenDTO.getNombre());
        // almacen.setUbicacion(almacenDTO.getUbicacion());
        
        return almacenRepository.save(almacen);
    }
    
    //EDITAR ALMACEN
    @Transactional
    public Almacen editarAlmacen(Almacen almacen, Long id){

        Almacen almacenEncontrado = encontrarAlmacenById(id);

        almacenEncontrado.setNombre(almacen.getNombre());
        almacenEncontrado.setUbicacion(almacen.getUbicacion());
        almacenEncontrado.setEstado(almacen.getEstado());

        return almacenEncontrado;
    }

    // ELIMINAR ALMACÉN
    @Transactional
    public Almacen eliminarAlmacenByIdLogico(Long id){

        Almacen almacenEncontrado = encontrarAlmacenById(id);
        almacenEncontrado.setEstado(false);
        // almacenRepository.delete(almacenEncontrado);

        return almacenEncontrado;
    }
    
}
