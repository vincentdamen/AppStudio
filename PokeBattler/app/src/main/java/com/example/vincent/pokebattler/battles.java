package com.example.vincent.pokebattler;



public class battles  {
    public long First_pokemon;
    public long Second_pokemon;
    public long Winner;
    public float Score;

    public void pokemon(
              long First_pokemon
            , long Second_pokemon
            , long Winner,
              float Score){
        this.First_pokemon = First_pokemon;
        this.Second_pokemon = Second_pokemon;
        this.Winner = Winner;
        this.Score = Score;
    }

    public String getInfo(){
        return (First_pokemon+" "+ Second_pokemon+" "+Winner+" "+Score);
    }
}

