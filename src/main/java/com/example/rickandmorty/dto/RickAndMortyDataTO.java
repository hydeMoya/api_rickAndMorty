package com.example.rickandmorty.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RickAndMortyDataTO {
    
    private int id;
	private String name;
	private String status;
	private String species;
	private String gender;
	private String image;
}
