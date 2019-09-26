package me.streafe.DuelsPro.Listeners;


import me.streafe.DuelsPro.DuelsPro;
import me.streafe.DuelsPro.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PlayerBannedEvent implements Listener {

    private boolean banned = false;

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        Player player = new Player(event.getPlayer().getUniqueId());
        try {
            Statement st = DuelsPro.getInstance().getSql().getConnection().createStatement();
            ResultSet rs = st.executeQuery("SELECT banned FROM playerinfo WHERE uuid='"+player.getPlayerUUID()+"'");
            if(rs.next()){
                this.banned = rs.getBoolean("banned");
            }
            else{
                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(this.banned){
            event.getPlayer().kickPlayer("You are banned");
        }
    }
}

