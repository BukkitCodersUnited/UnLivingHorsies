package me.drkmatr1984.unlivinghorsies;

import java.util.Random;

import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Horse;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.event.entity.EntityDeathEvent;

public class hListener implements Listener{
	
public static Random random = new Random();
@EventHandler
  public void onCreatureSpawn(CreatureSpawnEvent e) {
	for(World world : UnLivingHorsies.enabledWorlds)
	{
		if(e.getEntity().getWorld() == world)
		{
			if((e.getSpawnReason() == SpawnReason.CHUNK_GEN) || (e.getSpawnReason() == SpawnReason.NATURAL) || (e.getSpawnReason() == SpawnReason.BREEDING) || (e.getSpawnReason() == SpawnReason.CUSTOM) || (e.getSpawnReason() == SpawnReason.SPAWNER) || (e.getSpawnReason() == SpawnReason.SPAWNER_EGG) || (e.getSpawnReason() == SpawnReason.DEFAULT)){
		    	if (e.getEntityType() == EntityType.HORSE) {
				  int a = random.nextInt(100) + 1;
				  int b = random.nextInt(100) + 1;
				  if (a <= UnLivingHorsies.spawnChance){
					  if (b <= UnLivingHorsies.skeletonTozombieChance) {
						  UnLivingHorsies.spawnSkeletonHorse(e.getEntity().getLocation());						  
						  e.getEntity().remove();
					  }else{
					  UnLivingHorsies.spawnZombieHorse(e.getEntity().getLocation());
					  e.getEntity().remove();
					  }
				  }  
			  }
		    }
		}
	}
	  return;
  }

  @EventHandler
  public void onEntityDeath(EntityDeathEvent e) {
	  for(World world : UnLivingHorsies.enabledWorlds)
		{
			if(e.getEntity().getWorld() == world)
			{
				if (e.getEntityType() == EntityType.HORSE) {
			      Horse h = (Horse)e.getEntity();
			      if ((h.getVariant() == Horse.Variant.SKELETON_HORSE) || (h.getVariant() == Horse.Variant.UNDEAD_HORSE)) return;
			      int c = random.nextInt(200) + 1;
			      if ((c <= UnLivingHorsies.horseToSkeletonChance) && (UnLivingHorsies.horseToSkeletonChance != 0)) {
			    	  UnLivingHorsies.spawnSkeletonHorse(e.getEntity().getLocation());
			      }
			      if ((c >= 100) && (c <= 100 + UnLivingHorsies.horseToZombieChance) && (UnLivingHorsies.horseToZombieChance != 0))
			    	  UnLivingHorsies.spawnZombieHorse(e.getEntity().getLocation());
			      }
			  }
		}
  }
}