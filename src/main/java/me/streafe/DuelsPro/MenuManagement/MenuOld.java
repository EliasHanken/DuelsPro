package me.streafe.DuelsPro.MenuManagement;

import me.streafe.DuelsPro.DuelsPro;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class MenuOld {

    private int size;
    private String name;
    private ItemStack background;
    private Inventory inventory;

    public MenuOld(String name, int size, ItemStack background){
        this.background = background;
        this.size = size;
        this.name = name;
    }

    public void createView(){
        background = new ItemStack((Material) DuelsPro.getInstance().getConfig().get("menu.background"),(short)3);
        this.inventory = Bukkit.createInventory(null,this.size,this.name);

        for(int i = 0; i < this.size; i++){
            inventory.setItem(i,background);
        }

    }
}
