package me.streafe.DuelsPro;

import me.streafe.DuelsPro.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class Player {

    private UUID playerUUID;
    private GameEnumType enumType;
    private Player target;
    private File file;
    private Utils utils = new Utils();
    private String name;

    public Player(UUID uuid){
        this.playerUUID = uuid;
        this.name = Bukkit.getPlayer(this.playerUUID).getName();

    }

    public boolean isInGame(Player player){
        if (DuelsPro.getInstance().inGame.contains(player)){
            return true;
        }
        return false;
    }

    public void addPlayer(Player player){
        if(!DuelsPro.getInstance().inGame.contains(player)){
            DuelsPro.getInstance().inGame.add(player);
        }
    }

    public void createPlayerFiles(){
        file = new File(DuelsPro.getInstance().getDataFolder() + "/players/" + getPlayerUUID().toString() + ".yml");
        YamlConfiguration yaml = YamlConfiguration.loadConfiguration(file);
        yaml.addDefault("name",getName());
        yaml.addDefault("uuid",getPlayerUUID().toString());
        yaml.options().copyDefaults(true);
        try {
            yaml.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }


        if(!file.exists()){
            try {
                file.mkdirs();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public UUID getPlayerUUID(){
        return this.playerUUID;
    }

    public String getName(){
        return this.name;
    }

}
