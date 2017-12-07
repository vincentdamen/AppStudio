package com.example.vincent.pokebattler;

/**
 * Created by Vincent on 7-12-2017.
 */

public class battles  {
    public long First_pokemon;
    public long Second_pokemon;
    public long Winner;

    public void pokemon(
              long First_pokemon
            , long Second_pokemon
            , long Winner){
        this.First_pokemon = First_pokemon;
        this.Second_pokemon = Second_pokemon;
        this.Winner = Winner;
    }

    public String getInfo(){
        return (First_pokemon+" "+ Second_pokemon+" "+Winner);
    }
}

