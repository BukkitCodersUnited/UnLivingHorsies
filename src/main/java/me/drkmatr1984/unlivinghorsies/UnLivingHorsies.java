package me.drkmatr1984.unlivinghorsies;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Horse;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.World;

public class UnLivingHorsies extends JavaPlugin
{
  public static int skeletonTozombieChance;
  public static int horseToSkeletonChance;
  public static int spawnChance;
  public static int horseToZombieChance;
  public static List<String> enabledWorldString = new ArrayList<String>();
  public static ArrayList<World> enabledWorlds = new ArrayList<World>();
  PluginManager pm = getServer().getPluginManager();
  public static Plugin plugin;

  public void onEnable() {
    getConfig().options().copyDefaults(true);
    saveConfig();
    initConfig();
    plugin = this;
    pm.registerEvents(new me.drkmatr1984.unlivinghorsies.hListener(), this);
    if(enabledWorlds.isEmpty())
    {
    	getServer().getConsoleSender().sendMessage("§4ERROR: No existing worlds defined in config.yml. Disabling...");
    	plugin.getServer().getPluginManager().disablePlugin(plugin);
    }else{
    	getServer().getConsoleSender().sendMessage("Â§F[Â§5UnLivingHorsies v0.2Â§F] §2Loaded!§FÂ§r");
    }   
  }
  
  public void onDisable()
  {
	getServer().getConsoleSender().sendMessage("Â§F[Â§5UnLivingHorsies v0.2Â§F] §4Disabled!§FÂ§r");
  }

  public void initConfig()
  {
	spawnChance = getConfig().getInt("Percent of natural spawns");
    skeletonTozombieChance = getConfig().getInt("Natural Spawn skeleton to zombie horse chance");
    horseToZombieChance = getConfig().getInt("Horse death to zombie horse chance");
    horseToSkeletonChance = getConfig().getInt("Horse death to skeleton horse chance");
    enabledWorldString = getConfig().getStringList("EnabledWorlds");
    for(String world : enabledWorldString)
    {
        World enabled = Bukkit.getServer().getWorld(world);
        if(enabled == null)
        {
        	getServer().getConsoleSender().sendMessage("§4ERROR: World§F " + world + " §4not found! §5UnLivingHorsies §4will not function!");
        }else{
        	enabledWorlds.add(enabled);
        }       
    }
  }

  public static void spawnSkeletonHorse(Location l) {
    spawnHorse(l).setVariant(Horse.Variant.SKELETON_HORSE);
  }

  public static void spawnZombieHorse(Location l) {
    spawnHorse(l).setVariant(Horse.Variant.UNDEAD_HORSE);
  }

  public static Horse spawnHorse(Location l) {
    Horse h = (Horse)l.getWorld().spawnEntity(l, EntityType.HORSE);
    h.setTamed(false);
    return h;
  }
}