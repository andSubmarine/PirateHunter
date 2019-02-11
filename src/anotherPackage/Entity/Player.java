package anotherPackage.Entity;

import anotherPackage.MainActivity;
import anotherPackage.Ships.Frigate;
import anotherPackage.Ships.Galleon;
import anotherPackage.Ships.Ship;
import anotherPackage.Ships.Sloop;

public class Player {

	// Properties of the player
	public int currentShip;
	public int xp = 0;
	public int totalXp = 0;
	public int gold = 0;
	public int pirateKills = 0;
	public int level = 0;

	// Leveling up system 
	public void checkLevel() {
		if (this.xp > 25 && this.level < 1) {
			this.xp -= 25;
			this.level++;
			System.out.println("You've gained a level! You're now level "+level);
		} else if(this.xp > 50 && this.level < 2) {
			this.xp -= 50;
			this.level++;
			System.out.println("You've gained a level! You're now level "+level);
		} else if(this.xp > 100 && this.level < 3) {
			this.xp -= 100;
			this.level++;
			System.out.println("You've gained a level! You're now level "+level);
		} else if(this.xp > 150 && this.level < 4) {
			this.xp -= 150;
			this.level++;
			System.out.println("You've gained a level! You're now level "+level);
		} else if(this.xp > 200 && this.level < 5) {
			this.xp -= 200;
			this.level++;
			System.out.println("You've gained a level! You're now level "+level);
		} 
	}
	
// Constructors
	
	// The basic constructor for the player object - used when creating a new game
	public Player() {
		startShip();
	}
	
	// Another constructor for the player - is used when loading a saved game
	public Player(int currentShip, int xp, int totalXp, int pirateKills, int level) {
		this.currentShip = currentShip;
		this.xp = xp;
		this.totalXp = totalXp;
		this.pirateKills = pirateKills;
		this.level = level;
	}

// Methods for the player
	
	// Method that finds out what ship the player starts with
	public void startShip() {
		System.out.println("Please choose your favorite ship:");
		System.out
				.println("The current selection is: Sloop (hard), Frigate (medium) and Galleon (easy)");
		java.util.Scanner keyboardScanner = new java.util.Scanner(System.in);
		String selectShip = keyboardScanner.next();

		// Finds out what the player choose
		if (selectShip.equalsIgnoreCase("s")) {
			this.currentShip = 1;
		} else if (selectShip.equalsIgnoreCase("sloop")) {
			this.currentShip = 1;
		} else if (selectShip.equalsIgnoreCase("f")) {
			this.currentShip = 2;
		} else if (selectShip.equalsIgnoreCase("frigate")) {
			this.currentShip = 2;
		} else if (selectShip.equalsIgnoreCase("g")) {
			this.currentShip = 3;
		} else if (selectShip.equalsIgnoreCase("galleon")) {
			this.currentShip = 3;
		} else {
			startShip();
		}
	}

	// Method used to actually create the player's ship and starts the story of the game
	public Ship createPlayerShip() {
		Ship playerShip = null;
		// Introducing the story
		System.out
				.println("You are a pirate hunter and your job are to kill all enemy pirates!");
		MainActivity.timeoffSet();

		// Constructs the ship for the player
		playerShip = shipConstruct(playerShip);
		
		System.out.println("You've chosen a " + playerShip.nameTag
				+ " to hunt 'em down!");
		MainActivity.timeoffSet();

		// Returns the ship back to the main activity
		return playerShip;
	}
	
	// Method that sets the player's constructs a ship for the player
	public Ship shipConstruct (Ship playerShip) {
		
		// If statement, which decides the players ship
		if (this.currentShip == Sloop.shipID) {
			Sloop playerShip1 = new Sloop(50);
			playerShip = playerShip1;
		} else if (this.currentShip == Frigate.shipID) {
			Frigate playerShip2 = new Frigate(); 
			playerShip = playerShip2;
		} else {
			Galleon playerShip3 = new Galleon();
			playerShip = playerShip3;
		}
		return playerShip;
	}
	
	// Method used to increase the number of pirate kills
	public void pirateKill() {
		pirateKills++;
	}

	// Method used to gain xp to the player, works along with the levelling system
	public void xpGain(int gainXP) {
		this.xp += gainXP;
		this.totalXp += gainXP;
	}
	
	// Method to gain gold for the player to use in the shops
	public void goldGain(int gainGold) {
		this.gold += gainGold;
	}
	
	// Method to remove gold from the player, when it buys stuff or losses them
	public void goldLost(int lostGold) {
		this.gold -= lostGold;
	}
	
}
