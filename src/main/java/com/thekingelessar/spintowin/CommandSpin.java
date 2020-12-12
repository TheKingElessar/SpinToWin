package com.thekingelessar.spintowin;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandSpin implements CommandExecutor
{
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if (args == null || args.length == 0) return false;
        
        switch (args[0])
        {
            case "announce":
                if (sender.hasPermission("spintowin.announce"))
                {
                    if (args.length > 1)
                    {
                        double announce;
                        try
                        {
                            announce = Double.parseDouble(args[1]);
                        }
                        catch (NumberFormatException exception)
                        {
                            sender.sendMessage(SpinToWin.spinPrefix + "Error: Announcement delay must be a number");
                            return true;
                        }
                        
                        SpinToWin.instance.loadConfig();
                        TaskSpinToWin.announce = announce;
    
                        SpinToWin.instance.getConfig().set("delay", announce);
                        SpinToWin.instance.saveConfig();
    
                        sender.sendMessage(SpinToWin.spinPrefix + "Set announcement delay: " + TaskSpinToWin.announce);
                    }
                    else
                    {
                        return false;
                    }
                }
                else
                {
                    sender.sendMessage(ChatColor.RED + "You do not have permission to use this command!");
                }
                return true;
            
            case "seconds":
                if (sender.hasPermission("spintowin.seconds"))
                {
                    if (args.length > 1)
                    {
                        int seconds;
                        try
                        {
                            seconds = Integer.parseInt(args[1]);
                        }
                        catch (NumberFormatException exception)
                        {
                            sender.sendMessage(SpinToWin.spinPrefix + "Error: Seconds must be an integer");
                            return true;
                        }
                        
                        SpinToWin.instance.loadConfig();
                        TaskSpinToWin.fullSeconds = seconds;
    
                        SpinToWin.instance.getConfig().set("seconds", seconds);
                        SpinToWin.instance.saveConfig();
                        
                        sender.sendMessage(SpinToWin.spinPrefix + "Set seconds: " + seconds);
                    } else {
                        return false;
                    }
                }
                else
                {
                    sender.sendMessage(ChatColor.RED + "You do not have permission to use this command!");
                }
                return true;
            
            case "reload":
                if(sender.hasPermission("spintowin.reload"))
                {
                    SpinToWin.instance.loadConfig();
                    sender.sendMessage(SpinToWin.spinPrefix + "Reloaded config!");
                }
                else
                {
                    sender.sendMessage(ChatColor.RED + "You do not have permission to use this command!");
                }
                return true;
    
            default:
                return false;
        }
    }
}