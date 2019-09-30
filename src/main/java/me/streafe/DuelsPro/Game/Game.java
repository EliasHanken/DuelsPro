package me.streafe.DuelsPro.Game;

import me.streafe.DuelsPro.DuelsPro;
import org.bukkit.scheduler.BukkitRunnable;

public class Game extends BukkitRunnable {

    @Override
    public void run() {

        runTaskLaterAsynchronously(DuelsPro.getInstance(),100L);
    }

}
