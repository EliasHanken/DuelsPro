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
import java.util.ArrayList;
import java.util.List;

public class BanPlayer implements CommandExecutor {

    private Utils utils = new Utils();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String l, String args[]) {
        if (sender instanceof org.bukkit.entity.Player) {
            org.bukkit.entity.Player sender_ = (org.bukkit.entity.Player) sender;
            if(args.length >= 2){
                if (cmd.getName().equalsIgnoreCase("ban")){
                    String reason = args[1];
                    Player target = new Player(Bukkit.getPlayer(args[0]).getUniqueId());
                    Player player = new Player(sender_.getUniqueId());
                    int op = 0;
                    try {
                        Statement st = DuelsPro.getInstance().getSql().getConnection().createStatement();
                        ResultSet rs = st.executeQuery("SELECT op FROM playerinfo WHERE uuid='"+player.getPlayerUUID()+"'");
                        while(rs.next()){
                            if(rs.getInt("op") == 1){
                                String argsReason = "";
                                List<String> reasonBan = new ArrayList<>();

                                for(int i = 1; i < args.length; i++){
                                    if(i == 1){
                                        argsReason = args[1];
                                    }
                                    else{
                                        argsReason = argsReason + ", " + args[i];
                                    }

                                }

                                reasonBan.add(utils.translate("&7[Reason(s)] : " + argsReason));

                                SQL_Player_Manager sql_player_manager = new SQL_Player_Manager(target);
                                sql_player_manager.updateColumn(target.getPlayerUUID(),"banned",1);

                                player.sendMessage(utils.translate("&aYou successfully banned " + Bukkit.getPlayer(target.getPlayerUUID()).getName()));
                                player.sendMessage(reasonBan.toString());

                            }else{
                                player.sendMessage(utils.translate("&cYou are not an Admin!"));
                            }
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }else {
                sender.sendMessage("args.length != 2");
            }
        }
        return true;
    }
}
