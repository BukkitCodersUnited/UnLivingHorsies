package me.drkmatr1984.unlivinghorsies;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Horse;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class UnLivingHorsies extends JavaPlugin
{
  public static int skeletonTozombieChance;
  public static int horseToSkeletonChance;
  public static int spawnChance;
  public static int horseToZombieChance;
  PluginManager pm = getServer().getPluginManager();

  public void onEnable() {
    getConfig().options().copyDefaults(true);
    saveConfig();
    initConfig();
    pm.registerEvents(new me.drkmatr1984.unlivinghorsies.hListener(), this);
    getServer().getConsoleSender().sendMessage("§F[§5UnLivingHorsies§F] Loaded!§r");
  }

  public void initConfig()
  {
	spawnChance = getConfig().getInt("Percent of natural spawns");
    skeletonTozombieChance = getConfig().getInt("Natural Spawn skeleton to zombie horse chance");
    horseToZombieChance = getConfig().getInt("Horse death to zombie horse chance");
    horseToSkeletonChance = getConfig().getInt("Horse death to skeleton horse chance");
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