package pw.JA.mobspawn;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Cow;
import org.bukkit.plugin.java.JavaPlugin;
import org.yaml.snakeyaml.Yaml;

import java.util.Map;

/**
 * Created by jaapa on 17/05/2016.
 */

public class mobspawning  extends JavaPlugin implements Runnable{


    public Map getMobsAndInfo(String yml,String mob) {
        Yaml yaml = new Yaml();
        Map map = (Map) yaml.load(yml);
        getLogger().info(yml);
        Map nmap = (Map) map.get(mob);
        getLogger().info(String.valueOf(nmap == null));
        if (nmap ==  null) {
            Map FMap = null;
            return FMap;
        }

        return nmap;
    }

    public mobspawning(Location location) {
        System.out.println("Hello World!");
        Cow cow = (Cow) Bukkit.getWorld("world").spawn(location, Cow.class);
        cow.setCustomName("hoi");
        cow.setCustomNameVisible(true);
    }



    @Override
    public void run() {

    }
}
