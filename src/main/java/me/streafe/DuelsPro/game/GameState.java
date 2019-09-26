package me.streafe.DuelsPro.game;

public enum GameState {

    WAITING("waiting"),
    STARTED("started"),
    ENDGAME("endgame"),
    RESETTING("resetting");

    private String name;

    GameState(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

}
