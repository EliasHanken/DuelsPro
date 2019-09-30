package me.streafe.DuelsPro;

import me.streafe.DuelsPro.Commands.BanPlayer;
import me.streafe.DuelsPro.Commands.MenuCommand;
import me.streafe.DuelsPro.Commands.UnBan;
import me.streafe.DuelsPro.Listeners.MenuGadgetListener;
import me.streafe.DuelsPro.Listeners.PlayerBannedEvent;
import me.streafe.DuelsPro.MySQL.SQL;
import me.streafe.DuelsPro.MySQL.SQL_Player_Manager;
import me.streafe.DuelsPro.Commands.PlayerList;
import me.streafe.DuelsPro.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class DuelsPro extends JavaPlugin implements Listener {

    public Map<UUID,Player> players;
    private static DuelsPro instance;
    public List<Player> inGame;
    public Utils utils;
    public SQL sql;
    public String host, database, user, password;
    public int port;
    public SQL_Player_Manager sql_player_manager;

    @Override
    public void onEnable(){
        instance = this;
        this.host = getConfig().get("mysql.host").toString();
        this.database = getConfig().get("mysql.database").toString();
        this.user = getConfig().get("mysql.user").toString();
        this.password = getConfig().get("mysql.password").toString();
        this.port = 3306;
        this.inGame = new ArrayList<Player>();
        this.players = new HashMap<>();
        this.utils = new Utils();
        this.sql = new SQL(this.host,this.database,this.user,this.password,this.port);

        try {
            this.sql.openConnection();
            this.sql.createTable("playerSettings");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Bukkit.getPluginCommand("PlayerList").setExecutor(new PlayerList());
        Bukkit.getPluginCommand("ban").setExecutor(new BanPlayer());
        Bukkit.getPluginCommand("unban").setExecutor(new UnBan());
        Bukkit.getPluginCommand("menu").setExecutor(new MenuCommand());

        getServer().getConsoleSender().sendMessage(this.utils.translate(getConfig().get("duelspro.prefix").toString() + " &dHas been enabled"));

        getServer().getConsoleSender().sendMessage(this.host + ":" + this.database + ":" + this.user + ":" + this.password + ":" + this.port);
        getServer().getPluginManager().registerEvents(this,this);
        getServer().getPluginManager().registerEvents(new PlayerBannedEvent(),this);
        getServer().getPluginManager().registerEvents(new MenuGadgetListener(),this);


        getConfig().options().copyDefaults(true);
        saveConfig();


    }

    @Override
    public void onDisable(){
        try {
            this.sql.getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static DuelsPro getInstance(){
        return instance;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){


        if(!this.players.containsKey(event.getPlayer().getUniqueId())){
            Player playerClass = new Player(event.getPlayer().getUniqueId());
            players.put(playerClass.getPlayerUUID(),playerClass);
            event.getPlayer().sendMessage(getPlayersClassList().toString());
            event.getPlayer().sendMessage(utils.translate(getConfig().get("duelspro.prefix").toString()) + utils.translate("&aSuccessfully added to the databases!"));
        }else{
            event.getPlayer().sendMessage(utils.translate(getConfig().get("duelspro.prefix").toString()) + utils.translate("&cError: &7Please rejoin or ask admin for help"));
            event.getPlayer().sendMessage(utils.translate(getConfig().get("duelspro.prefix").toString()) +utils.translate("&cError code &7-> 1"));
        }

        this.sql_player_manager = new SQL_Player_Manager(new Player(event.getPlayer().getUniqueId()));
        this.sql_player_manager.createPlayerTable();
        this.sql_player_manager.createPlayerFirstTime();
        sql_player_manager.updatePlayer(event.getPlayer().getUniqueId(),1);


    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event){
        if(players.containsKey(event.getPlayer().getUniqueId())){
            players.remove(event.getPlayer().getUniqueId());
        }

        this.sql_player_manager.updatePlayer(event.getPlayer().getUniqueId(),0);

    }

    public Set<UUID> getPlayersClassList(){
        return players.keySet();
    }

    public SQL getSql(){
        return this.sql;
    }

}
