package com.example.vincent.pokebattler;

/**
 * Created by Vincent on 7-12-2017.
 */

public class battles  {
    private long firstPokemon;
    private long secondPokemon;
    private long winner;

    public void pokemon(
              long firstPokemon
            , long secondPokemon
            , long winner){
        this.firstPokemon = firstPokemon;
        this.secondPokemon = secondPokemon;
        this.winner = winner;
    }

    public String getInfo(){
        return (firstPokemon+" "+ secondPokemon+" "+winner);
    }
}

