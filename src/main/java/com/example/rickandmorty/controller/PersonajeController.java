package com.example.rickandmorty.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.rickandmorty.Service.PersonajeService;
import com.example.rickandmorty.entity.Personaje;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api")
public class PersonajeController {

    @Autowired
    private PersonajeService personajeService;

    @RequestMapping(value = "/save_personaje", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> savePersonaje(){
        HashMap<String,String> map = new HashMap<>();
        map = personajeService.save();
        return new ResponseEntity<>(map,HttpStatus.OK);
    }

    @GetMapping(value="/character")
    public ResponseEntity<?> findAll(){
        List<Personaje> list = personajeService.findAll();

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping(value = "/character/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        Personaje personaje = personajeService.findById(id);
        if(personaje != null){
            return new ResponseEntity<>(personaje, HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Recurso no encontrado. ", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value="/character/varios/{ids}")
    public ResponseEntity<?> findByAllId(@PathVariable List<Long> ids){

        List<Personaje> list = personajeService.findByAllId(ids);

        if(list.size()>0){
            return new ResponseEntity<>(list, HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Recurso no encontrado. ", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value="/character/filter")
    public ResponseEntity<?> filtrarStatus(@RequestParam String status){

        List<Personaje> list = personajeService.findByStatus(status);

        if(list.size()>0){
            return new ResponseEntity<>(list, HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Recurso no encontrado. ", HttpStatus.NOT_FOUND);
        }

    }

    @PutMapping(value="/character/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Personaje personaje) {
        Personaje perUpdate = new Personaje();
        Personaje per = personajeService.findById(id);

        if(per != null){
            perUpdate = new Personaje();
            perUpdate.setId(per.getId());
            perUpdate.setName(personaje.getName());
            perUpdate.setStatus(personaje.getStatus());
            perUpdate.setGender(personaje.getGender());
            perUpdate.setImage(personaje.getImage());

            personajeService.updatePersonaje(perUpdate);

            return new ResponseEntity<>(perUpdate, HttpStatus.OK);
        }else {
			return new ResponseEntity<>("Recurso no actualizado. ", HttpStatus.NOT_FOUND);
		}
    }

    @DeleteMapping(value="/character/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){

        Personaje per = personajeService.findById(id);

        if(per != null){
            personajeService.deleteById(id);
            return new ResponseEntity<>("Recurso eliminado",HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Recurso no encontrado",HttpStatus.NOT_FOUND);
        }
    }


}
