package anotherPackage;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import anotherPackage.Entity.Player;
import anotherPackage.Entity.PubEvent;
import anotherPackage.Entity.ShopEvent;
import anotherPackage.Ships.Frigate;
import anotherPackage.Ships.Galleon;
import anotherPackage.Ships.Ship;
import anotherPackage.Ships.Sloop;

public class MainActivity {

	public static void main(String[] args) {
		// Starts the menu
		boolean gameStart = false;
		System.out.println("Welcome to the game Pirate Hunter!");
		gameStart = ProgramMenu(gameStart);

		// Begins the game
		if (gameStart == true) {

			// Method to decide what ship to give to the player
			Player player = new Player();

			// Creates the player and it's ship
			Ship playerShip = player.createPlayerShip();

			// Small tutorial:
			System.out
					.println("You attack by pressing 'z' and use special on 'x'");
			timeoffSet();

			// Starts the method: game(), where the game begins
			game(playerShip, player, true);
		}
	}

	public static boolean ProgramMenu(boolean gameStart) {
		// Initializing the values for the menu
		String continueStr;
		boolean continueProgram = false;

		// Welcome text and initializing the menu
		System.out.println("Do you want to start a NEW game, LOAD an old save or EXIT the game?");

		// Creates keyboard object out of java.util.Scanner
		java.util.Scanner keyboardInput = new java.util.Scanner(System.in);

		// Checks what you'd want to do in the program
		do {
			continueStr = keyboardInput.next();
			if (continueStr.equalsIgnoreCase("new")) {
				continueProgram = true;
				gameStart = true;
			} else if (continueStr.equalsIgnoreCase("load")) { 
				loadGame();
			} else if (continueStr.equalsIgnoreCase("exit")) {
				java.lang.System.exit(0);
			} else {
				System.out.println("Error. Use correct spelling, please.");
				continueProgram = false;
			}
		} while (continueProgram == false);
		return gameStart;
	}

	public static void game(Ship playerShip, Player player, boolean skipTalk) {
		// Creating keyboard object out of java.util.Scanner
		java.util.Scanner keyboardInput = new java.util.Scanner(System.in);

		while (playerShip.hullHealth > 0) {
			if (skipTalk != true) {
				System.out.println("\nWhere are we heading, sir?");
			}
			boolean answer = false;
			while (answer == false) {
				String answerStr = "";
				if (skipTalk != true) {
				System.out
						.println("Should we head for the open SEA and hunt more pirates? \nOr towards the nearest PORT?");
				answerStr = keyboardInput.next();
				}
				if (answerStr.equalsIgnoreCase("SEA") || skipTalk == true) {
					answer = true;
					CombatSystem.combatSystem(playerShip, player);
				} else if (answerStr.equalsIgnoreCase("PORT")) {
					answer = true;
					worldPort(playerShip,player);
				}
			}
		}
	}

	public static void worldPort(Ship playerShip, Player player) {
			java.util.Scanner keyboardInput = new java.util.Scanner(System.in);
		
			System.out.println("\nWelcome to Port Royale! \nWe have the finest shops in the Caribean and some of the best company too!");
			timeoffSet();
			boolean answer = false;
			while (answer == false) {
				System.out.println("\nWhere will you go, captain?");
				timeoffSet(3000);
				System.out.println("To the SHOPS, the DOCKS, the PUBS or vist the game OPTIONS?");
				String answerStr = keyboardInput.next();
				if (answerStr.equalsIgnoreCase("SHOPS")) {
					answer = true;
					playerShip = ShopEvent.event(playerShip, player);
					answer = false;
				} else if (answerStr.equalsIgnoreCase("DOCKS")) {
					answer = true;
					boolean dockAns = false;
					while (dockAns == false) {
						java.util.Scanner dockInput = new java.util.Scanner(System.in);
						System.out.println("Should we head out to hunt pirates? YES / NO");
						String dockStr = dockInput.next();
						if (dockStr.equalsIgnoreCase("YES")) {
							dockAns = true;
							game(playerShip, player, true);
						} else if (dockStr.equalsIgnoreCase("NO")) {
							dockAns = true;
							answer = false;
						} 
					}
					
				} else if (answerStr.equalsIgnoreCase("PUBS")) {
					answer = true;
					PubEvent.event();
					answer = false;
				} else if (answerStr.equalsIgnoreCase("OPTIONS")) {
					boolean optionsAns = false;
					while (optionsAns == false) {
						System.out.println("What do you want to do?");
						timeoffSet(3000);
						System.out.println("EXIT the game, SAVE the game or RETURN to the game?");
						String optionsStr = keyboardInput.next();
						if (optionsStr.equalsIgnoreCase("EXIT")) {
							optionsAns = true;
							answer = true;
							main(null);
						} else if (optionsStr.equalsIgnoreCase("SAVE")) { 
							saveGame(playerShip, player);
						} else if (optionsStr.equalsIgnoreCase("RETURN")) {
							optionsAns = true;
						}
					}
				}
			}
	}
	
	public static void saveGame (Ship pShip, Player p) {
		try {	// Catch errors in I/O if necessary  
			
			// Open a file to write to, called savegame.sav 
			FileOutputStream saveFile = new FileOutputStream("savegame.sav"); 
			
			// Creates an ObjectOutputStream to put objects into save file
			ObjectOutputStream save = new ObjectOutputStream(saveFile);
			
			// The ship starts being saved
			save.writeObject(pShip.shipID);
			save.writeObject(pShip.armor);
			save.writeObject(pShip.hullHealth);
			save.writeObject(pShip.maxHealth);
			save.writeObject(pShip.nrCannons);
			save.writeObject(pShip.nrMasts);
			save.writeObject(pShip.xpGain);
			save.writeObject(pShip.goldGain);
			save.writeObject(pShip.maxArmor);
			
			// The player is saved
			save.writeObject(p.currentShip);
			save.writeObject(p.gold);
			save.writeObject(p.level);
			save.writeObject(p.pirateKills);
			save.writeObject(p.xp);
			save.writeObject(p.totalXp);
			
			// Closes the both output streams
			save.close();
			
			// Tells the user that the save is complete and the game can begin 
			System.out.println("The game has been succesfully saved.");
			
		} catch (Exception e) {
			e.printStackTrace();	// If an error occurs, print the info about it
		}
	}
	
	public static void loadGame () {
		try {
			// Opening the file
			FileInputStream saveFile = new FileInputStream("savegame.sav");
			
			// Open an object stream
			ObjectInputStream load = new ObjectInputStream(saveFile);
			
			// Creates the two objects that'll be transformed into the player's former objects
			Ship pShip = new Ship(0, 0, 0, 0, 0, 0, null);
			Player p = new Player(0, 0, 0, 0, 0);
			
			// Finds out what kind of ship the players has and creates it as so
			pShip.shipID = 		(int) load.readObject();
			
			int armor = (int) load.readObject();
			
			if (pShip.shipID == 1) {
				pShip = (Sloop) new Sloop(armor);
			} else if (pShip.shipID == 2) {
				pShip = (Frigate) new Frigate();
			} else if (pShip.shipID == 3) {
				pShip = (Galleon) new Galleon();
			}
			
			// Loads the rest of the ships data and saves it in the new object
			pShip.hullHealth = 	(int) load.readObject();
			pShip.maxHealth = 	(int) load.readObject();
			pShip.nrCannons = 	(int) load.readObject();
			pShip.nrMasts = 		(int) load.readObject();
			pShip.xpGain = 		(int) load.readObject();
			pShip.goldGain = 		(int) load.readObject();
			pShip.armor = 		armor;
			pShip.maxArmor = 	(int) load.readObject();
			
			// Loads the player and saves the correct values at the right places
			p.currentShip = (int) load.readObject();
			p.gold = (int) load.readObject();
			p.level = (int) load.readObject();
			p.pirateKills = (int) load.readObject();
			p.xp = (int) load.readObject();
			p.totalXp = (int) load.readObject();
			
			// Closes both input streams
			load.close();
			
			// Prints out a successful loading message
			System.out.println("The save file (with your "+pShip.nameTag+") have been succesfully loaded...");
			timeoffSet(3000);
			
			// Return to the game
			game(pShip, p, false);
			
		} catch (Exception e) {
			e.printStackTrace();	// If an error occurs, print the info about it
		}
	}
	
	public static void timeoffSet() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void timeoffSet(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
