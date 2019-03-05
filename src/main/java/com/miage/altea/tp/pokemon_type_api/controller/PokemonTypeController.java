package com.miage.altea.tp.pokemon_type_api.controller;

import com.miage.altea.tp.pokemon_type_api.bo.PokemonType;
import com.miage.altea.tp.pokemon_type_api.service.PokemonTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.List;

@Controller()
@RequestMapping("/pokemon-types")
@RestController
class PokemonTypeController {
    private PokemonTypeService pokemonTypeService;

    @Autowired
    public PokemonTypeController(PokemonTypeService pokemonTypeService) {
        this.pokemonTypeService = pokemonTypeService;
    }

    @GetMapping("/{id}")
    PokemonType getPokemonTypeFromId(@PathVariable("id") Integer id){
        return this.pokemonTypeService.getPokemonType(id);
    }

    @GetMapping("")
    public List<PokemonType> getAllPokemonTypes() {
        var res = this.pokemonTypeService.getAllPokemonTypes();
        res.sort(((o1, o2) -> o1.getId() - o2.getId()));
        return res;
    }

    @GetMapping(value = "/", params = "name")
    PokemonType getPokemonTypeFromName(@PathParam("name") String name) {

        return this.pokemonTypeService.getPokemonName(name);
    }
}