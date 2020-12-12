package com.thekingelessar.spintowin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class SpinToWin extends JavaPlugin
{
    
    static public ConsoleCommandSender console = null;
    static public final String spinPrefix = ChatColor.DARK_PURPLE.toString() + "[SpinToWin] " + ChatColor.RESET.toString();
    static public SpinToWin instance;
    
    @Override
    public void onEnable()
    {
        instance = this;
        console = Bukkit.getServer().getConsoleSender();
        
        getServer().getPluginManager().registerEvents(new ListenerSpinToWin(), this);
        this.getCommand("spin").setExecutor(new CommandSpin());
    
        super.onEnable();
    }
    
    @Override
    public void onDisable()
    {
        super.onDisable();
    }
    
    public void loadConfig()
    {
        this.saveDefaultConfig();
        TaskSpinToWin.fullSeconds = Integer.parseInt(SpinToWin.instance.getConfig().get("seconds").toString());
        TaskSpinToWin.announce = Double.parseDouble(SpinToWin.instance.getConfig().get("delay").toString());
    }
    
}