package me.streafe.DuelsPro.Game;

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
