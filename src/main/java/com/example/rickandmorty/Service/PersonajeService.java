package com.example.rickandmorty.Service;
import java.util.HashMap;
import java.util.List;

import com.example.rickandmorty.entity.Personaje;

public interface PersonajeService {
    
    public HashMap<String,String> save();

    public List<Personaje> findAll();

    public Personaje findById(Long id);

    public List<Personaje> findByAllId(List<Long> ids);

    public List<Personaje> findByStatus(String status);

    public Personaje updatePersonaje(Personaje per);

    public void deleteById(Long id);

}
