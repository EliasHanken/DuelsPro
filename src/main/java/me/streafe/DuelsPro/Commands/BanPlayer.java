package me.streafe.DuelsPro.Commands;

import me.streafe.DuelsPro.MySQL.SQL_Player_Manager;
import me.streafe.DuelsPro.Player;
import me.streafe.DuelsPro.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class BanPlayer implements CommandExecutor {

    private Utils utils = new Utils();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String l, String args[]) {
        if(sender instanceof Player){
            Player player = (Player) sender;
            if(cmd.getName().equalsIgnoreCase("ban") && args.length == 2){
                Player target = new Player(Bukkit.getPlayer(args[1]).getUniqueId());
                SQL_Player_Manager player_manager = new SQL_Player_Manager(target);
                player_manager.updateColumn(target.getPlayerUUID(),"banned",1);
            }
        }
        return true;
    }
}
