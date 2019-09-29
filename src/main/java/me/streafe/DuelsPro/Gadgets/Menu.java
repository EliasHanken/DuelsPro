package me.streafe.DuelsPro.Gadgets;

import me.streafe.DuelsPro.DuelsPro;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.UUID;

public class Menu implements Listener {

    private Inventory menu;
    private int size;
    private String title;
    private org.bukkit.entity.Player player;
    private int stringInt;
    private int i = 0;



    public Menu(Player player, String title, int size){
        this.player = player;
        this.title = title;
        this.size = size;
    }

    public void createDisplay(){
        this.menu = Bukkit.createInventory(null,this.size,this.title);
        this.stringInt = DuelsPro.getInstance().getConfig().getStringList("gadgets.heads.default").size();
        List<String> list = DuelsPro.getInstance().getConfig().getStringList("gadgets.heads.default");


        while(i < this.stringInt){
            Head head = new Head(list.get(i),menu);
            menu.setItem(i,head.createHeadReturn());
            i = i +1;
        }


        this.player.openInventory(menu);

        this.player.sendMessage(list.toString());

    }
}
