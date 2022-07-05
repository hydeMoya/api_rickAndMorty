package com.example.rickandmorty.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.rickandmorty.entity.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long>{

    Usuario findByUsername(String username);
    
}
