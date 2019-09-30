package me.streafe.DuelsPro.Gadgets;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.UUID;

public class Head extends ItemStack {

    private String name;
    private ItemStack itemStack;
    private SkullMeta skullMeta;
    private int invSlot;
    private Inventory inv;
    private boolean enchanted = false;

    public Head(String name, Inventory inv){
        this.name = name;
        this.inv = inv;
    }

    public ItemStack createHeadReturn(){
        this.itemStack = new ItemStack(Material.SKULL_ITEM,1,(short)3);
        this.skullMeta = (SkullMeta) itemStack.getItemMeta();
        skullMeta.setOwner(name);
        skullMeta.setDisplayName(name);
        itemStack.setItemMeta(skullMeta);


        return itemStack;
    }

    public void enchantItem(){
        this.skullMeta.addEnchant(Enchantment.KNOCKBACK,1,true);
        skullMeta.getItemFlags().add(ItemFlag.HIDE_ATTRIBUTES);
        this.itemStack.setItemMeta(skullMeta);
        this.enchanted = true;
    }

    public void removeEnchant(){
        this.skullMeta.removeEnchant(Enchantment.KNOCKBACK);
        skullMeta.getItemFlags().remove(ItemFlag.HIDE_ATTRIBUTES);
        this.itemStack.setItemMeta(skullMeta);
        this.enchanted = false;
    }

    public boolean hasEnchantment(){
        if(this.enchanted){
            return true;
        }

        return false;
    }
}
