package com.thekingelessar.spintowin;

import com.google.gson.internal.$Gson$Preconditions;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerGameModeChangeEvent;
import org.bukkit.scheduler.BukkitTask;
import sun.security.provider.ConfigFile;

import java.util.Collection;

public class ListenerSpinToWin implements Listener
{
    
    public static BukkitTask spinTask;
    public static boolean spinning = false;
    
    @EventHandler
    public void onPlayerGamemodeChange(PlayerGameModeChangeEvent event)
    {
        Server server = event.getPlayer().getServer();
        Collection<? extends Player> onlinePlayers = server.getOnlinePlayers();
        if (onlinePlayers.size() > 2)
        {
            int adventureCount = 0;
            int spectatorCount = 0;
            
            if(event.getPlayer().getGameMode().equals(GameMode.ADVENTURE) && event.getNewGameMode().equals(GameMode.SPECTATOR))
            {
                spectatorCount++;
            }
    
            if(event.getPlayer().getGameMode().equals(GameMode.SPECTATOR) && event.getNewGameMode().equals(GameMode.ADVENTURE))
            {
                adventureCount++;
            }
    
            for (Player player : onlinePlayers)
            {
                if (!player.getUniqueId().equals(event.getPlayer().getUniqueId()))
                {
                    GameMode gameMode = player.getGameMode();
                    if (gameMode == GameMode.ADVENTURE) adventureCount++;
                    if (gameMode == GameMode.SPECTATOR) spectatorCount++;
                }
            }
            
            if (adventureCount == 2 && !spinning)
            {
                spinning = true;
                spinTask = new TaskSpinToWin(SpinToWin.instance).runTaskTimer(SpinToWin.instance, 20, 20L);
            }
            
   //         SpinToWin.console.sendMessage(SpinToWin.spinPrefix + "adventure count: " + adventureCount);
    
            if(adventureCount > 2 && spinning) {
                ListenerSpinToWin.spinning = false;
                server.broadcastMessage(ChatColor.GREEN + "SPIN TO WIN CANCELED!");
                spinTask.cancel();
                spinTask = null;
            }
        }
    }
}
