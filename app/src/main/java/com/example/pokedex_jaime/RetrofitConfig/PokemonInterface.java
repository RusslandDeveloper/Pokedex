package com.example.pokedex_jaime.RetrofitConfig;

import com.example.pokedex_jaime.Model.Pokedex;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface PokemonInterface {


    @GET("pokedex.json")
    Observable<Pokedex>getListPokemon();
}
