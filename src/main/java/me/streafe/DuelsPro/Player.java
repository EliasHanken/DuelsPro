package me.streafe.DuelsPro;

import me.streafe.DuelsPro.Game.GameState;
import org.bukkit.Bukkit;

import java.util.UUID;

public class Player {

    private UUID playerUUID;
    private GameState enumType;
    private Player target;
    private DuelsPro duelsPro;

    public Player(UUID uuid){
        this.playerUUID = uuid;
    }

    public boolean isInGame(Player player){
        if (duelsPro.inGame.contains(player)){
            return true;
        }
        return false;
    }

    public void addPlayer(Player player){
        if(!duelsPro.inGame.contains(player)){
            duelsPro.inGame.add(player);
        }
    }

    public void createPlayerFiles(Player player){

    }

    public UUID getPlayerUUID(){
        return this.playerUUID;
    }

    public void sendMessage(String msg){
        Bukkit.getPlayer(this.playerUUID).sendMessage(msg);
    }

}
