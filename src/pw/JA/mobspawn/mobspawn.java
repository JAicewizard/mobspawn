package pw.JA.mobspawn;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
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
import java.util.*;

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
    }

    public List<String> searchPlayerGroup(String yml, String Player){
        Yaml yaml = new Yaml();
        getLogger().info(yml);
        Map map = (Map) yaml.load(yml);
        Map nmap = (Map) map.get(Player);
        getLogger().info("lie!");
        Map namap = (Map) nmap.get("groups");
        List<String> list = new ArrayList(namap.keySet());
        return list;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String Label, String[] args){
        java.util.List<String> list = new ArrayList<String>(Arrays.asList(args));
        if (cmd.getName().equalsIgnoreCase("AMS")){
            if (sender instanceof Player){
                Player player = (Player)  sender;
                if(args.length == 0){
                    player.sendMessage(ChatColor.RED+"usage:'ams [mode]'");
                }else {
                    switch (args[0]) {
                        case "add":
                            list.remove(0);
                            if (player.hasPermission("ams")) {

                                for(int i = 0; i< 3; i++) {
                                    if(list.toArray().length <= i) {
                                        player.sendMessage(ChatColor.RED+"please enter your "+(i+1)+"th value of the location");
                                        return false;
                                    }
                                }
                                double x = Double.parseDouble(args[1]);
                                double y = Double.parseDouble(args[2]);
                                double z = Double.parseDouble(args[3]);
                                Location location = new Location(Bukkit.getWorld("world"),x,y,z);
                                if(location == null){
                                    player.sendMessage(ChatColor.RED+"location is not recognized");
                                }else{
                                    player.teleport(location);
                                }




                            } else {
                                player.sendMessage(ChatColor.RED + "you do not have the correct permissions");
                            }

                            break;
                    }
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
    public void OnJoin(PlayerJoinEvent event) {
        getLogger().info("playerloged in");
        String permUrl = "plugins/mobspawn/perm.yml";
        File permFile = new File(permUrl);

        Player playerInfo = event.getPlayer();
        playerInfo.sendMessage(ChatColor.AQUA+"hello"+playerInfo.getDisplayName());
        if (permFile.exists()) {
            try {
                String ducument = new String(Files.readAllBytes(Paths.get(permUrl)));
                List groups =searchPlayerGroup(ducument, playerInfo.getDisplayName());
                for(int i = 0; i < groups.size(); i++) {
                    playerInfo.sendMessage(String.valueOf(groups.get(i)));
                    usrPermset(playerInfo, String.valueOf(groups.get(i)));
                }

            }catch (java.io.IOException e){
                e.printStackTrace();
            }
        }
    }
}
