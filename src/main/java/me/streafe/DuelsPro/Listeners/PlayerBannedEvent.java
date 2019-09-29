package me.streafe.DuelsPro.Listeners;


import me.streafe.DuelsPro.DuelsPro;
import me.streafe.DuelsPro.Player;
import me.streafe.DuelsPro.utils.Utils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PlayerBannedEvent implements Listener {

    private boolean banned = false;
    private Utils utils = new Utils();

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
            List<String> list = new ArrayList<>();
            list.add(utils.translate("&c&lYou are banned from the server"));
            list.add(utils.translate("&7Reason: &r&f%reason%"));
            event.getPlayer().kickPlayer(list.toString());
        }
    }
}

