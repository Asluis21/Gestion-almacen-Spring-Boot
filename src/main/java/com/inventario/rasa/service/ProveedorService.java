package com.inventario.rasa.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inventario.rasa.models.Proveedor;
import com.inventario.rasa.models.dto.ProveedorDTO;
import com.inventario.rasa.repository.IProveedorRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProveedorService{
    
    private final IProveedorRepository proveedorRepository;

    // LISTAR PROVEEDORES
    @Transactional(readOnly = true)
    public List<Proveedor> listar() throws EmptyResultDataAccessException{
        
        return proveedorRepository.findAll();
        // if(!proveedores.isEmpty()) return proveedores;

        // throw new EmptyResultDataAccessException("No se encontraron proveedores", 1);
    }
    
    // LISTAR PROVEEDORES DISPONIBLES
    @Transactional(readOnly = true)
    public List<Proveedor> listarProveedorDisponible() throws EmptyResultDataAccessException{
        
        return proveedorRepository.findAll()
            .stream()
            .filter(e -> e.getEstado())
            .collect(Collectors.toList());

        // if(!proveedores.isEmpty()) return proveedores;

        // throw new EmptyResultDataAccessException("No se encontraron proveedores", 1);
    }

    // ENCONTRAR PROVEEDOR POR ID
    @Transactional(readOnly = true)
    public Proveedor encontrarProveedorById(Long id) {
        return proveedorRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException("No se encontro el proveedor", 1));
    }

    // CREAR PROVEEDOR
    @Transactional
    public Proveedor crearProveedor(Proveedor proveedor) {

        // Proveedor proveedor = new Proveedor();

        // proveedor.setEstado(true);
        // proveedor.setNombre(proveedorDTO.getNombre());
        // proveedor.setRuc(proveedorDTO.getRuc());

        return proveedorRepository.save(proveedor);
    }

    // EDITAR PROVEEDOR
    @Transactional
    public Proveedor editarProveedor(Proveedor proveedor, Long id) {

        Proveedor proveedorEncontrado = encontrarProveedorById(id);

        proveedorEncontrado.setNombre(proveedor.getNombre());
        proveedorEncontrado.setRuc(proveedor.getRuc());
        proveedorEncontrado.setEstado(proveedor.getEstado());

        // return proveedorRepository.save(proveedorEncontrado);
        return proveedorEncontrado;
    }

    // ENCONTRAR PROVEEDOR POR NOMBRE
    @Transactional(readOnly = true)
    public Proveedor encontrarProveedorByNombre(String nombre){

        return proveedorRepository.findByNombre(nombre).orElseThrow(() -> new EmptyResultDataAccessException("No se encontró el proveedor", 1));
    }

    // ELIMINAR PROVEEDOR
    @Transactional
    public Proveedor eliminarProveedorPorId(Long id){
        Proveedor proveedorEncontrado = encontrarProveedorById(id);
        proveedorRepository.deleteById(id);
        return proveedorEncontrado;
    }
    
    // ELIMINAR PROVEEDOR LOGICO
    @Transactional
    public Proveedor eliminarProveedorPorIdLogico(Long id){
        Proveedor proveedorEncontrado = encontrarProveedorById(id);
        proveedorEncontrado.setEstado(false);
        return proveedorEncontrado;
    }

}
