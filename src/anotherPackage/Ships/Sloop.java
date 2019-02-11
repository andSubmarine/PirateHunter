package anotherPackage.Ships;

import anotherPackage.MainActivity;
import anotherPackage.Entity.Player;

public class Sloop extends Ship {
	
	// One of the three ships currently in the game:
	// The sloop: has one mast, 14 cannons, 100 health and
	// has the special ability to escape during battle
	
	public static int hullHealth = 100;
	public static int nrCannons = 14;
	public static int nrMasts = 1;
	public final static int shipID = 1;
	public static final int xpGain = 25;
	public static final int goldGain = 25;
	public static final String shipName = "Sloop";
	
	// Constructor of the ship: uses the constructor of the ship class with the standard values of the sloop
	public Sloop(int armour) {		
		super(hullHealth, nrCannons, nrMasts, shipID, xpGain, goldGain, shipName);
	}
	
	// The special attack of the sloop: used by the player 
	public void specialAttack(Ship playerShip, Player player) {
		// Chance decides whether the player succeds or not
		if (Math.random() < 0.35) {
			// The player has succeded escaping the battle
			System.out.println("You have escaped! You've gained 0 xp");
			MainActivity.timeoffSet();
			System.out.println("You have currently "+playerShip.hullHealth+" health left");
			MainActivity.timeoffSet();
			MainActivity.game(playerShip, player, false);
		} else {
			System.out.println("Oh no! You failed escaping!");
		}
	}
	
	// The special attack of the sloop: used by the enemy
	public void enemySpecialAttack(Ship playerShip, Player player) {
		// Chance decides whether the player succeds or not
		if (Math.random() < 0.35) {
			// The enemy has succeded to escape from battle
			System.out.println("The enemy have escaped! You've gained 0 xp");
			MainActivity.timeoffSet();
			System.out.println("You have currently "+playerShip.hullHealth+" health left");
			MainActivity.timeoffSet();
			MainActivity.game(playerShip, player, false);
		} else {
			System.out.println("The enemy tried to escaped, but you caught up to it");
		}
	}
}
