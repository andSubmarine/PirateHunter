package anotherPackage.Entity;

import anotherPackage.MainActivity;
import anotherPackage.Ships.Ship;

public class ShopEvent {

	// Main event of the shop - the player decides what to do in the shop
	public static Ship event(Ship playerShip, Player player) {
		System.out
				.println("Welcome to the shop! Here you can buy upgrades and repair your ship!");
		MainActivity.timeoffSet();
		java.util.Scanner keyboardInput = new java.util.Scanner(System.in);
		
		// Finds out what the player wants to do and which methods to go to
		boolean ansVal = false;
		while (ansVal == false) {
			System.out.println("UPGRADE your ship, REPAIR it, see STATS or EXIT?");
			String ansStr = keyboardInput.next();
			if (ansStr.equalsIgnoreCase("STATS")) {
				
				// Prints the stats of the ship out to the console
				playerShip.tellProperties();
			} else if (ansStr.equalsIgnoreCase("UPGRADE")) {
				
				// Goes to the part of the shop, where the player can upgrade it's ship
				playerShip = upgradeMethod(playerShip, player);
			} else if (ansStr.equalsIgnoreCase("REPAIR")) {
				
				// Goes to the part of the shop where the player can repair it's ship
				playerShip = repairMethod(playerShip, player);
			} else if (ansStr.equalsIgnoreCase("EXIT")) {
				
				// Exits back to the world port
				MainActivity.timeoffSet();
				ansVal = true;
			}
		}
		return playerShip;
	}

	// The part of the shop, where the player can upgrade parts of their ship
	public static Ship upgradeMethod (Ship ship, Player p) {
		java.util.Scanner upgradeScanner = new java.util.Scanner(System.in);
		String answer;
		
		// Finds out whether the player wants to upgrade
		boolean exit = false;
		do {
			System.out.println("Do you want to buy ARMOR, more CANNONS or EXIT?");
			answer = upgradeScanner.next(); 
			
			// Does what the player wanted to do
			if (answer.equalsIgnoreCase("ARMOR")) {
				upgradeArmor (ship, p);
				exit = true;
			} else if (answer.equalsIgnoreCase("CANNONS")) {
				buyCannons (ship, p);
				exit = true;
			} else if (answer.equalsIgnoreCase("EXIT")) {
				exit = true;
			} 
		} while (exit == false);
		return ship;
	}
	
	// Upgrade ship: buying more cannons for the ship
	public static Ship buyCannons (Ship ship, Player p) {
		java.util.Scanner buyScanner = new java.util.Scanner(System.in);
		
		// Finds out what the player wants to do and checks if it can
		String answer;
		boolean exit = false;
		do {
			System.out.println("Cannon: 10 cannons for 50 gold");
			System.out.println("Current balance: "+p.gold);
			System.out.println("Ship cannons: "+ship.nrCannons);
			MainActivity.timeoffSet();
			System.out.println("Press BUY to buy 30 points of repair or EXIT to get back to the shop");
			answer = buyScanner.next();
			
			// The consequences of either buying or exiting
			if (answer.equalsIgnoreCase("buy")){
				// Finds out if the player has enough gold to buy cannons
				if (p.gold >= 50) {
					ship.nrCannons += 10;
					p.goldLost(50);
				} else if (p.gold < 50) {
					System.out.println("You do not have enough money to repair. Return when you have some");
					exit = true;
				} 
			} else if (answer.equalsIgnoreCase("exit")) {
				exit = true;
			}
		} while (exit == false);
		return ship;
	}
	
	// Upgrade ship: buying armor for the ship
	public static Ship upgradeArmor (Ship ship, Player p) {
		java.util.Scanner scanner = new java.util.Scanner(System.in);
		
		// Finds out what the player decides to do
		String answer ="";
		boolean exit = false;
		do {
			System.out.println("Upgrade: 10 points of armour for 5 gold");
			System.out.println("Current balance: "+p.gold);
			System.out.println("Current armour: "+ship.armor);
			MainActivity.timeoffSet();
			System.out.println("Press BUY to buy 10 points of armour or EXIT to get back to the shop");
			answer = scanner.next();
			
			// Checks if it is possible to buy armor and if true the player can buy
			if (ship.tellIfMaxArmorIsReached() == false && answer.equalsIgnoreCase("buy")){
				
				// Checks the player's amount of gold and finds out if it has enough gold to pay with
				if (p.gold >= 5) {
					ship.upgradeArmor(p);
				} else if (p.gold < 5) {
					System.out.println("You do not have enough money to repair. Return when you have some");
					exit = true;
				} 
			} else {
				System.out.println("\nMaximum armor has been reached\n");
				MainActivity.timeoffSet();
			}
			if (answer.equalsIgnoreCase("exit")) {
				exit = true;
			}
		} while (exit == false);
		return ship;
	}
	
	// Another part of the shop, where the player can repair it's damaged ship
	public static Ship repairMethod(Ship ship, Player p){
			java.util.Scanner repairScanner = new java.util.Scanner(System.in);
			
			// Finds out whether the player wants to repair the ship or not
			String answer;
			boolean exit = false;
			do {
				System.out.println("Repair: 30 points for 5 gold");
				System.out.println("Current balance: "+p.gold);
				System.out.println("Ship health: "+ship.hullHealth);
				MainActivity.timeoffSet();
				System.out.println("Press BUY to buy 30 points of repair or EXIT to get back to the shop");
				answer = repairScanner.next();
				
				// Checks that the ship has been damaged and then follows the player's command
				if (ship.tellIfMaxHealthIsReached() == false && answer.equalsIgnoreCase("buy")){
					
					// Checks that the player has enough gold to pay for the repair
					if (p.gold >= 5) {
						ship.repairShip(p);
					} else if (p.gold < 5) {
						System.out.println("You do not have enough money to repair. Return when you have some");
						exit = true;
					} 
				} else {
					System.out.println("Maximum health has been reached");
				}
				if (answer.equalsIgnoreCase("exit")) {
					exit = true;
				}
			} while (exit == false);
		return ship;
	}
}
