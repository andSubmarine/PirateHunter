package anotherPackage.Ships;

import anotherPackage.MainActivity;
import anotherPackage.Entity.Player;

public class Ship {

	// This class is used as a super class for all the ships in the game,
	// which means that all the ships can use these methods and has these values

	// Properties of Ship
	public int maxHealth; // The Maximum amount of health
	public int hullHealth; // The Health of the Ship
	public int armor; // The armor of the ship; Protects it from damage
	public int maxArmor; // The maximum amount of armor;
	public int nrCannons; // The amount of Cannons on board
	public int nrMasts; // The amount of Masts on board
	public int shipID; // ID of the current ship
	public int xpGain; // The amount of xp earned by killing the ship
	public int goldGain; // The amount of gold earned by killing the ship
	public String nameTag; // The name of the ship

	// Default properties
	public final int cannonStrength = 2; // Default strength of the ship's
											// cannons
	public final int mastSpeed = 10; // Default speed from the ship's masts

	// Constructor of Ship
	public Ship(int Health, int Cannons, int Masts, int shipID, int xpGain,
			int goldGain, String nameTag) {
		this.hullHealth = Health;
		this.maxHealth = Health;
		this.nrCannons = Cannons;
		this.nrMasts = Masts;
		this.shipID = shipID;
		this.xpGain = xpGain;
		this.goldGain = goldGain;
		this.nameTag = nameTag;
		this.armor = 0;
		this.maxArmor = 100;
	}

	// Methods of Ship
	public void tellProperties() {
		// Tells the player about the ship and what it contains
		System.out.println("Your ship's hull health is at " + hullHealth
				+ " / " + maxHealth);
		if (armor > 0) {
			System.out.println("Your ship have " + armor + " armour out of "
					+ maxArmor);
		} else {
			System.out.println("Your ship have no armour");
		}
		System.out.println("It has " + nrCannons + " cannons and " + nrMasts
				+ " masts");
		MainActivity.timeoffSet(5000);
	}

	public void repairShip(Player p) {
		// Repairs the ship and removes gold from the player for the repair
		int formerHealth = hullHealth;
		int exception = maxHealth - hullHealth;
		if (exception < 30) {
			hullHealth = maxHealth;
		} else {
			hullHealth = formerHealth + 30;
		}
		p.goldLost(5);
	}

	public boolean tellIfMaxHealthIsReached() {
		// Finds out if the maximum amount of health has been reached
		boolean answer;
		if (maxHealth == hullHealth) {
			answer = true;
		} else {
			answer = false;
		}
		return answer;
	}

	public void upgradeArmor(Player p) {
		// Upgrades the armor of the ship and removes gold from the player as
		// payment
		int formerArmour = armor;
		int exception = maxArmor - armor;
		if (exception < 10) {
			armor = maxArmor;
		} else {
			armor = formerArmour + 10;
		}
		p.goldLost(5);
	}

	public boolean tellIfMaxArmorIsReached() {
		// Finds out if the maximum amount of armor has been reached
		boolean answer;
		if (maxArmor == armor) {
			answer = true;
		} else {
			answer = false;
		}
		return answer;
	}

	public int damageDealt() {
		// Method that decides how much a ship does of damage against another
		// ship
		int damageDealt;

		// GreatChance is used to determine how good a shot it was
		double greatChance = Math.floor(Math.random() * 10) * 0.5;

		// Currently there are a 100% chance of hitting the enemy ship
		if (greatChance == 0.0) {
			greatChance = 1.0;
		}

		// This decides exactly how much damage the ship does to the other ship
		damageDealt = (int) (greatChance * (nrCannons / 3) * cannonStrength);

		return damageDealt;
	}

	public double chanceOfSucces(Ship ship2) {
		// Method used to determine whether this ship have an advantage over the
		// other ship
		double chance = 0;
		if (shipID < ship2.shipID) {
			chance = 0.15;
		} else if (shipID == ship2.shipID) {
			chance = 0.30;
		} else if (shipID > ship2.shipID) {
			chance = 0.45;
		}
		return chance;
	}
}
