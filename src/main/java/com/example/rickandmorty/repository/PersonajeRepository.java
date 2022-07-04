package com.example.rickandmorty.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.rickandmorty.entity.Personaje;

@Repository
public interface PersonajeRepository extends JpaRepository<Personaje,Long> {
    Personaje personaje = new Personaje();
    @Transactional(readOnly = true)
    @Query("SELECT p FROM Personaje p WHERE p.status LIKE %?1%")
	public List<Personaje> findByStatus(String status);
}
