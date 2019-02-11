package anotherPackage.Entity;

import anotherPackage.MainActivity;

public class PubEvent {
	
	// Event inside the port - happens when you enter the pub
	
	public static void event() {
		java.util.Scanner keyboardInput = new java.util.Scanner(System.in);
		
		// Random chance of what happens in the pub
		if (Math.floor(Math.random()*2)+1 < 2) {
			// You come into a fight in the pub
			pubFight(keyboardInput);
		} else {
			// Nothing happens and you just have a great time
			greatNight();
		}
	}
	
	// Method for the fight inside the bar
	public static void pubFight(java.util.Scanner keyboardInput) {
		
		// Strings used
		String str1 = "\nYou walk into the pub with some of your crew and sits down at a table in the corner.";
		String str2 = "Your first mate shouts to the bartender: 'Bring us your finest beer and rum, good sir!'";
		String str3 = "'Cause we have been at sea to long hunting pirates and needs a good drink!'";
		String str4 = "Some of the men from the table next to you look at your table and one of them says:";
		String str5 = "'Pirate hunters you say? I've heard of you kind before. Sea rats without a prober business.'";
		String str6 = "One of the other men then says: 'Slaughering traders and pirates alike - you aren't better than them!'";
		String str7 = "\nHow will you react in response to these men?";
		String str8_1 = "AGGRESIVE";
		String str8_2 = "FRIENDLY";
		String str8_3 = "EVASIVE";
		String str8 = "Choose to either be " + str8_1 + ", " + str8_2 + " or "
				+ str8_3;
		
		// Prints the first strings out in the console
		String[] fightList = { str1, str2, str3, str4, str5, str6, str7, str8 };
		printList(fightList);

		// Constructs the remaining strings and initiazes them 
		String str9 = null,str10 = null,str11 = null,str12 = null ,str13 = null,str14 = null;
		
		// While loop to find out how the player reacts
		boolean answer = false;
		while (answer == false) {
			String ansStr = keyboardInput.next();
			
			// Does as the player has chosen to react
			if (ansStr.equalsIgnoreCase(str8_1)) { 
				// Player chose AGGRESIVE
				str9 = "You raise your voice and says to the men:";
				str10 = "'You wanna fight you bastards?'";
				str11 = "The men gets off their chairs and so does your crew";
				str12 = "Within minutes every man and woman in the pub was fighting";
				str13 = "Suddenly the guards appear and halts the fight";
				str14 = "You're charged for starting a fight and spends the night in jail";
				
				// Prints the last strings out to the console
				String[] list = {str9,str10,str11,str12,str13,str14};
				printList(list);
				answer = true;
			} else if (ansStr.equalsIgnoreCase(str8_2)) { 
				// Player chose FRIENDLY
				str9 = "You look at the men at the other table and says:";
				str10 = "'We're as good as any old men! Here, let me buy you some drinks!'";
				str11 = "You laugh and asks the bartender to come with some drinks to the men ";
				str12 = "They refuse to take them and walks out of the pub";
				str13 = "The bartender gives your drinks for free and says:";
				str14 = "'I'm just thankful that my pub wasn't wrecked in another fight!'";
				
				// Prints the last strings out to the console
				String[] list = {str9,str10,str11,str12,str13,str14};
				printList(list);
				answer = true;
			} else if (ansStr.equalsIgnoreCase(str8_3)) { 
				// Player chose EVASIVE
				str9 = "You take a deep look down your drink and says:";
				str10 = "'We aren't looking for any trouble, mate'";
				str11 = "The tallest of the men get's off his chair and says:";
				str12 = "'But I do! I've lost my brother because of the kind like you!'";
				str13 = "You and your crew stands up - ready to leave, when the guards show up";
				str14 = "The men are arrested and your crew and you drinks on";
				
				// Prints the last strings out to the console
				String[] list = {str9,str10,str11,str12,str13,str14};
				printList(list);
				answer = true;
			} else {
				System.out.println(str8);
			}
		}
	}
	
	// 
	public static void greatNight() {
		
		// All the strings that descripe the event of greatNight
		String str1 = "\nYou walk into the pub with some of your crew and sits down at a table in the corner.";
		String str2 = "Your first mate shouts to the bartender: 'Bring us your finest beer and rum, good sir!'";
		String str3 = "'Cause we have been at sea to long hunting pirates and needs a good drink!'";
		String str4 = "A couble of women notice you and your men and walks over to your table with some beer.";
		String str5 = "'Aren't you a bit cocky, noble gentleman?' One of them asks";
		String str6 = "'I'm always cocky when I'm finally in port' responds your first mate";
		String str7 = "'You're always cocky' does you say at receive a kick in return";
		String str8 = "'Let's see about that' they say and guides you and your first mate into their private quaters";
		String str9 = "After you're done - the wowan, which you've followed, says:";
		String str10 = "'That'll be 5 gold coins or I'll tell the guards that you raped me!'";
		String str11 = "You pay and leave the pub without knowing where your crew went";
		
		// Prints all the strings out to the console
		String[] greatList = {str1,str2,str3,str4,str5,str6,str7,str8,str9,str10,str11};
		printList(greatList);
	}

	// Method used to print out strings from a array (using a for loop)
	public static void printList (String[] list) {
		for (int i = 0; i < list.length; i++) {
			System.out.println(list[i]);
			MainActivity.timeoffSet(3000);
		}
	}
	
}
