package me.streafe.DuelsPro.Gadgets;

import me.streafe.DuelsPro.DuelsPro;
import me.streafe.DuelsPro.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
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
    private Utils utils = new Utils();



    public Menu(Player player, String title, int size){
        this.player = player;
        this.title = title;
        this.size = size;
    }

    public void createHeadDisplay(){
        if(this.size < stringInt-1){
            this.menu = Bukkit.createInventory(null,this.size,this.title);
        }
        else if(this.size > stringInt-1){
            this.menu = Bukkit.createInventory(null,this.size + 9,this.title);
        }
        this.stringInt = DuelsPro.getInstance().getConfig().getStringList("gadgets.heads.default").size();
        List<String> list = DuelsPro.getInstance().getConfig().getStringList("gadgets.heads.default");


        while(i < this.stringInt){
            Head head = new Head(list.get(i),menu);
            menu.setItem(i,head.createHeadReturn());
            i = i +1;
        }

        if(menu.getItem(8) == null){
            for(int z = 0; z <= stringInt; z++){
                ItemStack item = utils.createItem("&anext",Material.ARROW);
                menu.setItem(size -1,item);
            }
        } else if(menu.getItem(8) != null){
            for(int z = 0; z <= stringInt; z++){
                ItemStack item = utils.createItem("&anext",Material.ARROW);
                menu.setItem(size +8,item);
            }
        }




        this.player.openInventory(menu);

        this.player.sendMessage(list.toString());

    }

    public void createHomeDisplay(){

        this.menu = Bukkit.createInventory(null,27,"home menu");

        this.stringInt = DuelsPro.getInstance().getConfig().getStringList("gadgets.home_menu").size();
        List<String> list = DuelsPro.getInstance().getConfig().getStringList("gadgets.home_menu");

        while(i < this.stringInt && i < this.size-1){
            Head head = new Head(list.get(i),menu);
            menu.setItem(i,head.createHeadReturn());
            i = i +1;
        }

        menu.setItem(26,utils.createItem("&anext",Material.ARROW));


        this.player.openInventory(menu);
    }

    public void openInv(Player player){
        player.openInventory(menu);
    }
}
