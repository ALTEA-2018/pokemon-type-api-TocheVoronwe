package com.miage.altea.tp.pokemon_type_api.service;

import com.miage.altea.tp.pokemon_type_api.bo.PokemonType;
import com.miage.altea.tp.pokemon_type_api.repository.TranslationRepository;

import java.util.List;

public interface PokemonTypeService {
    PokemonType getPokemonType(int id);
    PokemonType getPokemonName(String name, String locale);
    List<PokemonType> getAllPokemonTypes(String locale);
    void setTranslationRepository(TranslationRepository translationRepository);
}