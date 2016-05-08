package pw.JA.mobspawn;

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
        if (cmd.getName().equalsIgnoreCase("MS")){
            if (sender instanceof Player){
                Player player = (Player)  sender;
                String name = sender.getName();
                player.sendMessage("hey you are "+name+"aren't you?");
            }else{
                sender.sendMessage("NO ONLY PLAYERS GRRR");
            }
        }

        return false;
    }
}
