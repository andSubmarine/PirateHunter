package anotherPackage.Ships;

import anotherPackage.MainActivity;
import anotherPackage.Entity.Player;

public class Galleon extends Ship {

	// One of the three ships currently in the game:
	// The galleon: has three masts, 34 cannons, 150 health and
	// has the special ability to do a broadside (heavy damage)
	
	public static int hullHealth = 150;
	public static int nrCannons = 34;
	public static int nrMasts = 3;
	public static final int shipID = 3;
	public static final int xpGain = 100;
	public static final int goldGain = 100;
	public static final String shipName = "Galleon";
	
	// Constructor of the ship: uses the constructor of the ship class with the standard values of the galleon
	public Galleon() {
		super(hullHealth, nrCannons, nrMasts, shipID, xpGain, goldGain, shipName);
	}
	
	// The special attack of the galleon: used by the player 
	public void specialAttack(Ship playerShip, Ship enemyShip, Player player) {
		System.out.println("Preparing for broadside!");
		MainActivity.timeoffSet(4000);
		
		// Chance decides whether the galleon hits or miss
		if (Math.random() < 0.80) {
			int playerDamage = 100;
			System.out.println("A great hit sir! We've done "+playerDamage+" damage!");
			enemyShip.hullHealth -= playerDamage;
		} else {
			System.out.println("A complete miss, sir...");
		}
	}
	
	// The special attack of the galleon: used by the enemy
	public void enemySpecialAttack(Ship playerShip, Player player) {
		System.out.println("The enemy is preparing for broadside!");
		MainActivity.timeoffSet(4000);
		
		// Chance decides whether the galleon hits or miss
		if (Math.random() < 0.70) {
			int enemyDamage = 100;
			System.out.println("They've hit us, sir! And done "+enemyDamage+" damage to our ship!");
			playerShip.hullHealth -= enemyDamage;
		} else {
			System.out.println("Thank god! They've missed us!");
		}
	}
}
