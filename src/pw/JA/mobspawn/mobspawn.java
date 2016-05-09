package pw.JA.mobspawn;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.EventHandler;
import java.util.HashMap;
import java.util.UUID;

public class mobspawn extends JavaPlugin implements Listener{
    public void onEnable(){
        getLogger().info("getlogger at its best");
        System.out.println("and the clasic ones");
        getServer().getPluginManager().registerEvents(this, this);
    }
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String Label, String[] args){
        if (cmd.getName().equalsIgnoreCase("AMS")){
            if (sender instanceof Player){
                Player player = (Player)  sender;
                if(player.hasPermission("ams")){
                    player.sendMessage(ChatColor.AQUA+"you have the correct permissions");
                    usrPermunset(player, "ams");
                    player.sendMessage(ChatColor.RED+"just not anymore");
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
    public void usrPermunset(Player player, String permission){
        HashMap<UUID, PermissionAttachment> perms = new HashMap<UUID, PermissionAttachment>();
        PermissionAttachment attach = player.addAttachment(this);
        perms.put(player.getUniqueId(), attach);
        attach.setPermission(permission, false);
        player.sendMessage(ChatColor.YELLOW+"test it again");
    }
    public void usrPermset(Player player, String permission){
        HashMap<UUID, PermissionAttachment> perms = new HashMap<UUID, PermissionAttachment>();
        PermissionAttachment attach = player.addAttachment(this);
        perms.put(player.getUniqueId(), attach);
        attach.setPermission(permission, true);
    }
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player playerInfo = event.getPlayer();
        String name = playerInfo.getName();
        playerInfo.sendMessage(name);
        boolean TF = name.equals("jaap313");
        playerInfo.sendMessage(Boolean.toString(TF)+" "+name.length());
        playerInfo.sendMessage(name);
        playerInfo.sendMessage("jaap313");
        if (name.equals("jaap313")) {
            playerInfo.sendMessage("almost  ");
            usrPermset( playerInfo, "ams");
            playerInfo.sendMessage("done");

        }
    }
}
