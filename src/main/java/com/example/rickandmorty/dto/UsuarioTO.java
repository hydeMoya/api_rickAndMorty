package com.example.rickandmorty.dto;

import java.util.List;

import com.example.rickandmorty.entity.Rol;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioTO {
    private String username;
    private String password;
    private Boolean enabled;
    private String nombre;
    private String apellido;
    private String email;
    private List<Rol> roles;
     
}