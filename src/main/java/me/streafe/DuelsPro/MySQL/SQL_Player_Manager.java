package me.streafe.DuelsPro.MySQL;

import me.streafe.DuelsPro.DuelsPro;
import org.bukkit.scheduler.BukkitRunnable;

public class SQL_Player_Manager extends BukkitRunnable {


    @Override
    public void run() {

        runTaskLaterAsynchronously(DuelsPro.getInstance(),100L);
    }
}
