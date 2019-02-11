package anotherPackage.Ships;

import anotherPackage.CombatSystem;
import anotherPackage.MainActivity;
import anotherPackage.Entity.Player;

public class Frigate extends Ship {
	
	// One of the three ships currently in the game:
	// The frigate: has two masts, 24 cannons, 125 health and
	// has the special ability to board enemy ships
	
	public static int hullHealth = 125;
	public static int nrCannons = 24;
	static int nrMasts = 2;
	public static final int shipID = 2;
	public static final int xpGain = 50;
	public static final int goldGain = 50;
	public static final String shipName = "Frigate";

	// Constructor of the ship: uses the constructor of the ship class with the standard values of the frigate 
	public Frigate() {
		super(hullHealth, nrCannons, nrMasts, shipID, xpGain, goldGain, shipName);
	}
	
	// The special attack of the frigate: used by the player
	public void specialAttack(Ship playerShip, Ship enemyShip, Player player) {
		System.out.println("Preparing to board the enemy!\n");
		MainActivity.timeoffSet(4000);
		
		// Uses the ship method chance of succes to find out whether the ship have an advantage or not
		double chance = playerShip.chanceOfSucces(enemyShip);
		
		// Decides whether the NPC succeds or fails to board the ship
		if (Math.random() < (chance + 0.1)) {
			// The player succeds in boarding the enemy ship
			System.out.println("Victory is ours! We've succesfully captured the enemy ship!");
			
			// Destroys the enemy ship
			enemyShip.hullHealth = 0;
			MainActivity.timeoffSet();
			
			// Decides how much gold the player receives for capturing the enemy ship
			int extraGold;
			double goldenchance = Math.floor(Math.random()*10) / 10;
			if (goldenchance <= 0.3) {
				extraGold = 10;
			} else if (goldenchance <= 0.7) {
				extraGold = 20;
			} else {
				extraGold = 30;
			}
			
			// Tells the player about the found gold
			System.out.println("In their cargo our crewmen found: "+extraGold+" gold");
			MainActivity.timeoffSet();
			
			// Standard win thereafter
			CombatSystem.playerWin(playerShip, enemyShip, player);
		} else {
			// The player failed boarding the enemy ship
			System.out.println("There fight was tense, but we failed taking over their ship");
			MainActivity.timeoffSet();
			System.out.println("While we prepare to attack the ship again, our enemy escapes\n");
			MainActivity.timeoffSet();
			MainActivity.game(playerShip, player, false);
		}
	}
	
	// The special attack of the frigate: used by the NPC or enemy
	public void enemySpecialAttack(Ship playerShip, Ship enemyShip, Player player) {
		System.out.println("\nThe enemy is preparing to board us, sir!");
		MainActivity.timeoffSet(4000);
		
		// Uses the ship method chance of succes to find out whether the ship have an advantage or not
		double chance = enemyShip.chanceOfSucces(playerShip);
		
		// Decides whether the NPC succeds or fails to board the ship
		if (Math.random() < chance) {
			System.out.println("There fight was tense, but the enemy succeded boarding us... We've lost\n");
			
			// By setting the player's ship health to 0 - the game ends
			playerShip.hullHealth = 0;
		} else {
			System.out.println("After intense fighting, the victory was ours!");
			
			// By setting the enemy's ship health to 0 - the combat ends
			enemyShip.hullHealth = 0;
			
			MainActivity.timeoffSet();
			System.out.println("Our ship have "+playerShip.hullHealth+" health left");
			MainActivity.timeoffSet();
			
			// The player wins the battle
			CombatSystem.playerWin(playerShip, enemyShip, player);
		}
	}
}
