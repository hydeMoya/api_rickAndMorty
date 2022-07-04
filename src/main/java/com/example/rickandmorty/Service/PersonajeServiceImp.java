package com.example.rickandmorty.Service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.example.rickandmorty.dto.RickAndMortyDataTO;
import com.example.rickandmorty.dto.rickAndMortyResponseTO;
import com.example.rickandmorty.entity.Personaje;
import com.example.rickandmorty.repository.PersonajeRepository;

@Service
public class PersonajeServiceImp implements PersonajeService{

    @Autowired
    private PersonajeRepository personajeRepository;

    @Override
    public HashMap<String,String> save() {
        // TODO Auto-generated method stub
        HashMap<String, String> respuesta = new HashMap<String, String>();
        RestTemplate restTemplate = new RestTemplate();
        Personaje per = new Personaje();
        //llamar a ws rick and morty
        ResponseEntity<rickAndMortyResponseTO> response = restTemplate.getForEntity("https://rickandmortyapi.com/api/character", rickAndMortyResponseTO.class,"");
        //System.out.println("response -->"+ response.getBody().getResults().size());
        
        for (RickAndMortyDataTO listPer : response.getBody().getResults()) {
            per = new Personaje();
            per.setName(listPer.getName());
            per.setStatus(listPer.getStatus());
            per.setGender(listPer.getGender());
            per.setImage(listPer.getImage());

            personajeRepository.save(per);
        }
        
        respuesta.put("mensaje", "Se grabaron los nombres de rick and morty correctamente");
        

        return respuesta;
    }

    @Override
    @Transactional(readOnly=true)
    public List<Personaje> findAll() {
        // TODO Auto-generated method stub
        return (List<Personaje>)personajeRepository.findAll();
    }

    @Override
    public Personaje findById(Long id) {
        // TODO Auto-generated method stub
        return personajeRepository.findById(id).orElse(null);
    }

    @Override
    public List<Personaje> findByAllId(List<Long> ids) {
        // TODO Auto-generated method stub
        return personajeRepository.findAllById(ids);
    }

    @Override
    public Personaje updatePersonaje(Personaje per) {
        // TODO Auto-generated method stub
        return personajeRepository.save(per);
    }

    @Override
    public void deleteById(Long id) {
        // TODO Auto-generated method stub
        personajeRepository.deleteById(id);
        
    }

    @Override
    public List<Personaje> findByStatus(String status) {
        // TODO Auto-generated method stub
        return personajeRepository.findByStatus(status);
    }
    
}
