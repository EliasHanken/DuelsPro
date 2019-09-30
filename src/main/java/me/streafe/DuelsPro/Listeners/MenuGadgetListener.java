package me.streafe.DuelsPro.Listeners;


import me.streafe.DuelsPro.Gadgets.Head;
import me.streafe.DuelsPro.Gadgets.Menu;
import me.streafe.DuelsPro.utils.Utils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class MenuGadgetListener implements Listener {

    Utils utils = new Utils();

    @EventHandler
    public void onPlayerInteract(InventoryClickEvent event){
        Player player = (org.bukkit.entity.Player) event.getWhoClicked();

        if(event.getClickedInventory() != null || event.getClickedInventory().getName() != null){
            if(event.getCurrentItem() instanceof Head){
                event.setCancelled(true);
                Head selected = (Head) event.getCurrentItem();
                selected.enchantItem();

                if(selected.hasEnchantment()){
                    selected.removeEnchant();
                }


            }
            if(event.getClickedInventory().getName().equalsIgnoreCase("home menu")){
                Material next = event.getCurrentItem().getType();
                if(next == Material.ARROW){
                    event.setCancelled(true);
                    player.closeInventory();
                    Menu heads = new Menu(player,"heads",9);
                    heads.createHeadDisplay();
                    heads.openInv(player);
                }
            }





        }

    }
}
