package com.example.vincent.pokebattler;

/**
 * Created by Vincent on 7-12-2017.
 */

public class pokemon   {
    private String Name;
    private long Attack;
    private long Defense;
    private long DexNo;
    private long Generation;
    private long HP;
    private boolean Legendary;
    private long no;
    private long SpAtk;
    private long SpDef;
    private long Speed;
    private String Type1;
    private String Type2;
    public void pokemon(String Name,long Attack
                        , long Defense
                        , long DexNo
                        , long Generation
                        , long HP
                        , boolean Legendary
                        , int no
                        , long SpAtk
                        , long SpDef
                        , long Speed
                        , String Type1
                        , String Type2){
        this.Name = Name;
        this.Attack = Attack;
        this.Defense = Defense;
        this.DexNo = DexNo;
        this.Generation = Generation;
        this.HP = HP;
        this.Legendary = Legendary;
        this.no = no;
        this.SpAtk = SpAtk;
        this.SpDef = SpDef;
        this.Speed = Speed;
        this.Type1 = Type1;
        this.Type2 = Type2;
    }

    public String getInfo(){
        return (Name + " " + Attack + " " + Defense + " " + DexNo + " " + Generation +
                " " + HP + " " + Legendary + " " + no + " " + SpAtk + " " + SpDef + " " +
                Speed + " " + Type1 + " " + Type2);
    }
}
