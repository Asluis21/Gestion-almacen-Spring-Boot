package com.inventario.rasa.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inventario.rasa.models.Categoria;
import com.inventario.rasa.models.dto.CategoriaDTO;
import com.inventario.rasa.repository.ICategoriaRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CategoriaService {
    
    private final ICategoriaRepository categoriaRepository;

    @Transactional
    public Categoria crearCategoria(Categoria categoria){

        // Categoria categoria = new Categoria();

        // categoria.setEstado(true);
        // categoria.setNombre(categoriaDTO.getNombre());
        

        return categoriaRepository.save(categoria);
    }

    @Transactional(readOnly = true)
    public List<Categoria> listarCategoria() throws EmptyResultDataAccessException{

        return categoriaRepository.findAll();
        // List<Categoria> categorias = categoriaRepository.findAll();
        // if(!categorias.isEmpty()) return categorias;
        
        // throw new EmptyResultDataAccessException("No se encontraron categorias", 1);
    }
    
    @Transactional(readOnly = true)
    public List<Categoria> listarCategoriaDisponible() throws EmptyResultDataAccessException{

        List<Categoria> categorias = categoriaRepository.findAll()
            .stream()
            .filter(e -> e.getEstado())
            .collect(Collectors.toList());
        
        if(!categorias.isEmpty()) return categorias;
        
        throw new EmptyResultDataAccessException("No se encontraron categorias", 1);
    }

    @Transactional(readOnly = true)
    public Categoria encontrarCategoriaById(Long id){
        return categoriaRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException("Categoria no encontrada", 0));
    }
    
    @Transactional(readOnly = true)
    public Categoria encontrarCategoriaByNombre(String nombre){
        return categoriaRepository.findByNombre(nombre).orElseThrow(() -> new EmptyResultDataAccessException("Categoria no encontrada", 0));
    }

    @Transactional
    public Categoria editarCategoria(Categoria categoria, Long id){

        Categoria categoriaEncontrada = encontrarCategoriaById(id);

        categoriaEncontrada.setNombre(categoria.getNombre());
        categoriaEncontrada.setEstado(categoria.getEstado());

        return categoriaEncontrada;
    }

    // SOLO SE PUEDE ELIMINAR CUANDO NO SEA PARTE DE UN FOREIGN KEY DE UN USUARIO
    @Transactional
    public Categoria eliminarCategoriaByIdLogico(Long id){

        Categoria categoriaAEliminar = encontrarCategoriaById(id);

        categoriaAEliminar.setEstado(false);

        return categoriaAEliminar;
    }

}
