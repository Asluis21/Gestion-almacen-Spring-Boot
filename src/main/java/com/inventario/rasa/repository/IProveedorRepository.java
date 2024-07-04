package com.inventario.rasa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inventario.rasa.models.Proveedor;

@Repository
public interface IProveedorRepository extends JpaRepository<Proveedor, Long>{
    
    Optional<Proveedor> findByNombre(String nombre);

}
