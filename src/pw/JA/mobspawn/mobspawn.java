package pw.JA.mobspawn;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.plugin.java.JavaPlugin;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class mobspawn extends JavaPlugin implements Listener{
    public void onEnable(){
        getLogger().info("getlogger at its best");
        System.out.println("and the clasic ones");
        getServer().getPluginManager().registerEvents(this, this);
        File file = new File("plugins/mobspawn");
        if (!file.exists()) {
            if (file.mkdir()) {
                System.out.println("Directory is created!");
            } else {
                System.out.println("Failed to create directory!");
            }
        }

        String permUrl = "plugins/mobspawn/perm.yml";
        File permFile = new File(permUrl);
        if (!permFile.exists()) {
            try {
                permFile.createNewFile();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        if (permFile.exists()) {
            try {
                String ducument = new String(Files.readAllBytes(Paths.get(permUrl)));
                Boolean groups =searchYml(ducument, "hello", "hallo");
                getLogger().info(groups.toString());
            }catch (java.io.IOException e){
                e.printStackTrace();
            }
        }
    }

    public boolean searchYml(String yml, String group, String player){
        Yaml yaml = new Yaml();
        getLogger().info(yml);
        Map map = (Map) yaml.load(yml);
        getLogger().info(map.toString());
        Map nmap = (Map) map.get(group);
        for(String groups:  knsasx)
        getLogger().info(nmap.toString());
        getLogger().info(nmap.get(player).toString());
        String TF = nmap.get(player).toString();
        if(TF != null && !TF.isEmpty()){
            return true;
        }else{
            return false;
        }
        return
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String Label, String[] args){
        if (cmd.getName().equalsIgnoreCase("AMS")){
            if (sender instanceof Player){
                Player player = (Player)  sender;
                if(player.hasPermission("ams")){
                    player.sendMessage(ChatColor.AQUA+"you have the correct permissions");
                    if(args.length == 0){
                        player.sendMessage(ChatColor.RED+"usage:'ams [mode]'");
                    }else{
                        switch (args[0]){
                            case "add":
                                switch(args[1]){
                                    case "op":
                                        Bukkit.getPlayer(args[2]);
                                        break;
                                }
                                break;
                        }
                    }
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
        playerInfo.sendMessage(ChatColor.AQUA+"hello"+playerInfo.getDisplayName());
    }
}
