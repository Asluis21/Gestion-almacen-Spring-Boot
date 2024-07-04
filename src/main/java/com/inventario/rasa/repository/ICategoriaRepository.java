package com.inventario.rasa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inventario.rasa.models.Categoria;

@Repository
public interface ICategoriaRepository extends JpaRepository<Categoria, Long>{

    Optional<Categoria> findByNombre(String nombre);
    
}
