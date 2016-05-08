package pw.JA.mobspawn;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class mobspawn extends JavaPlugin{
    public void onEnable(){
        getLogger().info("getlogger at its best");
        System.out.println("and the clasic ones");
    }
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String Label, String[] args){
        if (cmd.getName().equalsIgnoreCase("AMS")){
            if (sender instanceof Player){
                Player player = (Player)  sender;
                if(player.hasPermission("mobspawn.mod")){
                    player.sendMessage(ChatColor.AQUA+"you have the correct permissions");
                }else{
                    player.sendMessage(ChatColor.RED+"you do not have the correct permissions");
                }
            }else{
                sender.sendMessage("NO ONLY PLAYERS GRRRWHAHAH");
            }
        }
        if (cmd.getName().equalsIgnoreCase("test")){
            if (sender instanceof Player){
                Player player = (Player)  sender;
                String name = sender.getName();
                player.sendMessage(ChatColor.AQUA+"hey you are "+name+" aren't you?");
            }else{
                sender.sendMessage("NO ONLY PLAYERS GRRRWHAHAH");
            }
        }

        return false;
    }
}
