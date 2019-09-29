package me.streafe.DuelsPro.Commands;

import me.streafe.DuelsPro.DuelsPro;
import me.streafe.DuelsPro.MySQL.SQL_Player_Manager;
import me.streafe.DuelsPro.Player;
import me.streafe.DuelsPro.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UnBan implements CommandExecutor {

    private Utils utils = new Utils();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String args[]) {
        if (sender instanceof org.bukkit.entity.Player) {
            org.bukkit.entity.Player sender_ = (org.bukkit.entity.Player) sender;
            if(cmd.getName().equalsIgnoreCase("unban")){
                if (args.length == 1){
                    Player target = new Player(Bukkit.getPlayer(args[0]).getUniqueId());
                    Player player = new Player(sender_.getUniqueId());
                    int op = 0;
                    try {
                        Statement st = DuelsPro.getInstance().getSql().getConnection().createStatement();
                        ResultSet rs = st.executeQuery("SELECT op FROM playerinfo WHERE uuid='"+player.getPlayerUUID()+"'");
                        while(rs.next()){
                            if(rs.getInt("op") == 1){
                                SQL_Player_Manager sql_player_manager = new SQL_Player_Manager(target);
                                sql_player_manager.updateColumn(target.getPlayerUUID(),"banned",0);
                                player.sendMessage(utils.translate("&aYou successfully unbanned " + Bukkit.getPlayer(target.getPlayerUUID()).getName()));
                            }else{
                                player.sendMessage(utils.translate("&cYou are not an Admin!"));
                            }
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return true;
    }
}
