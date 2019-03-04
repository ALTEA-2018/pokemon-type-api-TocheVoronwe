package com.miage.altea.tp.pokemon_type_api.service;

import com.miage.altea.tp.pokemon_type_api.bo.PokemonType;
import com.miage.altea.tp.pokemon_type_api.repository.PokemonTypeRepository;
import com.miage.altea.tp.pokemon_type_api.repository.TranslationRepository;
import com.miage.altea.tp.pokemon_type_api.repository.TranslationRepositoryImpl;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
@Getter
@Setter
public class PokemonTypeServiceImpl implements PokemonTypeService {
    public PokemonTypeRepository pokemonTypeRepository;
    public TranslationRepository translationRepository;

    @Autowired
    public PokemonTypeServiceImpl(PokemonTypeRepository pokemonTypeRepository) {
        this.pokemonTypeRepository = pokemonTypeRepository;
        this.translationRepository = new TranslationRepositoryImpl();
    }

    public PokemonTypeServiceImpl() {

    }

    @Override
    public PokemonType getPokemonType(int id) {
        var res = pokemonTypeRepository.findPokemonTypeById(id);
        if (res != null)
            res.setName(getTranslatedName(res, null));
        return res;
    }

    @Override
    public PokemonType getPokemonName(String name, String clientLocale) {
        var res = this.pokemonTypeRepository.findPokemonTypeByName(name);
        res.setName(getTranslatedName(res, clientLocale));
        return res;
    }

    private String getTranslatedName(PokemonType pokemonType, String clientLocale) {

        Locale locale = clientLocale != null ? new Locale(clientLocale, "") : null;
        return translationRepository.getPokemonName(pokemonType.getId(), locale);
    }

    @Override
    public List<PokemonType> getAllPokemonTypes(String locale) {

        var res = pokemonTypeRepository.findAllPokemonType();
        if (res != null)
            res.forEach(pokemonType -> pokemonType.setName(getTranslatedName(pokemonType, locale)));
        return res;
    }

    @Override
    public void setTranslationRepository(TranslationRepository translationRepository) {
        this.translationRepository = translationRepository;
    }

    public void setPokemonTypeRepository(PokemonTypeRepository pokemonTypeRepository) {
        System.out.println("hihi");
        this.pokemonTypeRepository = pokemonTypeRepository;
    }
}