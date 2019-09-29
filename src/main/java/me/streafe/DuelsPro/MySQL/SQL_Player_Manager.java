package me.streafe.DuelsPro.MySQL;

import me.streafe.DuelsPro.DuelsPro;
import me.streafe.DuelsPro.Player;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import javax.swing.plaf.nimbus.State;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

public class SQL_Player_Manager {

    private Player player;


    public SQL_Player_Manager(Player player){
        this.player = player;

    }


    public void createPlayerFirstTime(){
        try {
            Statement st = DuelsPro.getInstance().getSql().getConnection().createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM playerinfo WHERE uuid='"+player.getPlayerUUID()+"'");
            if(!rs.next()){
                st.executeUpdate("INSERT INTO playerinfo (uuid, name, nick, rank, banned, overallKills, duelsKills, level,op,receiveMsg) VALUES ('"+player.getPlayerUUID()+"','"+
                        Bukkit.getPlayer(player.getPlayerUUID()).getName() +"', '"+
                        Bukkit.getPlayer(player.getPlayerUUID()).getName()+"', 'default' , 0, 0, 0, 0, 1,0)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createPlayerTable(){
        try{
            Statement st =  DuelsPro.getInstance().getSql().getConnection().createStatement();
            st.executeUpdate("CREATE TABLE IF NOT EXISTS playerInfo ( uuid VARCHAR(45), name VARCHAR(45), nick VARCHAR(45)," +
                        "online BOOL, banned BOOL, overallKills INT(10), duelsKills INT(10), level INT(10), receiveMsg BOOL," +
                        "rank VARCHAR(45), op BOOL ) ");
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    public void updatePlayer(UUID uuid, int online){
        try {
            Statement st = DuelsPro.getInstance().getSql().getConnection().createStatement();
            st.executeUpdate("UPDATE playerinfo SET online='"+online+"' WHERE uuid = '"+uuid+"'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateColumn(UUID uuid, String columnName, String value){
        try{
            Statement st = DuelsPro.getInstance().getSql().getConnection().createStatement();
            st.executeUpdate("UPDATE playerinfo SET "+columnName+"='"+value+"' WHERE uuid='"+uuid+"'");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void updateColumn(UUID uuid, String columnName, int value){
        try{
            Statement st = DuelsPro.getInstance().getSql().getConnection().createStatement();
            st.executeUpdate("UPDATE playerinfo SET "+columnName+"="+value+" WHERE uuid='"+uuid+"'");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
