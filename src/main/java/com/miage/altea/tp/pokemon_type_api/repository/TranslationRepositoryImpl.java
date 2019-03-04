package com.miage.altea.tp.pokemon_type_api.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.miage.altea.tp.pokemon_type_api.bo.Translation;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

@Repository
public class TranslationRepositoryImpl implements TranslationRepository {

    private Map<Locale, List<Translation>> translations;

    private List<Translation> defaultTranslations;

    public TranslationRepositoryImpl() {
        try {
            var objectMapper = new ObjectMapper();

            var frenchTranslationStream = new ClassPathResource("translations-fr.json").getInputStream();
            var frenchTranslationsArray = objectMapper.readValue(frenchTranslationStream, Translation[].class);

            var englishTranslationStream = new ClassPathResource("translations-en.json").getInputStream();
            var englishTranslationsArray = objectMapper.readValue(englishTranslationStream, Translation[].class);

            this.translations = Map.of(
                    Locale.FRENCH, List.of(frenchTranslationsArray),
                    Locale.ENGLISH, List.of(englishTranslationsArray)
            );

            this.defaultTranslations = List.of(englishTranslationsArray);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Translation getPokemon(List<Translation> pokemonList, int id) {
        var res = pokemonList.stream().filter(translation -> translation.getId() == id).findFirst();
        return res.orElse(null);
    }

    @Override
    public String getPokemonName(int id, Locale clientLocale) {
        var locale = clientLocale != null && translations.containsKey(clientLocale) ?  translations.get(clientLocale) : defaultTranslations;
        var res = getPokemon(locale, id);
        if (res != null) {
            return res.getName();
        }
        return null;
    }
}