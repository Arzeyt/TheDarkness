package us.twoguys.thedarkness.mechanics.mirages;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.entity.Player;

import us.twoguys.thedarkness.TheDarkness;
/**
 * @effect: PotionEffect
 * @Description: Applys a potion effect to the player
 */
/*
 * Params:
 * 		Mandatory:
 * 			- setting[0]: mirage name
 * 			- setting[1]: % chance
 * 			- setting[2]: duration
 * 		Optional:
 * 			- setting[3]: frequency (ticks)
 * */
public class Mirage {


	TheDarkness plugin;
	
	protected Player player;
	protected int level;
	protected int taskId;
	protected ArrayList<Integer> setting;
	
	protected Random random = new Random();
	
	public Mirage(TheDarkness instance, Player player, int level, ArrayList<Integer> setting){
		plugin = instance;
		this.player = player;
		this.level = level;
		this.setting = setting;
	}
}


