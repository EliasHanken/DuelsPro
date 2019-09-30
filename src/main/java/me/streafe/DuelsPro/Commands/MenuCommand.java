package me.streafe.DuelsPro.Commands;

import me.streafe.DuelsPro.Gadgets.Menu;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MenuCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String args[]) {
        if(sender instanceof Player){
            Player player = (Player) sender;
            if(args.length < 1){
                if (cmd.getName().equalsIgnoreCase("menu")){
                    Menu menu = new Menu(player,"gadgets",18);
                    menu.createHomeDisplay();
                }
            }
        }
        return true;
    }
}
