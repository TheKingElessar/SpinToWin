package com.thekingelessar.spintowin;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Collection;

public class TaskSpinToWin extends BukkitRunnable
{
    static public Server server;
    static public int fullSeconds = 60;
    static public int secondsLeft = 60;
    
    static public double announce = 10;
    
    public TaskSpinToWin(JavaPlugin plugin)
    {
        secondsLeft = fullSeconds;
        server = plugin.getServer();
        server.broadcastMessage(ChatColor.GREEN + "SPIN TO WIN IN 60 SECONDS!");
        secondsLeft--;
    }
    
    @Override
    public void run()
    {
        if (secondsLeft == 0)
        {
            Collection<? extends Player> players = server.getOnlinePlayers();
            String spin = ChatColor.AQUA.toString() + ChatColor.BOLD.toString() + "SPIN ";
            String to = ChatColor.GREEN.toString() + ChatColor.BOLD.toString() + "TO ";
            String win = ChatColor.LIGHT_PURPLE.toString() + ChatColor.BOLD.toString() + "WIN!!!";
            server.broadcastMessage(ChatColor.GREEN + "SPIN TO WIN IN " + secondsLeft + " SECONDS!");
    
            for (Player player : players)
            {
                player.sendTitle(spin + to + win, ChatColor.GOLD + "Release the moles");
                GameMode gameMode = player.getGameMode();
                if (gameMode == GameMode.SPECTATOR) player.setGameMode(GameMode.SURVIVAL);
            }
            
            ListenerSpinToWin.spinning = false;
            this.cancel();
        }
        else if (secondsLeft % announce == 0)
        {
            server.broadcastMessage(ChatColor.GREEN + "SPIN TO WIN IN " + secondsLeft + " SECONDS!");
        }
        
        secondsLeft--;
    }
    
    @Override
    public synchronized void cancel() throws IllegalStateException
    {
        ListenerSpinToWin.spinning = false;
        server.broadcastMessage(ChatColor.GREEN + "SPIN TO WIN CANCELED!");
    
        super.cancel();
    }
}