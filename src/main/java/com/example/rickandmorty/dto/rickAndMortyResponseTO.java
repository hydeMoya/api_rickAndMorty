package com.example.rickandmorty.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class rickAndMortyResponseTO {
    
    private List<RickAndMortyDataTO> results;
}
