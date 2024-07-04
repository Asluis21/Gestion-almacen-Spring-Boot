package com.inventario.rasa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inventario.rasa.models.Rol;

@Repository
public interface IRolRepository extends JpaRepository<Rol, Long>{
    
    Optional<Rol> findByNombre(String nombre);
}
