package me.streafe.DuelsPro.utils;

import me.streafe.DuelsPro.Player;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import org.bukkit.ChatColor;

public class Utils {

    public String translate(String string){
        return ChatColor.translateAlternateColorCodes('&',string);
    }

    public void sendTitle(Player player, int fadeIn, int duration, int fadeOut, String message){
        IChatBaseComponent titleJson = new IChatBaseComponent.ChatSerializer().a("{\"text\":}" + translate(message)  +"\"}");
        PacketPlayOutTitle title = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE,titleJson,fadeIn,duration,fadeOut);
    }

    public void sendSubtitle(Player player, int fadeIn, int duration, int fadeOut, String message){
        IChatBaseComponent titleJson = new IChatBaseComponent.ChatSerializer().a("{\"text\":}" + translate(message)  +"\"}");
        PacketPlayOutTitle title = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE,titleJson,fadeIn,duration,fadeOut);

    }


}
