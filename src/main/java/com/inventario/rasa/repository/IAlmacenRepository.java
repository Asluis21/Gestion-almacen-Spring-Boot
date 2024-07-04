package com.inventario.rasa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inventario.rasa.models.Almacen;

@Repository
public interface IAlmacenRepository extends JpaRepository<Almacen, Long>{
    
    Optional<Almacen> findByNombre(String nombre);
}
