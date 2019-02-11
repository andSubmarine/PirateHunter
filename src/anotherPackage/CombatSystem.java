package anotherPackage;

import anotherPackage.Entity.Player;
import anotherPackage.Ships.Frigate;
import anotherPackage.Ships.Galleon;
import anotherPackage.Ships.Ship;
import anotherPackage.Ships.Sloop;

public class CombatSystem {

	public static void combatSystem(Ship playerShip, Player player) {
		// Creates an enemy to fight with
		Ship enemyShip = createEnemyShip(player);

		// Combat loop
		combatLoop(playerShip, enemyShip, player);
	}

	public static Ship createEnemyShip(Player p) {
		Ship enemyShip = new Ship(0, 0, 0, 0, 0, 0, null);
		double randomShip = Math.random();
		double chanceFrigate = 0, chanceGalleon = 0, chanceSloop = 0;

		// Progression system (chances ranges from 0 to 1)
		if (p.level <= 1) {
			// Higher probability to meet sloops
			chanceSloop = 0.5;
			chanceFrigate = 0.90;
			chanceGalleon = 1;
		} else if (p.level <= 4) {
			// Lower probability to meet sloops and higher probability to engage
			// frigates
			chanceSloop = 0.3;
			chanceFrigate = 0.8;
			chanceGalleon = 1;
		} else if (p.level >= 5) {
			// Lower probability to meet frigates and higher probability to
			// engage galleons
			chanceSloop = 0.15;
			chanceFrigate = 0.6;
			chanceGalleon = 1;
		}

		// Decides what kind of ship the enemy fights with
		if (randomShip <= chanceSloop) {
			Sloop enemySloop = new Sloop(0);
			enemyShip = enemySloop;

		} else if (randomShip <= chanceFrigate && randomShip >= chanceSloop) {
			Frigate enemyFrigate = new Frigate();
			enemyShip = enemyFrigate;

		} else if (randomShip <= chanceGalleon) {
			Galleon enemyGalleon = new Galleon();
			enemyShip = enemyGalleon;

		}

		// Tells the player what ship the computer has chosen to fight against
		// the player
		System.out.println("\nA pirate " + enemyShip.nameTag
				+ " has appeared in the distance! It wants to fight us!");
		MainActivity.timeoffSet();

		return enemyShip;
	}

	public static void combatLoop(Ship playerShip, Ship enemyShip, Player player) {

		// Computer decides who attacks first
		int chosenShipAttack = (int) Math.floor(Math.random() * 2 + 1);
		int turn = 0;

		while ((playerShip.hullHealth > 0 || enemyShip.hullHealth > 0)) {

			if (chosenShipAttack == 1) { // If the player attacks then:

				// Player decides how to attack
				String attackChoice = attackChoice();

				if (attackChoice.equalsIgnoreCase("z")) {
					// Player uses normal attack
					playerDamage(playerShip, enemyShip, player);

				} else if (attackChoice.equalsIgnoreCase("x")) {

					// Player uses special attack
					if (playerShip.nameTag.equals("Sloop")) {
						((Sloop) playerShip).specialAttack(playerShip, player);

					} else if (playerShip.nameTag.equals("Frigate")) {
						((Frigate) playerShip).specialAttack(playerShip,
								enemyShip, player);

					} else if (playerShip.nameTag.equals("Galleon")) {
						((Galleon) playerShip).specialAttack(playerShip,
								enemyShip, player);
					}
				}
				turn++; // Keeps track of number of turns played
				chosenShipAttack = 2; // Next turn the NPC will attack
			} else if (chosenShipAttack == 2) { // If the NPC attacks then:
				if (Math.random() < 0.25 && turn > 2) {
					// The enemy tries to use special attack
					if (enemyShip.nameTag.equals("Sloop")) {
						((Sloop) enemyShip).enemySpecialAttack(playerShip,
								player);

					} else if (enemyShip.nameTag.equals("Frigate")) {
						((Frigate) enemyShip).enemySpecialAttack(playerShip,
								enemyShip, player);

					} else if (enemyShip.nameTag.equals("Galleon")) {
						((Galleon) enemyShip).enemySpecialAttack(playerShip,
								player);
					}
				} else {
					// The enemy uses the standard attack
					enemyDamage(playerShip, enemyShip, player);
				}
				turn++; // Keeps track of number of turns played
				chosenShipAttack = 1; // Next turn the player will attack
			}

			// Checks whether victory condition is true or not
			if (playerShip.hullHealth <= 0) {
				// If the player has lost it's ship
				System.out
						.println("Oh no! The enemy destroyed your ship! You've lost!");
				MainActivity.timeoffSet();

				System.out.println("They had " + enemyShip.hullHealth
						+ " health left");
				System.out.println("You've collected " + player.totalXp
						+ " xp and was in level " + player.level + "\n");
				MainActivity.timeoffSet();

				MainActivity.main(null);
			} else if (enemyShip.hullHealth <= 0) {
				// If the enemy has lost it's ship
				System.out
						.println("You have sunk the enemy ship! You currently have "
								+ playerShip.hullHealth + " health left");
				playerWin(playerShip, enemyShip, player);
			}
		}
	}

	public static void playerWin(Ship playerShip, Ship enemyShip, Player player) {
		// Finds out how much the player has gained from killing the enemy ship
		int gainXp = enemyShip.xpGain;
		int gainGold = enemyShip.goldGain;

		// Player gains gold and xp - also checks whether the player has gained
		// a level
		player.xpGain(gainXp);
		player.checkLevel();
		player.goldGain(gainGold);
		player.pirateKill();

		// Tells the player about it's gain from killing the enemy ship
		MainActivity.timeoffSet();
		System.out.println("You've gained " + player.xp + " xp");
		MainActivity.timeoffSet();
		System.out.println("You've gained " + gainGold + " gold and now have "
				+ player.gold + " gold");
		MainActivity.timeoffSet();

		// Return to the game
		MainActivity.game(playerShip, player, false);
	}

	public static String attackChoice() {
		// This method returns the attack of the player back to the combat
		// system
		System.out.print("Attack: ");
		java.util.Scanner input = new java.util.Scanner(System.in);
		boolean answer = false;
		String playerInput = "";

		// While loop to make sure that what the player inputs can be used in
		// the game
		while (answer == false) {
			playerInput = input.next();

			// Checks if the player have decided how to attack
			if (playerInput.equalsIgnoreCase("z")
					|| playerInput.equalsIgnoreCase("x")) {
				answer = true;
			} else {
				System.out
						.println("Press z for a normal attack and x for a special attack");
			}
		}
		return playerInput;
	}

	public static void playerDamage(Ship playerShip, Ship enemyShip,
			Player player) {
		// The method is used to find out the damage the player does on the
		// enemy ship and then does damage to the enemy ship
		int playerDamage = playerShip.damageDealt();
		int armorDamage = 0;

		// Finds out how much damage the armor takes
		if (enemyShip.armor > 0) {
			armorDamage = playerDamage / 2;
			playerShip.armor -= armorDamage;
			System.out.println("Their ship's armour has taken " + armorDamage
					+ " damage");
			MainActivity.timeoffSet();
		}

		// Saves the final damage to the enemy's ship
		int finalDamage = (int) (playerDamage - armorDamage);

		// Tells the player how much damage the ship has taken
		if (armorDamage > 0) { // If the ship does have armor
			System.out.println("The ship would have taken " + playerDamage
					+ " damage, but now only took " + finalDamage+"\n");
			MainActivity.timeoffSet();
		}

		if (playerDamage > 0 && armorDamage == 0) { // If the ship does not have
													// armor
			enemyShip.hullHealth -= playerDamage;

			System.out.println("Your ship has dealt " + playerDamage
					+ " damage to their ship\n");
		}

		MainActivity.timeoffSet();
	}

	public static void enemyDamage(Ship playerShip, Ship enemyShip,
			Player player) {
		// The method is used to find out how much damage the enemy does on the
		// player's ship and if the player has armor to protect it from the
		// attack
		int enemyDamage = enemyShip.damageDealt();
		int armorDamage = 0;

		// Finds out how much damage the armor takes
		if (playerShip.armor > 0) {
			armorDamage = enemyDamage / 2;
			playerShip.armor -= armorDamage;
			System.out.println("Your ship's armour has taken " + armorDamage
					+ " damage\n");
			MainActivity.timeoffSet();

		}

		// Saves the final damage to the player's ship
		int finalDamage = (int) (enemyDamage - armorDamage);

		// Tells the player how much damage the ship has taken
		if (armorDamage > 0) { // If the ship does have armor
			System.out.println("The ship would have taken " + enemyDamage
					+ " damage, but now only took " + finalDamage);
			MainActivity.timeoffSet();
		}

		if (enemyDamage > 0 && armorDamage == 0) { // If the ship does not have
													// armor
			playerShip.hullHealth -= enemyDamage;

			System.out.println("Their ship has dealt " + enemyDamage
					+ " damage to your ship\n");
		} else if (enemyDamage <= 0) {
			System.out.println("Your ship did not take any damage\n");
		}

		MainActivity.timeoffSet();
	}
}
