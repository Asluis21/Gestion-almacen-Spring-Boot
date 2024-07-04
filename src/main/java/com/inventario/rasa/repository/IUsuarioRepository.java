package com.inventario.rasa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inventario.rasa.models.Usuario;


@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Long>{
    
    Optional<Usuario> findByUsername(String Username);
}
