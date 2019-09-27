package me.streafe.DuelsPro.Commands;

import me.streafe.DuelsPro.DuelsPro;
import me.streafe.DuelsPro.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.*;

public class PlayerList implements CommandExecutor {

    private Utils utils = new Utils();
    private List<String> list;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String args[]) {
        if(sender instanceof Player){
            Player player = (Player) sender;
            if(args.length <1){
                if(cmd.getName().equalsIgnoreCase("playerlist")){
                    for(UUID uuid : DuelsPro.getInstance().getPlayersClassList()){

                        this.list = new ArrayList<>();
                        list.add(Bukkit.getPlayer(uuid).getName());
                    }
                    player.sendMessage(utils.translate(DuelsPro.getInstance().getConfig().get("duelspro.prefix").toString())+utils.translate("&aPlayers online:"));
                    player.sendMessage(utils.translate("&7------------------------"));
                    player.sendMessage(utils.translate("&7" + this.list.toString()));
                }
            }

        }
        return true;
    }
}
