import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner scan = new Scanner(System.in);
		String choice = "BLANK";
		while (choice != "EXIT") {
			System.out.printf(
					"Select function: COACH, DRAFT, EXIT, GENSILVER, GENGOLD, GROWTH, INJURY, NCAA, NHL, REGRESSION, RETIREMENT\n");
			choice = scan.nextLine();
			
			if (choice.equals("COACH") == true) {
				Coach temp = new Coach();
				temp.print();
			}

			if (choice.equals("DRAFT") == true) {
				for (int x = 1; x <= 100; x++) {
					Player temp = new Player(x);
					temp.print();
				}
			}

			if (choice.equals("GENSILVER") == true) {
				Growth temp = new Growth();
				String ptchoice = "EMPTY";
				System.out.printf("Enter player type: ");
				ptchoice = scan.nextLine();
				String res = temp.generateSilverFactor(ptchoice);
				System.out.printf("Silver X-Factor generated: %s\n", res);
			}
			
			if (choice.equals("NCAA") == true) {
				Random rand = new Random();
				String[] ncaaTeams = { "Boston College", "Boston University", "Denver", "Maine",
						"Michigan", "Michigan State", "Minnesota", "Minnesota State",
						"Minnesota-Duluth", "North Dakota", "Northeastern", "Notre Dame", "Ohio State",
						"Penn State", "Providence", "Quinnipiac", "St. Cloud State", "UMass",
						"Western Michigan", "Wisconsin" };
				System.out.printf("%s\n",  ncaaTeams[rand.nextInt(ncaaTeams.length)]);
			}
			
			if (choice.equals("NHL") == true) {
				Random rand = new Random();
				String[] nhlTeams = {"Anaheim Ducks", "Arizona Coyotes", "Boston Bruins", "Buffalo Sabres", "Calgary Flames", "Carolina Hurricanes", "Chicago Blackhawks", "Colorado Avalanche", "Columbus Blue Jackets", "Dallas Stars", "Detroit Red Wings", "Edmonton Oilers", "Florida Panthers", "Los Angeles Kings", "Minnesota Wild", "Montreal Canadiens", "Nashville Predators", "New Jersey Devils", "New York Islanders", "New York Rangers", "Ottawa Senators", "Philadelphia Flyers", "Pittsburgh Penguins", "San Jose Sharks", "Seattle Kraken", "St. Louis Blues", "Tampa Bay Lightning", "Toronto Maple Leafs", "Vancouver Canucks", "Vegas Golden Knights", "Washington Capitals", "Winnipeg Jets"};
				System.out.printf("%s\n",  nhlTeams[rand.nextInt(nhlTeams.length)]);
			}

			if (choice.equals("GENGOLD") == true) {
				Growth temp = new Growth();
				String ptchoice = "EMPTY";
				System.out.printf("Enter player type: ");
				ptchoice = scan.nextLine();
				String res = temp.generateGoldFactor(ptchoice);
				System.out.printf("Gold X-Factor generated: %s\n", res);
			}

			if (choice.equals("INJURY") == true) {
				Injury temp = new Injury();
				temp.injure();
			}

			if (choice.equals("EXIT") == true) {
				System.exit(0);
			}

			if (choice.equals("GROWTH") == true) {
				Growth temp = new Growth();
				temp.grow();
			}

			if (choice.equals("RETIREMENT") == true) {
				Retirement temp = new Retirement();
				temp.retire();
			}
		}

		scan.close();	
	}
}

class Injury {

	public void injure() throws FileNotFoundException {
		Random rand = new Random();
		File input = new File("input.txt");
		Scanner sc = new Scanner(input);
		int time = sc.nextInt();

		String names[] = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16",
				"17", "18", "19" };
		int durabilities[] = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19 };

		for (int x = 0; x < 20; x++) {
			String firstPlaceholder = sc.next();
			String n = sc.next();
			int d = sc.nextInt();
			names[x] = n;
			durabilities[x] = d;
		}

		int injuries = 0;
		if (time == 0) {
			int totalInjuriesChance = rand.nextInt(100) + 1;
			if (totalInjuriesChance <= 15)
				injuries = 1;
		} else if (time == 1) {
			int totalInjuriesChance = rand.nextInt(100) + 1;
			if (totalInjuriesChance <= 20)
				injuries = 1;
			else if (20 < totalInjuriesChance && totalInjuriesChance <= 30)
				injuries = 2;
			else if (30 < totalInjuriesChance && totalInjuriesChance <= 35)
				injuries = 3;
		} else if (time > 1) {
			System.out.printf("Time can only be 0 or 1!!!\n");
			System.exit(0);
		}

		if (injuries == 0) {
			System.out.printf("No injuries.\n");
			System.exit(0);
		}

		int injuryCounter = 0;
		while (injuryCounter != injuries) {
			int randomPlayer = rand.nextInt(20);
			int injuryLevelPercentage = rand.nextInt(10000) + 1;
			int injuryLevel = 0;
			if (durabilities[randomPlayer] <= 1 || durabilities[randomPlayer] == 2) {
				if (injuryLevelPercentage <= 4000)
					injuryLevel = 1;
				else if(4001 <= injuryLevelPercentage && injuryLevelPercentage <= 7500)
					injuryLevel = 2;
				else if(7501 <= injuryLevelPercentage && injuryLevelPercentage <= 9000)
					injuryLevel = 3;
				else if(9001 <= injuryLevelPercentage && injuryLevelPercentage <= 9990)
					injuryLevel = 4;
				else if(9991 <= injuryLevelPercentage)
					injuryLevel = 5;
				else
					injuryLevel = 1000;
				
			} 
			else if (durabilities[randomPlayer] > 2) {
				if (injuryLevelPercentage <= 5000)
					injuryLevel = 1;
				else if(5001 <= injuryLevelPercentage && injuryLevelPercentage <= 8000)
					injuryLevel = 2;
				else if(8001 <= injuryLevelPercentage && injuryLevelPercentage <= 9250)
					injuryLevel = 3;
				else if(9251 <= injuryLevelPercentage && injuryLevelPercentage <= 9999)
					injuryLevel = 4;
				else if(10000 <= injuryLevelPercentage)
					injuryLevel = 5;
				else
					injuryLevel = 1000;
			}

			int injured = 0;
			int hurtChance = rand.nextInt(30) + 1;
			if (durabilities[randomPlayer] == 5 && hurtChance == 1)
				injured = 1;
			else if (durabilities[randomPlayer] == 4 && (hurtChance == 2 || hurtChance == 3))
				injured = 1;
			else if (durabilities[randomPlayer] == 3 && hurtChance <= 4 && hurtChance <= 7)
				injured = 1;
			else if (durabilities[randomPlayer] == 2 && hurtChance <= 8 && hurtChance <= 15)
				injured = 1;
			else if (durabilities[randomPlayer] <= 1 && hurtChance <= 16 && hurtChance <= 31)
				injured = 1;
			else if (durabilities[randomPlayer] == 0)
				injured = 1;
			else
				injured = 0;

			if (injured == 1) {
				injuryCounter++;
				System.out.printf("%s suffers a Level %d injury (%s).\nRECOVERY TIME: ", names[randomPlayer], injuryLevel, getInjury(injuryLevel), injuryLevel);
				if (injuryLevel == 1) {
					if(getInjuryChance() <= 20)
						System.out.printf("Out for 1 sim. ");
					System.out.printf("Day-to-day for 1 sim.\n");
				}
				else if (injuryLevel == 2) {
					int x = getInjuryChance();
					if(x <= 33) {
						x = getInjuryChance();
						if(x <= 50)
							System.out.printf("Out for 1 sim. ");
						else
							System.out.printf("Out for 3 sims. ");
					}
					else
						System.out.printf("Out for 2 sims. ");
					System.out.printf("Day-to-day for 1 sim.\n");
				}
				else if (injuryLevel == 3) {
					durabilities[randomPlayer] -= 1;
					if(durabilities[randomPlayer] > -1) {
						int x = getInjuryChance();
						if(x <= 25)
							System.out.printf("Out for 5 sims. ");
						else if(x <= 26 && x <= 50)
							System.out.printf("Out for 4 sims. ");
						else
							System.out.printf("Out for 3 sims. ");
						System.out.printf("Day-to-day for 1 sim. Decreases to DUR %d. Overall decreases by 1.\n", durabilities[randomPlayer]);
					}
					else if(durabilities[randomPlayer] <= -1)
						System.out.printf("Player retires.");
				}
				else if (injuryLevel == 4) {
					durabilities[randomPlayer] -= 2;
					if(durabilities[randomPlayer] > -1) {
						int x = getInjuryChance();
						if(x <= 25)
							System.out.printf("Out for 6 sims. ");
						else if(x <= 26 && x <= 50)
							System.out.printf("Out for 5 sims. ");
						else
							System.out.printf("Out for 4 sims. ");
						System.out.printf("Day-to-day for 1 sim. Decreases to DUR %d. Overall decreases by 1.\n", durabilities[randomPlayer]);
					}
					else if(durabilities[randomPlayer] <= -1)
						System.out.printf("Player retires.\n");
				}
				else if(injuryLevel == 5)
					System.out.printf("Player retires.\n");
			}
		}
		System.out.printf("\n\n");
		sc.close();
	}
	
	public int getInjuryChance() {
		Random rand = new Random();
		int x = rand.nextInt(100) + 1;
		return x;
	}
	
	public String getInjury(int level) {
		String[][] injuries = {{"Bruised Foot", "Illness", "Arm Soreness", "Leg Soreness", "Minor Ankle Sprain", "Concussion Symptoms"}, {"Broken Jaw", "Broken Rib", "Torn Bicep", "Grade 1 Concussion", "Separated Shoulder", "Elbow Sprain", "Moderate Ankle Sprain"}, {"Broken Foot", "Grade 2 Concussion", "Torn UCL", "MCL Sprain", "Dislocated Shoulder", "Dislocated Kneecap"}, {"Broken Arm", "Broken Leg", "Broken Wrist", "Torn ACL", "Torn MCL", "Torn PCL", "Torn Achilles", "Hernia"}, {"Grade 3 Concussion", "Broken Neck", "Equipment Allergy", "Heart Problems", "Lumbar Degenerative Disc Disease", "Arthritis"}};
		level -= 1;
		Random rand = new Random();
		return injuries[level][rand.nextInt(injuries[level].length)];
	}
}

class Retirement {
	public static int age;
	public static int injury;
	public static int baseRetirementChance = 0;
	public static int injuryRetirementModifier = 0;
	public static int careerInjury;
	public static int careerInjuryModifier = 0;
	public static int regressing;
	public static int highestInjuryOccurences;
	public static String name;

	public void retire() throws FileNotFoundException {
		File input = new File("input.txt");
		Scanner sc = new Scanner(input);
		int count = sc.nextInt();

		for (int x = 0; x < count; x++) {
			Random rand = new Random();
			name = sc.next();
			age = sc.nextInt();
			injury = sc.nextInt();
			careerInjury = sc.nextInt();
			highestInjuryOccurences = sc.nextInt();
			regressing = sc.nextInt();

			if (regressing == 0) {
				if (age == 32) {
					baseRetirementChance = 2;
				}
				if (age == 33) {
					baseRetirementChance = 3;
				}
				if (age == 34) {
					baseRetirementChance = 4;
				}
				if (age == 35) {
					baseRetirementChance = 5;
				}
				if (age == 36) {
					baseRetirementChance = 6;
				}
				if (age == 37) {
					baseRetirementChance = 8;
				}
				if (age == 38) {
					baseRetirementChance = 10;
				}
				if (age == 39) {
					baseRetirementChance = 12;
				}
				if (age >= 40) {
					baseRetirementChance = 15;
				}
			}

			if (regressing == 1) {
				if (age == 32) {
					baseRetirementChance = 2;
				}
				if (age == 33) {
					baseRetirementChance = 6;
				}
				if (age == 34) {
					baseRetirementChance = 10;
				}
				if (age == 35) {
					baseRetirementChance = 15;
				}
				if (age == 36) {
					baseRetirementChance = 25;
				}
				if (age == 37) {
					baseRetirementChance = 50;
				}
				if (age == 38) {
					baseRetirementChance = 65;
				}
				if (age == 39) {
					baseRetirementChance = 70;
				}
				if (age >= 40) {
					baseRetirementChance = 75;
				}
			}

			if (injury == 4) {
				injuryRetirementModifier = 5;
			} else if (injury == 5) {
				injuryRetirementModifier = 10;
			} else if (injury == 6) {
				injuryRetirementModifier = 20;
			} else if (injury == 7) {
				injuryRetirementModifier = 25;
			}

			if (careerInjury == 6) {
				careerInjuryModifier = 5;
			}
			if (careerInjury == 7) {
				careerInjuryModifier = 15;
			}

			int retirementThreshold = baseRetirementChance + injuryRetirementModifier
					+ (careerInjuryModifier * highestInjuryOccurences);

			int retirementNumber = rand.nextInt(100) + 1;
			if (retirementNumber <= retirementThreshold)
				System.out.printf("%s retires\n", name);
			else if (retirementNumber > retirementThreshold)
				System.out.printf("%s does not retire\n", name);
		}
		sc.close();
	}
}

class Growth {
	public static int growthChance = 0;
	public static int overallCap = 0;
	public static int chanceOfPotentialChange = 0;
	public static int potentialChanged = 0;
	public static int overallChanged = 0;
	public static double individualChance = 0;
	public static double productionIC = 0;
	public static int age = 0;
	public static int position = 0;
	public static int potential = 0;
	public static int overall = 0;
	public static int production = 0;
	public static int injury = 0;
	public static int line = 0;
	public static String name;
	public static String playerType;

	public String generateSilverFactor(String playerType) {
		Random rand = new Random();
		String[] TWFfactors = { "Wheels", "Elite Edges", "In Reverse", "Ankle Breaker", "Make it Snappy", "Shnipe",
				"One Tee", "Thunder Clap", "Heatseeker", "Beauty Backhand", "Shock and Awe", "Tape to Tape", "Send It",
				"Magnetic", "Third Eye", "Yoink!", "Stick 'Em Up", "Shutdown", "Puck on a String", "Quick Pick",
				"All Alone" };
		String[] PLYfactors = { "Wheels", "Elite Edges", "In Reverse", "Ankle Breaker", "Tape to Tape", "Send It",
				"Magnetic", "Third Eye", "Puck on a String", "All Alone" };
		String[] SNPfactors = { "Wheels", "Elite Edges", "In Reverse", "Ankle Breaker", "Make it Snappy", "Shnipe",
				"One Tee", "Thunder Clap", "Heatseeker", "Beauty Backhand", "Shock and Awe", "Puck on a String",
				"All Alone", "Elite Trick Shooting" };
		String[] GRNfactors = { "Wheels", "Elite Edges", "In Reverse", "Ankle Breaker", "Yoink!", "Stick 'Em Up",
				"Shutdown", "Quick Pick" };
		String[] PWFfactors = { "Wheels", "Elite Edges", "In Reverse", "Ankle Breaker", "Make it Snappy", "Shnipe",
				"One Tee", "Thunder Clap", "Heatseeker", "Beauty Backhand", "Shock and Awe", "Yoink!", "Stick 'Em Up",
				"Shutdown", "Puck on a String", "Crease Crasher", "Big Tipper" };
		String[] ENFfactors = { "Wheels", "Elite Edges", "In Reverse", "Ankle Breaker", "Yoink!", "Stick 'Em Up",
				"Shutdown", "Crease Crasher", "Big Tipper" };
		String[] OFDTWDfactors = { "Wheels", "Elite Edges", "In Reverse", "Ankle Breaker", "Make it Snappy", "Shnipe",
				"One Tee", "Thunder Clap", "Heatseeker", "Beauty Backhand", "Shock and Awe", "Tape to Tape", "Send It",
				"Magnetic", "Third Eye", "Yoink!", "Stick 'Em Up", "Shutdown", "Quick Pick" };
		String[] DFDEFDfactors = { "Wheels", "Elite Edges", "In Reverse", "Ankle Breaker", "Yoink!", "Stick 'Em Up",
				"Shutdown", "Quick Pick" };
		String[] Gfactors = { "Contortionist", "All or Nothing", "Dialed In", "Post to Post" };

		String retval = "NULL";

		if (playerType.equals("TWF"))
			retval = TWFfactors[(rand.nextInt(TWFfactors.length))];
		if (playerType.equals("PLY"))
			retval = PLYfactors[(rand.nextInt(PLYfactors.length))];
		if (playerType.equals("SNP"))
			retval = SNPfactors[(rand.nextInt(SNPfactors.length))];
		if (playerType.equals("GRN"))
			retval = GRNfactors[(rand.nextInt(GRNfactors.length))];
		if (playerType.equals("PWF"))
			retval = PWFfactors[(rand.nextInt(PWFfactors.length))];
		if (playerType.equals("ENF"))
			retval = ENFfactors[(rand.nextInt(ENFfactors.length))];
		if (playerType.equals("OFD") || playerType.equals("TWD"))
			retval = OFDTWDfactors[(rand.nextInt(OFDTWDfactors.length))];
		if (playerType.equals("DFD") || playerType.equals("EFD"))
			retval = DFDEFDfactors[(rand.nextInt(DFDEFDfactors.length))];
		if (playerType.equals("HYB") || playerType.equals("BUT") || (playerType.equals("STD")))
			retval = Gfactors[(rand.nextInt(Gfactors.length))];

		return retval;
	}

	public String generateGoldFactor(String playerType) {
		Random rand = new Random();
		String[] TWFfactors = { "Send It", "Unstoppable Force", "No Contest", "Quick Draw", "Quick Pick",
				"Tape to Tape", "Yoink!", "Wheels", "Born Leader", "Magnetic", "Stick 'Em Up", "Shutdown", "Ice Pack",
				"In Reverse", "Elite Edges" };
		String[] PLYfactors = { "Send It", "Quick Draw", "Ankle Breaker", "Third Eye", "Tape to Tape",
				"Puck on a String", "Wheels", "Born Leader", "Magnetic", "Spin-o-Rama", "Elite Edges",
				"Beauty Backhand" };
		String[] SNPfactors = { "Heatseeker", "Seeing Eye", "Ankle Breaker", "Puck on a String", "One Tee",
				"It's Tricky", "Wheels", "Shnipe", "Born Leader", "Magnetic", "Spin-o-Rama", "Shock and Awe",
				"Make it Snappy", "Elite Edges", "Off the Rush", "Beauty Backhand", "Thunder Clap" };
		String[] GRNfactors = { "Send It", "Big Rig", "Unstoppable Force", "No Contest", "Quick Pick", "Born Leader",
				"Total Eclipse", "Big Tipper", "Close Quarters", "Magnetic", "Bouncer", "Ice Pack", "Back at Ya",
				"Elite Edges", "Truculence", "Crease Crasher" };
		String[] PWFfactors = { "Heatseeker", "Send It", "Big Rig", "No Contest", "One Tee", "Born Leader",
				"Big Tipper", "Close Quarters", "Magnetic", "Bouncer", "Back at Ya", "Elite Edges", "Truculence",
				"Crease Crasher" };
		String[] ENFfactors = { "Heatseeker", "Send It", "Big Rig", "Unstoppable Force", "No Contest", "Born Leader",
				"Total Eclipse", "Big Tipper", "Close Quarters", "Magnetic", "Bouncer", "Back at Ya", "Elite Edges",
				"Truculence", "Crease Crasher" };
		String[] OFDfactors = { "Heatseeker", "Seeing Eye", "Send It", "Unstoppable Force", "Quick Pick", "Third Eye",
				"Tape to Tape", "Yoink!", "One Tee", "Shnipe", "Born Leader", "Magnetic", "Stick 'Em Up", "Spin-o-Rama",
				"Back at Ya", "In Reverse", "Elite Edges", "Off the Rush", "Thunder Clap" };
		String[] TWDfactors = { "Send It", "Unstoppable Force", "Quick Pick", "Third Eye", "Tape to Tape", "Yoink!",
				"Born Leader", "Magnetic", "Stick 'Em Up", "Shutdown", "Back at Ya", "In Reverse", "Elite Edges",
				"Truculence", "Thunder Clap" };
		String[] DFDfactors = { "Send It", "No Contest", "Quick Pick", "Yoink!", "Born Leader", "Magnetic",
				"Stick 'Em Up", "Shutdown", "Ice Pack", "Back at Ya", "In Reverse", "Elite Edges", "Truculence" };
		String[] EFDfactors = { "Send It", "No Contest", "Quick Pick", "Yoink!", "Born Leader", "Magnetic",
				"Stick 'Em Up", "Shutdown", "Back at Ya", "In Reverse", "Elite Edges" };
		String[] BUTfactors = { "Dialed In", "Butterfly Effect", "Handled It", "Contortionist", "Last Stand",
				"Whirlwind", "Adventurer", "Sponge", "All or Nothing", "Post to Post", "X-Ray", "Elite Edges" };
		String[] Gfactors = { "Dialed In", "Handled It", "Contortionist", "Last Stand", "Whirlwind", "Adventurer",
				"Sponge", "All or Nothing", "Post to Post", "X-Ray", "Elite Edges" };

		if (playerType.equals("TWF"))
			return TWFfactors[(rand.nextInt(TWFfactors.length))];
		if (playerType.equals("PLY"))
			return PLYfactors[(rand.nextInt(PLYfactors.length))];
		if (playerType.equals("SNP"))
			return SNPfactors[(rand.nextInt(SNPfactors.length))];
		if (playerType.equals("GRN"))
			return GRNfactors[(rand.nextInt(GRNfactors.length))];
		if (playerType.equals("PWF"))
			return PWFfactors[(rand.nextInt(PWFfactors.length))];
		if (playerType.equals("ENF"))
			return ENFfactors[(rand.nextInt(ENFfactors.length))];
		if (playerType.equals("OFD"))
			return OFDfactors[(rand.nextInt(OFDfactors.length))];
		if (playerType.equals("TWD"))
			return TWDfactors[(rand.nextInt(TWDfactors.length))];
		if (playerType.equals("DFD"))
			return DFDfactors[(rand.nextInt(DFDfactors.length))];
		if (playerType.equals("EFD"))
			return EFDfactors[(rand.nextInt(EFDfactors.length))];
		if (playerType.equals("BUT"))
			return BUTfactors[(rand.nextInt(BUTfactors.length))];
		if (playerType.equals("HYB") || (playerType.equals("STD")))
			return Gfactors[(rand.nextInt(Gfactors.length))];
		else
			return "ERROR";
	}

	public void grow() throws FileNotFoundException {
		File input = new File("input.txt");
		Scanner sc = new Scanner(input);
		int count = sc.nextInt();

		for (int x = 0; x < count; x++) {
			Random rand = new Random();
			
			age = 0;
			position = 0;
			potential = 0;
			overall = 0;
			production = 0;
			injury = 0;
			line = 0;
			
			name = sc.next();
			position = sc.nextInt();
			playerType = sc.next();
			overall = sc.nextInt();
			potential = sc.nextInt();
			age = sc.nextInt();
			injury = sc.nextInt();
			line = sc.nextInt();
			production = sc.nextInt();
			int originalOverall = overall;
			
			growthChance = 0;
			overallCap = 0;
			chanceOfPotentialChange = 0;
			potentialChanged = 0;
			overallChanged = 0;
			individualChance = 0;
			productionIC = 0;

			// AGE
			if (position == 1 || position == 2) {
				if (age == 18)
					growthChance += 95;
				else if (age == 19)
					growthChance += 93;
				else if (age == 20)
					growthChance += 90;
				else if (age == 21)
					growthChance += 85;
				else if (age == 22)
					growthChance += 80;
				else if (age == 23)
					growthChance += 77;
				else if (age == 24) {
					growthChance += 70;
					individualChance -= 5;
				} else if (age == 25) {
					growthChance += 55;
					individualChance -= 5;
				} else if (age == 26) {
					growthChance += 35;
					overallCap -= 1;
					individualChance -= 10;
				}
			} else if (position == 3) {
				if (age == 18)
					growthChance += 95;
				else if (age == 19)
					growthChance += 93;
				else if (age == 20)
					growthChance += 90;
				else if (age == 21)
					growthChance += 88;
				else if (age == 22)
					growthChance += 85;
				else if (age == 23)
					growthChance += 80;
				else if (age == 24)
					growthChance += 75;
				else if (age == 25) {
					growthChance += 70;
					individualChance -= 5;
				} else if (age == 26) {
					growthChance += 50;
					individualChance -= 5;
				} else if (age == 27) {
					growthChance += 35;
					overallCap -= 1;
					individualChance -= 10;
				}
			}

			// POTENTIAL
			if (potential == 18) {
				growthChance += 10;
				overallCap += 10;
				individualChance += 80;
			} else if (potential == 17) {
				growthChance += 8;
				overallCap += 9;
				individualChance += 75;
			} else if (potential == 16) {
				growthChance += 5;
				overallCap += 9;
				individualChance += 65;
			} else if (potential == 15) {
				growthChance += 10;
				overallCap += 8;
				individualChance += 75;
			} else if (potential == 14) {
				growthChance += 8;
				overallCap += 8;
				individualChance += 65;
			} else if (potential == 13) {
				growthChance += 4;
				overallCap += 7;
				individualChance += 55;
			} else if (potential == 12) {
				growthChance += 8;
				overallCap += 6;
				individualChance += 65;
			} else if (potential == 11) {
				growthChance += 4;
				overallCap += 6;
				individualChance += 60;
			} else if (potential == 10) {
				overallCap += 5;
				individualChance += 50;
			} else if (potential == 9) {
				growthChance += 4;
				overallCap += 5;
				individualChance += 55;
			} else if (potential == 8) {
				overallCap += 4;
				individualChance += 50;
			} else if (potential == 7) {
				overallCap += 4;
				individualChance += 40;
			} else if (potential == 6) {
				overallCap += 3;
				individualChance += 45;
			} else if (potential == 5) {
				overallCap += 3;
				individualChance += 40;
			} else if (potential == 4) {
				overallCap += 2;
				individualChance += 20;
			} else if (potential == 3) {
				overallCap += 2;
				individualChance += 45;
			} else if (potential == 2) {
				overallCap += 2;
				individualChance += 30;
			} else if (potential == 1) {
				overallCap += 1;
				individualChance += 15;
			}

			// OVERALL
			if (position == 1 || position == 2) {
				if (overall > 95) {
					growthChance -= 10;
					overallCap -= 8;
					individualChance -= 75;
				} else if (95 >= overall && overall >= 92) {
					growthChance -= 5;
					overallCap -= 7;
					individualChance -= 50;
				} else if (91 >= overall && overall >= 88) {
					growthChance -= 5;
					overallCap -= 6;
					individualChance -= 40;
				} else if (87 >= overall && overall >= 85) {
					growthChance -= 5;
					overallCap -= 5;
					individualChance -= 30;
				} else if (84 >= overall && overall >= 82) {
					overallCap -= 4;
					individualChance -= 25;
				} else if (81 >= overall && overall >= 80) {
					overallCap -= 3;
					individualChance -= 20;
				} else if (79 >= overall && overall >= 78) {
					overallCap -= 2;
					individualChance -= 10;
				} else if (77 == overall || 76 == overall)
					overallCap -= 1;
				else if (74 == overall || 73 == overall)
					overallCap += 1;
				else if (72 >= overall && overall >= 70)
					overallCap += 2;
				else if (69 >= overall && overall >= 67) {
					overallCap += 3;
					individualChance += 5;
				} else if (66 >= overall && overall >= 64) {
					overallCap += 4;
					individualChance += 5;
				} else if (63 >= overall && overall >= 60) {
					overallCap += 5;
					individualChance += 10;
				} else if (60 > overall) {
					overallCap += 6;
					individualChance += 10;
				}
			} else if (position == 3) {
				if (overall > 95) {
					growthChance -= 10;
					overallCap -= 8;
					individualChance -= 75;
				} else if (95 >= overall && overall >= 92) {
					growthChance -= 5;
					overallCap -= 7;
					individualChance -= 50;
				} else if (91 >= overall && overall >= 88) {
					growthChance -= 5;
					overallCap -= 6;
					individualChance -= 35;
				} else if (87 >= overall && overall >= 85) {
					growthChance -= 5;
					overallCap -= 5;
					individualChance -= 20;
				} else if (84 >= overall && overall >= 82) {
					overallCap -= 4;
					individualChance -= 10;
				} else if (81 >= overall && overall >= 80) {
					overallCap -= 3;
					individualChance -= 5;
				} else if (79 >= overall && overall >= 77)
					overallCap -= 2;
				else if (76 == overall || 74 == overall)
					overallCap -= 1;
				else if (72 == overall || 71 == overall)
					overallCap += 1;
				else if (70 >= overall && overall >= 68)
					overallCap += 2;
				else if (67 >= overall && overall >= 64)
					overallCap += 3;
				else if (63 >= overall && overall >= 60) {
					overallCap += 4;
					individualChance += 5;
				} else if (60 > overall) {
					overallCap += 5;
					individualChance += 10;
				}

				// Taking off another 5 because goalies lose 5% automatically
				individualChance -= 5;
			}

			if (production == 5) {
				growthChance += 10;
				overallCap += 1;
				productionIC = 15;
				chanceOfPotentialChange = 35;
			}

			else if (production == 4) {
				growthChance += 5;
				overallCap += 1;
				productionIC = 10;
				chanceOfPotentialChange = 5;
			}

			else if (production == 2) {
				growthChance -= 5;
				overallCap -= 1;
				productionIC = -10;
				chanceOfPotentialChange = 5;
			}

			else if (production == 1) {
				growthChance -= 10;
				overallCap -= 1;
				productionIC = -15;
				chanceOfPotentialChange = 35;
			}

			if (line == 1 && position == 1) {
				productionIC = productionIC * 1.75;
			}

			else if (line == 2 && position == 1) {
				productionIC = productionIC * 1.5;
			}

			else if (line == 3 && position == 1) {
				productionIC = productionIC * 1.25;
			}

			else if (line == 4 && position == 1) {
				productionIC = productionIC * 1;
			}

			if (line == 1 && position == 2) {
				productionIC = productionIC * 1.6;
			}

			if (line == 2 && position == 2) {
				productionIC = productionIC * 1.2;
			}

			if (line == 3 && position == 2) {
				productionIC = productionIC * 1;
			}

			else if (line == 1 && position == 3) {
				productionIC = productionIC * 1.3;
			}

			else if (line == 2 && position == 3) {
				productionIC = productionIC * 1;
			}

			else if (line == 5) {
				if (age == 24) {
					if (position == 1 || position == 2) {
						overallCap -= 1;
						individualChance -= 5;
					}
				}

				else if (age == 25) {
					if (position == 1 || position == 2) {
						overallCap -= 1;
						individualChance -= 10;
					}
				}

				else if (age == 26) {
					if (position == 1 || position == 2) {
						overallCap -= 2;
						individualChance -= 15;
					}

					else if (position == 3) {
						overallCap -= 1;
						individualChance -= 5;
					}
				}

				else if (age == 27) {
					if (position == 3) {
						overallCap -= 1;
						individualChance -= 10;
					}
				}
			}

			// INJURY
			if (injury == 2) {
				growthChance -= 10;
			}

			else if (injury == 3) {
				growthChance -= 20;
				individualChance -= 5;
			}

			else if (injury == 4) {
				growthChance -= 40;
				individualChance -= 10;
			}

			else if (injury == 5) {
				growthChance -= 60;
				individualChance -= 15;
				overallCap -= 1;
			}

			else if (injury == 6) {
				growthChance -= 80;
				individualChance -= 20;
				overallCap -= 2;
			}

			else if (injury == 7) {
				growthChance -= 90;
				individualChance -= 25;
				overallCap -= 2;
			}

			individualChance = individualChance + productionIC;

			if (chanceOfPotentialChange > 0) {
				if (production == 4 || production == 5) {
					int potentialGeneratedNumber = rand.nextInt(100) + 1;
					if ((chanceOfPotentialChange == 5 && potentialGeneratedNumber <= 5)
							|| (chanceOfPotentialChange == 35 && potentialGeneratedNumber <= 35)) {
						int potentialChangeNumber = rand.nextInt(100) + 1;
						if (potential == 17) {
							potential = 18;
							potentialChanged = 1;
						}

						else if (potential == 16) {
							if (potentialChangeNumber <= 15) {
								potential = 18;
								potentialChanged = 1;
							} else if (potentialChangeNumber > 15) {
								potential = 17;
								potentialChanged = 1;
							}
						}

						else if (potential == 15) {
							if (potentialChangeNumber <= 30) {
								potential = 16;
								potentialChanged = 1;
							} else if (potentialChangeNumber > 30) {
								potential = 17;
								potentialChanged = 1;
							}
						}

						else if (potential == 14) {
							if (potentialChangeNumber <= 15) {
								potential = 15;
								potentialChanged = 1;
							} else if (potentialChangeNumber > 15 && potentialChangeNumber <= 50) {
								potential = 16;
								potentialChanged = 1;
							} else if (potentialChangeNumber > 50) {
								potential = 17;
								potentialChanged = 1;
							}
						}

						else if (potential == 13) {
							if (potentialChangeNumber <= 60) {
								potential = 14;
								potentialChanged = 1;
							} else if (potentialChangeNumber > 61 && potentialChangeNumber <= 80) {
								potential = 15;
								potentialChanged = 1;
							} else if (potentialChangeNumber > 80) {
								potential = 16;
								potentialChanged = 1;
							}
						}

						else if (potential == 12) {
							if (potentialChangeNumber <= 15) {
								potential = 13;
								potentialChanged = 1;
							} else if (potentialChangeNumber > 15 && potentialChangeNumber <= 85) {
								potential = 14;
								potentialChanged = 1;
							} else if (potentialChangeNumber > 85) {
								potential = 15;
								potentialChanged = 1;
							}
						}

						else if (potential == 11) {
							if (potentialChangeNumber <= 50) {
								potential = 12;
								potentialChanged = 1;
							} else if (potentialChangeNumber > 50 && potentialChangeNumber <= 65) {
								potential = 13;
								potentialChanged = 1;
							} else if (potentialChangeNumber > 65) {
								potential = 14;
								potentialChanged = 1;
							}
						}

						else if (potential == 10) {
							if (potentialChangeNumber <= 60) {
								potential = 11;
								potentialChanged = 1;
							} else if (potentialChangeNumber > 60 && potentialChangeNumber <= 80) {
								potential = 12;
								potentialChanged = 1;
							} else if (potentialChangeNumber > 80) {
								potential = 13;
								potentialChanged = 1;
							}
						}

						else if (potential == 9) {
							if (potentialChangeNumber <= 15) {
								potential = 10;
								potentialChanged = 1;
							} else if (potentialChangeNumber > 15 && potentialChangeNumber <= 85) {
								potential = 11;
								potentialChanged = 1;
							} else if (potentialChangeNumber > 85) {
								potential = 12;
								potentialChanged = 1;
							}
						}

						else if (potential == 8) {
							if (potentialChangeNumber <= 50) {
								potential = 9;
								potentialChanged = 1;
							} else if (potentialChangeNumber > 50 && potentialChangeNumber <= 65) {
								potential = 10;
								potentialChanged = 1;
							} else if (potentialChangeNumber > 65) {
								potential = 11;
								potentialChanged = 1;
							}
						}

						else if (potential == 7) {
							if (potentialChangeNumber <= 60) {
								potential = 8;
								potentialChanged = 1;
							} else if (potentialChangeNumber > 60 && potentialChangeNumber <= 80) {
								potential = 9;
								potentialChanged = 1;
							} else if (potentialChangeNumber > 80) {
								potential = 10;
								potentialChanged = 1;
							}
						}

						else if (potential == 6) {
							if (potentialChangeNumber <= 15) {
								potential = 7;
								potentialChanged = 1;
							} else if (potentialChangeNumber > 15 && potentialChangeNumber <= 85) {
								potential = 8;
								potentialChanged = 1;
							} else if (potentialChangeNumber > 85) {
								potential = 9;
								potentialChanged = 1;
							}
						}

						else if (potential == 5) {
							if (potentialChangeNumber <= 50) {
								potential = 6;
								potentialChanged = 1;
							} else if (potentialChangeNumber > 50 && potentialChangeNumber <= 65) {
								potential = 7;
								potentialChanged = 1;
							} else if (potentialChangeNumber > 65) {
								potential = 8;
								potentialChanged = 1;
							}
						}

						else if (potential == 4) {
							if (potentialChangeNumber <= 60) {
								potential = 5;
								potentialChanged = 1;
							} else if (potentialChangeNumber > 60 && potentialChangeNumber <= 80) {
								potential = 6;
								potentialChanged = 1;
							} else if (potentialChangeNumber > 80) {
								potential = 7;
								potentialChanged = 1;
							}
						}

						else if (potential == 3) {
							if (potentialChangeNumber <= 15) {
								potential = 4;
								potentialChanged = 1;
							} else if (potentialChangeNumber > 15 && potentialChangeNumber <= 85) {
								potential = 5;
								potentialChanged = 1;
							} else if (potentialChangeNumber > 85) {
								potential = 6;
								potentialChanged = 1;
							}
						}

						else if (potential == 2) {
							if (potentialChangeNumber <= 50) {
								potential = 3;
								potentialChanged = 1;
							} else if (potentialChangeNumber > 50 && potentialChangeNumber <= 65) {
								potential = 4;
								potentialChanged = 1;
							} else if (potentialChangeNumber > 65) {
								potential = 5;
								potentialChanged = 1;
							}
						}

						else if (potential == 1) {
							if (potentialChangeNumber <= 60) {
								potential = 2;
								potentialChanged = 1;
							} else if (potentialChangeNumber > 60 && potentialChangeNumber <= 80) {
								potential = 3;
								potentialChanged = 1;
							} else if (potentialChangeNumber > 80) {
								potential = 4;
								potentialChanged = 1;
							}
						}
					}
				} else if (production == 1 || production == 2) {
					int potentialGeneratedNumber = rand.nextInt(100) + 1;
					if ((chanceOfPotentialChange == 5 && potentialGeneratedNumber <= 5)
							|| (chanceOfPotentialChange == 35 && potentialGeneratedNumber <= 35)) {
						int potentialChangeNumber = rand.nextInt(100) + 1;
						if (potential == 18) {
							if (potentialChangeNumber <= 60) {
								potential = 17;
								potentialChanged = 1;
							} else if (potentialChangeNumber > 60 && potentialChangeNumber <= 80) {
								potential = 16;
								potentialChanged = 1;
							} else if (potentialChangeNumber > 80) {
								potential = 15;
								potentialChanged = 1;
							}
						}

						else if (potential == 17) {
							if (potentialChangeNumber <= 50) {
								potential = 16;
								potentialChanged = 1;
							} else if (potentialChangeNumber > 50 && potentialChangeNumber <= 65) {
								potential = 15;
								potentialChanged = 1;
							} else if (potentialChangeNumber > 65) {
								potential = 14;
								potentialChanged = 1;
							}
						}

						else if (potential == 16) {
							if (potentialChangeNumber <= 15) {
								potential = 15;
								potentialChanged = 1;
							} else if (potentialChangeNumber > 15 && potentialChangeNumber <= 85) {
								potential = 14;
								potentialChanged = 1;
							} else if (potentialChangeNumber > 85) {
								potential = 13;
								potentialChanged = 1;
							}
						}

						else if (potential == 15) {
							if (potentialChangeNumber <= 60) {
								potential = 14;
								potentialChanged = 1;
							} else if (potentialChangeNumber > 60 && potentialChangeNumber <= 80) {
								potential = 13;
								potentialChanged = 1;
							} else if (potentialChangeNumber > 80) {
								potential = 12;
								potentialChanged = 1;
							}
						}

						else if (potential == 14) {
							if (potentialChangeNumber <= 50) {
								potential = 13;
								potentialChanged = 1;
							} else if (potentialChangeNumber > 50 && potentialChangeNumber <= 65) {
								potential = 12;
								potentialChanged = 1;
							} else if (potentialChangeNumber > 65) {
								potential = 11;
								potentialChanged = 1;
							}
						}

						else if (potential == 13) {
							if (potentialChangeNumber <= 15) {
								potential = 12;
								potentialChanged = 1;
							} else if (potentialChangeNumber > 15 && potentialChangeNumber <= 85) {
								potential = 11;
								potentialChanged = 1;
							} else if (potentialChangeNumber > 85) {
								potential = 10;
								potentialChanged = 1;
							}
						}

						else if (potential == 12) {
							if (potentialChangeNumber <= 60) {
								potential = 11;
								potentialChanged = 1;
							} else if (potentialChangeNumber > 60 && potentialChangeNumber <= 80) {
								potential = 10;
								potentialChanged = 1;
							} else if (potentialChangeNumber > 80) {
								potential = 9;
								potentialChanged = 1;
							}
						}

						else if (potential == 11) {
							if (potentialChangeNumber <= 50) {
								potential = 10;
								potentialChanged = 1;
							} else if (potentialChangeNumber > 50 && potentialChangeNumber <= 65) {
								potential = 9;
								potentialChanged = 1;
							} else if (potentialChangeNumber > 65) {
								potential = 8;
								potentialChanged = 1;
							}
						}

						else if (potential == 10) {
							if (potentialChangeNumber <= 15) {
								potential = 9;
								potentialChanged = 1;
							} else if (potentialChangeNumber > 15 && potentialChangeNumber <= 85) {
								potential = 8;
								potentialChanged = 1;
							} else if (potentialChangeNumber > 85) {
								potential = 7;
								potentialChanged = 1;
							}
						}

						else if (potential == 9) {
							if (potentialChangeNumber <= 60) {
								potential = 8;
								potentialChanged = 1;
							} else if (potentialChangeNumber > 60 && potentialChangeNumber <= 80) {
								potential = 7;
								potentialChanged = 1;
							} else if (potentialChangeNumber > 80) {
								potential = 6;
								potentialChanged = 1;
							}
						}

						else if (potential == 8) {
							if (potentialChangeNumber <= 50) {
								potential = 7;
								potentialChanged = 1;
							} else if (potentialChangeNumber > 50 && potentialChangeNumber <= 65) {
								potential = 6;
								potentialChanged = 1;
							} else if (potentialChangeNumber > 65) {
								potential = 5;
								potentialChanged = 1;
							}
						}

						else if (potential == 7) {
							if (potentialChangeNumber <= 15) {
								potential = 6;
								potentialChanged = 1;
							} else if (potentialChangeNumber > 15 && potentialChangeNumber <= 85) {
								potential = 5;
								potentialChanged = 1;
							} else if (potentialChangeNumber > 85) {
								potential = 4;
								potentialChanged = 1;
							}
						}

						else if (potential == 6) {
							if (potentialChangeNumber <= 60) {
								potential = 5;
								potentialChanged = 1;
							} else if (potentialChangeNumber > 60 && potentialChangeNumber <= 80) {
								potential = 4;
								potentialChanged = 1;
							} else if (potentialChangeNumber > 80) {
								potential = 3;
								potentialChanged = 1;
							}
						}

						else if (potential == 5) {
							if (potentialChangeNumber <= 50) {
								potential = 4;
								potentialChanged = 1;
							} else if (potentialChangeNumber > 50 && potentialChangeNumber <= 65) {
								potential = 3;
								potentialChanged = 1;
							} else if (potentialChangeNumber > 65) {
								potential = 2;
								potentialChanged = 1;
							}
						}

						else if (potential == 4) {
							if (potentialChangeNumber <= 15) {
								potential = 3;
								potentialChanged = 1;
							} else if (potentialChangeNumber > 15 && potentialChangeNumber <= 85) {
								potential = 2;
								potentialChanged = 1;
							} else if (potentialChangeNumber > 85) {
								potential = 1;
								potentialChanged = 1;
							}
						}

						else if (potential == 3) {
							if (potentialChangeNumber <= 30) {
								potential = 2;
								potentialChanged = 1;
							} else if (potentialChangeNumber > 70) {
								potential = 1;
								potentialChanged = 1;
							}
						}

						else if (potential == 2) {
							potential = 1;
							potentialChanged = 1;
						}
					}
				}
			}

			int changeovr = 0;
			int doesPlayerGrow = rand.nextInt(100) + 1;
			if (overallCap > 0) {
				if (doesPlayerGrow <= growthChance) {
					for (int y = 0; y < overallCap; y++) {
						int overallBoostNumber = rand.nextInt(100) + 1;
						if (overallBoostNumber <= individualChance) {
							overall++;
							changeovr++;
							overallChanged = 1;
						}
					}
				}
			}

			int xFactorChance = rand.nextInt(100) + 1;
			String newXFactor1 = "EMPTY";
			String newXFactor2 = "EMPTY";
			String newXFactor3 = "EMPTY";
			String newGold = "EMPTY";

			if (changeovr == 6 && potential >= 9 && potential < 12 && (overall - changeovr >= 65)) {
				if (xFactorChance <= 50)
					newXFactor1 = generateSilverFactor(playerType);
			}
			if (changeovr == 7 && potential >= 9 && potential < 12 && (overall - changeovr >= 65)) {
				if (xFactorChance <= 75)
					newXFactor1 = generateSilverFactor(playerType);
				xFactorChance = rand.nextInt(100) + 1;
				if (xFactorChance <= 20)
					newXFactor2 = generateSilverFactor(playerType);
			}
			if (changeovr >= 8 && potential >= 9 && potential < 12 && (overall - changeovr >= 65)) {
				if (xFactorChance <= 80)
					newXFactor1 = generateSilverFactor(playerType);
				xFactorChance = rand.nextInt(100) + 1;
				if (xFactorChance <= 35)
					newXFactor2 = generateSilverFactor(playerType);
			}
			if (changeovr == 6 && potential >= 12 && potential < 14 && (overall - changeovr >= 65)) {
				if (xFactorChance <= 60)
					newXFactor1 = generateSilverFactor(playerType);
			}
			if (changeovr == 7 && potential >= 12 && potential < 14 && (overall - changeovr >= 65)) {
				if (xFactorChance <= 85)
					newXFactor1 = generateSilverFactor(playerType);
				xFactorChance = rand.nextInt(100) + 1;
				if (xFactorChance <= 40)
					newXFactor2 = generateSilverFactor(playerType);
			}
			if (changeovr >= 8 && potential >= 12 && potential < 14 && (overall - changeovr >= 65)) {
				newXFactor1 = generateSilverFactor(playerType);
				xFactorChance = rand.nextInt(100) + 1;
				if (xFactorChance <= 60)
					newXFactor2 = generateSilverFactor(playerType);
				xFactorChance = rand.nextInt(100) + 1;
				if (xFactorChance <= 15)
					newXFactor3 = generateSilverFactor(playerType);
			}
			if (changeovr == 6 && potential >= 14 && (overall - changeovr >= 65)) {
				if (xFactorChance <= 15)
					newGold = generateGoldFactor(playerType);
			}
			if (changeovr == 7 && potential >= 14 && (overall - changeovr >= 65)) {
				if (xFactorChance <= 25)
					newGold = generateGoldFactor(playerType);
			}
			if (changeovr >= 8 && potential >= 14 && (overall - changeovr >= 65)) {
				if (xFactorChance <= 40)
					newGold = generateGoldFactor(playerType);
			}

			if (potentialChanged == 1) {
				System.out.printf("%s potential changed to potential %d\n", name, potential);
			}

			if (overallChanged == 1) {
				System.out.printf("%s grew from %d to %d overall\n", name, originalOverall, overall);
			}

			if (newXFactor1 != "EMPTY" || newXFactor2 != "EMPTY" || newXFactor3 != "EMPTY") {
				System.out.printf("%s tries to acquire Zone Ability: ", name);
				if (newXFactor1 != "EMPTY")
					System.out.printf("%s", newXFactor1);
				if (newXFactor2 != "EMPTY")
					System.out.printf(" and %s", newXFactor2);
				if (newXFactor3 != "EMPTY")
					System.out.printf(" and %s", newXFactor3);
				System.out.printf("\n");
			}

			if (newGold != "EMPTY")
				System.out.printf("%s tries to acquire Superstar Ability: %s\n", name, newGold);

			if (overallChanged < 1 && potentialChanged < 1) {
				System.out.printf("%s did not grow\n", name);
			}
		}
		sc.close();
	}
}

class Person {
	public int french;
	public String firstName;
	public String lastName;
	public int age; // For players: USA and Canada 18 or 19, everyone else 20. For coaches: 30-50
	public String nation;

	public Person() {
		nation = generateNation();
		age = 18; // Age is not generated at the Person level because it varies between player and
		// coach. For testing, it is 18
		firstName = generateFirstName();
		lastName = generateLastName();
	}

	public String generateNation() {
		Random rand = new Random();
		int playerNationalityNumber = 1 + rand.nextInt(1000);
		if (playerNationalityNumber <= 313)
			return "Canada";
		else if (playerNationalityNumber >= 314 && playerNationalityNumber <= 566)
			return "United States";
		else if (playerNationalityNumber >= 567 && playerNationalityNumber <= 692)
			return "Russia";
		else if (playerNationalityNumber >= 693 && playerNationalityNumber <= 812)
			return "Sweden";
		else if (playerNationalityNumber >= 813 && playerNationalityNumber <= 914)
			return "Finland";
		else if (playerNationalityNumber >= 915 && playerNationalityNumber <= 945)
			return "Czechia";
		else if (playerNationalityNumber >= 946 && playerNationalityNumber <= 957)
			return "Belarus";
		else if (playerNationalityNumber >= 958 && playerNationalityNumber <= 963)
			return "Denmark";
		else if (playerNationalityNumber >= 964 && playerNationalityNumber <= 971)
			return "Germany";
		else if (playerNationalityNumber >= 972 && playerNationalityNumber <= 979)
			return "Latvia";
		else if (playerNationalityNumber >= 980 && playerNationalityNumber <= 986)
			return "Slovakia";
		else if (playerNationalityNumber >= 987 && playerNationalityNumber <= 993)
			return "Switzerland";
		else if (playerNationalityNumber >= 994 && playerNationalityNumber <= 996)
			return "Slovenia";
		else if (playerNationalityNumber >= 997 && playerNationalityNumber <= 998)
			return "Norway";
		else
			return "Austria";
	}

	public String generateFirstName() {
		Random rand = new Random();
		String canadaFirst[] = { "Michael", "Jacob", "Alex", "Tyler", "Andrew", "Daniel", "John", "Matthew", "Ryan", "Austin", "David", "Chris", "Nick", "Brandon", "Nathan", "Anthony", "Ethan", "Justin", "Joshua", "Jordan", "Jake", "Jack", "Dylan", "James", "Kyle", "Kevin", "Ben", "Noah", "Eric", "Sam", "Christian", "Josh", "Zach", "Joseph", "Logan", "Jonathan", "Adam", "Aaron", "Jason", "Christopher", "Caleb", "Brian", "William", "Robert", "Joe", "Luke", "Matt", "Cameron", "Thomas", "Hunter", "Evan", "Nicholas", "Cody", "Zachary", "Chase", "Mike", "Jose", "Steven", "Max", "Maximillian", "Ian", "Connor", "Mark", "Cole", "Patrick", "Sean", "Shaun", "Samuel", "Gabriel", "Nate", "Devin", "Will", "Alexander", "Jeremy", "Isaac", "Jesse", "Mason", "Joey", "Carlos", "Bryan", "Jared", "Tanner", "Peter", "Tristian", "Gavin", "Shane", "Seth", "Stepehn", "Paul", "Trevor", "Elijah", "Brendan", "Isiah", "Zack", "Marcus", "Lucas", "Garrett", "Aidan", "Luis", "Charles", "Eli", "Blake", "Charlie", "Jay", "Jimmy", "George", "Brock", "Rex", "Jackson", "Chuck", "Erick", "Erik", "Griffin", "Rider", "Devan", "Lance", "Carter", "Steve", "Quinn", "Quinton", "Jeffrey", "Timmy", "Timothy", "Scott", "Ronald", "Gary", "Auston", "Walker", "Aaron", "Miles", "Brooks", "Dejuan", "Gabe", "Sebastian", "Gregg", "Kelly", "Kellyn", "Aron", "Earnie", "B.J.", "R.J.", "Ricky", "Ricardo", "Weston", "Liam", "Henry", "Oliver", "Drew", "Matty", "Colin", "Cal", "Conor", "Tage", "Kodie", "Anton", "Bret", "Brett", "Hayden", "Jonas", "Mitchell", "Vince", "Vincent", "Vinny", "Hugh", "Bob", "Bobby", "Don", "Willard", "Ty", "Bill", "Dave", "Lee", "Dustin", "Tim", "Tom", "Tommy", "Keith", "Craig", "Toby", "Tobey", "Cory", "Corey", "Richard", "Dick", "Marty", "Brooks", "Freddy", "Doug", "John-Paul", "Jeff", "Nico", "Francis", "Damian", "Dan", "Ted", "Teddy", "Todd", "Dieter", "Derek", "Derrick", "Darby", "Deron", "Karl", "Trent", "Parris", "Bates", "Garth", "Chad", "Troy", "Trey", "Jim", "Guy", "Ray", "Allen", "Lane", "Al", "Marc", "Buzz", "Ken", "Herb", "Robbie", "Walter", "Wally", "Bruce", "Larry", "Lou", "Leonard", "Red", "Dates", "Roger", "Rod", "Ed", "Eddy", "Wendell", "Wellington", "Weldy", "Wilson", "Grant", "Arnie", "Rube", "Len", "Gerald", "Cliff", "Stan", "Ralph", "Goodwin", "Norman", "Harold", "Harry", "Curt", "Phil", "Philip", "Elbridge", "Frank", "Fred", "Aiden", "Alan", "Alonzo", "Alvin", "Anderson", "Andy", "Anson", "Aston", "Austen", "Austin", "Bailey", "Barret", "Barrett", "Baxter", "Bennet", "Bennett", "Brad", "Braden", "Brady", "Brayden", "Brenden", "Brendon", "Brennan", "Brent", "Cade", "Caden", "Calder", "Cale", "Cam", "Camden", "Cedric", "Charley", "Chris", "Clark", "Clay", "Clement", "Clifford", "Clifton", "Colby", "Collin", "Colton", "Conner", "Cooper", "Dallas", "Dalton", "Damion", "Danny", "Darrell", "Darryl", "Dawson", "Deacon", "Dean", "DeAndre", "Dennis", "Denton", "Derick", "Devon", "Dillon", "Dominic", "Dominick", "Donny", "Dougie", "Duncan", "Dwight", "Eddie", "Elliott", "Emmett", "Felix", "Finn", "Frankie", "Freddie", "Frederick", "Fredric", "Fredrick", "Garnet", "Garnett", "Garret", "Glen", "Glenn", "Greg", "Harris", "Harrison", "Harvey", "Heath", "Holden", "Howard", "Howell", "Howie", "Ike", "Indy", "Irwin", "Jaden", "Jayden", "Jalen", "Jamieson", "Jaylen", "Jayson", "Isaiah", "Jefferey", "Jeffery", "Jere", "Jeremiah", "Joel", "Johnny", "Jon", "Jonny", "Julian", "Kaden", "Keenan", "Kendall", "Kendrick", "Kenny", "Kevan", "Lance", "Landon", "Lex", "Louis", "Mack", "MacKenzie", "Mackenzie", "Malachi", "Malcolm", "Manny", "Marshall", "Martin", "Marvin", "Mathew", "Mathias", "Maverick", "Mel", "Mordecai", "Morgan", "Morty", "Myles", "Neal", "Nic", "Oliver", "Oscar", "Owen", "Parker", "Pat", "Pierce", "Pearce", "Albert", "Phillip", "Phoenix", "Randy", "Raymond", "Reagan", "Reece", "Reese", "Reggie", "Reilly", "Rick", "Sammy", "Scottie", "Shawn", "Skylar", "Skyler", "Steph", "Taylor", "Theo", "Theodore", "Travis", "Tucker", "Victor", "Warner", "Wyatt", "Xavier", "Curtis"};

		String frenchFirst[] = { "Adrien", "Alan", "Aldric", "Alexandre", "Andre", "Antoine", "Augustin", "Benjamin",
				"Bruno", "Cedric", "Charles", "Christophe", "Damien", "Daniel", "Denis", "Edmond", "Emeric", "Eric",
				"Fabien", "Felix", "Francois", "Frederic", "Gerald", "Germain", "Henry", "Jacques", "Jean-Baptiste",
				"Jean-Charles", "Jean-Francois", "Jeremy", "Jerome", "Jeremie", "Julien", "Laurent", "Louis", "Manuel",
				"Marcel", "Martin", "Mathias", "Mathieu", "Matthieu", "Maurice", "Max", "Maxime", "Nicolas", "Oliver",
				"Oscar", "Phillip", "Philippe", "Pierre", "Raphael", "Renaud", "Rene", "Sebastien", "Simon", "Stephane",
				"Theodore", "Timothy", "Vincent", "Xavier" };

		String usaFirst[] = { "Michael", "Jacob", "Alex", "Tyler", "Andrew", "Daniel", "John", "Matthew", "Ryan", "Austin", "David", "Chris", "Nick", "Brandon", "Nathan", "Anthony", "Ethan", "Justin", "Joshua", "Jordan", "Jake", "Jack", "Dylan", "James", "Kyle", "Kevin", "Ben", "Noah", "Eric", "Sam", "Christian", "Josh", "Zach", "Joseph", "Logan", "Jonathan", "Adam", "Aaron", "Jason", "Christopher", "Caleb", "Brian", "William", "Robert", "Joe", "Luke", "Matt", "Cameron", "Thomas", "Hunter", "Evan", "Nicholas", "Cody", "Zachary", "Chase", "Mike", "Jose", "Steven", "Max", "Maximillian", "Ian", "Connor", "Mark", "Cole", "Patrick", "Sean", "Shaun", "Samuel", "Gabriel", "Nate", "Devin", "Will", "Alexander", "Jeremy", "Isaac", "Jesse", "Mason", "Joey", "Carlos", "Bryan", "Jared", "Tanner", "Peter", "Tristian", "Gavin", "Shane", "Seth", "Stepehn", "Paul", "Trevor", "Elijah", "Brendan", "Isiah", "Zack", "Marcus", "Lucas", "Garrett", "Aidan", "Luis", "Charles", "Eli", "Blake", "Charlie", "Jay", "Jimmy", "George", "Brock", "Rex", "Jackson", "Chuck", "Erick", "Erik", "Griffin", "Rider", "Devan", "Lance", "Carter", "Steve", "Quinn", "Quinton", "Jeffrey", "Timmy", "Timothy", "Scott", "Ronald", "Gary", "Auston", "Walker", "Aaron", "Miles", "Brooks", "Dejuan", "Gabe", "Sebastian", "Gregg", "Kelly", "Kellyn", "Aron", "Earnie", "B.J.", "R.J.", "Ricky", "Ricardo", "Weston", "Liam", "Henry", "Oliver", "Drew", "Matty", "Colin", "Cal", "Conor", "Tage", "Kodie", "Anton", "Bret", "Brett", "Hayden", "Jonas", "Mitchell", "Vince", "Vincent", "Vinny", "Hugh", "Bob", "Bobby", "Don", "Willard", "Ty", "Bill", "Dave", "Lee", "Dustin", "Tim", "Tom", "Tommy", "Keith", "Craig", "Toby", "Tobey", "Cory", "Corey", "Richard", "Dick", "Marty", "Brooks", "Freddy", "Doug", "John-Paul", "Jeff", "Nico", "Francis", "Damian", "Dan", "Ted", "Teddy", "Todd", "Dieter", "Derek", "Derrick", "Darby", "Deron", "Karl", "Trent", "Parris", "Bates", "Garth", "Chad", "Troy", "Trey", "Jim", "Guy", "Ray", "Allen", "Lane", "Al", "Marc", "Buzz", "Ken", "Herb", "Robbie", "Walter", "Wally", "Bruce", "Larry", "Lou", "Leonard", "Red", "Dates", "Roger", "Rod", "Ed", "Eddy", "Wendell", "Wellington", "Weldy", "Wilson", "Grant", "Arnie", "Rube", "Len", "Gerald", "Cliff", "Stan", "Ralph", "Goodwin", "Norman", "Harold", "Harry", "Curt", "Phil", "Philip", "Elbridge", "Frank", "Fred", "Aiden", "Alan", "Alonzo", "Alvin", "Anderson", "Andy", "Anson", "Aston", "Austen", "Austin", "Bailey", "Barret", "Barrett", "Baxter", "Bennet", "Bennett", "Brad", "Braden", "Brady", "Brayden", "Brenden", "Brendon", "Brennan", "Brent", "Cade", "Caden", "Calder", "Cale", "Cam", "Camden", "Cedric", "Charley", "Chris", "Clark", "Clay", "Clement", "Clifford", "Clifton", "Colby", "Collin", "Colton", "Conner", "Cooper", "Dallas", "Dalton", "Damion", "Danny", "Darrell", "Darryl", "Dawson", "Deacon", "Dean", "DeAndre", "Dennis", "Denton", "Derick", "Devon", "Dillon", "Dominic", "Dominick", "Donny", "Dougie", "Duncan", "Dwight", "Eddie", "Elliott", "Emmett", "Felix", "Finn", "Frankie", "Freddie", "Frederick", "Fredric", "Fredrick", "Garnet", "Garnett", "Garret", "Glen", "Glenn", "Greg", "Harris", "Harrison", "Harvey", "Heath", "Holden", "Howard", "Howell", "Howie", "Ike", "Indy", "Irwin", "Jaden", "Jayden", "Jalen", "Jamieson", "Jaylen", "Jayson", "Isaiah", "Jefferey", "Jeffery", "Jere", "Jeremiah", "Joel", "Johnny", "Jon", "Jonny", "Julian", "Kaden", "Keenan", "Kendall", "Kendrick", "Kenny", "Kevan", "Lance", "Landon", "Lex", "Louis", "Mack", "MacKenzie", "Mackenzie", "Malachi", "Malcolm", "Manny", "Marshall", "Martin", "Marvin", "Mathew", "Mathias", "Maverick", "Mel", "Mordecai", "Morgan", "Morty", "Myles", "Neal", "Nic", "Oliver", "Oscar", "Owen", "Parker", "Pat", "Pierce", "Pearce", "Albert", "Phillip", "Phoenix", "Randy", "Raymond", "Reagan", "Reece", "Reese", "Reggie", "Reilly", "Rick", "Sammy", "Scottie", "Shawn", "Skylar", "Skyler", "Steph", "Taylor", "Theo", "Theodore", "Travis", "Tucker", "Victor", "Warner", "Wyatt", "Xavier", "Curtis"};

		String russiaFirst[] = { "Aleksandr", "Aleksei", "Alex", "Alexei", "Alexey", "Anatoli", "Anatoliy", "Anatoly",
				"Andrei", "Arseni", "Artem", "Artemiy", "Arseniy", "Boris", "Boleslav", "Daniil", "Dima", "Dimitri",
				"Dmitri", "Dmitrii", "Dmitri", "Dmitry", "Dorofeiy", "Dorofey", "Evgeni", "Evgeniy", "Evgeny",
				"Evgenii", "Fedor", "Fyodor", "Grigori", "Grigorii", "Grigoriy", "Grigory", "Ignatiy", "Igor", "Ilya",
				"Ipati", "Ipatiy", "Ivan", "Kazimir", "Kirill", "Klim", "Lavrenti", "Lavrentiy", "Lavrenty",
				"Mechislav", "Mikhail", "Mitya", "Mstislav", "Motya", "Nika", "Nikita", "Nikolai", "Nikolay", "Oleg",
				"Pasha", "Patya", "Pavel", "Pyotr", "Prokhor", "Prokopy", "Prokopiy", "Radimir", "Rostislav", "Semyon",
				"Sergei", "Sergej", "Sergey", "Stanislav", "Svyatoslav", "Timofey", "Vadik", "Vadim", "Vadimir",
				"Valentin", "Valeri", "Valeriy", "Valery", "Varfolomey", "Varfolomei", "Vasili", "Vasiliy", "Vasily",
				"Viktor", "Vitali", "Vitaly", "Vladimir", "Vladislav", "Vlas", "Vlasiy", "Vyacheslav", "Yakim",
				"Yaroslav", "Yasha", "Yuri"};

		String swedenFirst[] = { "Adam", "Adrian", "Anton", "Artur", "Arvid", "Axel", "Bengt", "Christian",
				"Christoffer", "Elias", "Emil", "Eric", "Erik", "Felix", "Filip", "Fredrik", "Frej", "Gustav", "Hampus",
				"Hugo", "Jens", "Johan", "Jon", "Jorgen", "Kalle", "Karl", "Kristian", "Kristoffer", "Lars", "Leo",
				"Lias", "Linus", "Liam", "Loui", "Lucas", "Lukas", "Mats", "Mattias", "Melker", "Michael", "Mikael",
				"Nicklas", "Niklas", "Nils", "Olof", "Olov", "Oskar", "Osvald", "Patrik", "Peter", "Rasmus", "Rikard",
				"Rolf", "Ruben", "Sebastian", "Sven", "Teodor", "Thorvald", "Tim", "Tobias", "Tomas", "Ulf", "Ulrik",
				"Victor", "Viktor", "Vilhelm", "Vilmar" };

		String finlandFirst[] = { "Aapo", "Aatu", "Aki", "Aku", "Aleksi", "Altti", "Antti", "Artturi", "Arttu", "Atte",
				"Esa", "Heikki", "Iiro", "Jaakko", "Jalo", "Jari", "Jani", "Jarmo", "Joonas", "Joona", "Jukka", "Jussi",
				"Juuso", "Kaapo", "Kari", "Kauko", "Kimi", "Kusti", "Lauri", "Lassi", "Leevi", "Lenni", "Matti",
				"Martti", "Mauri", "Mika", "Mikael", "Mikko", "Niko", "Oskari", "Paavali", "Paavo", "Pekka", "Pertti",
				"Pentti", "Riku", "Roope", "Saku", "Sami", "Sampo", "Santeri", "Seppo", "Taavi", "Tahvo", "Teppo",
				"Tommi", "Timo", "Vilhelmi", "Ville" };

		String czechFirst[] = { "Ales", "Alexandr", "Alexej", "Antonin", "Blazej", "Bohumil", "Bohuslav", "Boleslav",
				"Boris", "Bretislav", "Bronislav", "Cenek", "Cyril", "Dobroslav", "Dominik", "Elias", "Frantisek",
				"Hynek", "Havel", "Honza", "Jarmil", "Jaromir", "Jaroslav", "Jeronym", "Jiri", "Jonas", "Kaspar",
				"Kazimir", "Klement", "Kristian", "Krystof", "Libor", "Lubomir", "Ludek", "Ludvik", "Lukas", "Lumir",
				"Matej", "Mikolas", "Michal", "Milan", "Miloslav", "Mirek", "Nikola", "Ondrej", "Pavel", "Radek",
				"Radim", "Radko", "Radomir", "Rostislav", "Samuel", "Tobias", "Tomas", "Vaclav", "Viktor", "Vitek",
				"Vlastislav", "Vratislav" };

		String denmarkFirst[] = { "William", "Noah", "Lucas", "Emil", "Oliver", "Oscar", "Victor", "Malthe", "Alfred",
				"Carl", "Frederik", "Elias", "Magnus", "Valdemar", "Villads", "Alexander", "Christian", "August",
				"Johan", "Felix" };

		String germanyFirst[] = { "Brandt", "Faust", "Gotthilf", "Gotthold", "Herschel", "Hershel", "Horst", "Kiefer",
				"Leberecht", "Linden", "Luther", "Wendell", "Leon", "Moritz", "Alexander", "Daniel", "Dennis",
				"Hermann" };

		String latviaFirst[] = { "Aleksandrs", "Alvis", "Arturs", "Arvids", "Davids", "Daniels", "Edgars", "Edvins",
				"Edvards", "Filips", "Georgijs", "Ivans", "Janis", "Karlis", "Kirils", "Ludvigs", "Martins", "Matiss",
				"Mikhelis", "Niklavs", "Nikolajs", "Oskars", "Pauls", "Peteris", "Ricards", "Roberts", "Romans",
				"Stanislavs", "Sergejs", "Stefans", "Teodors", "Tomass", "Viktors", "Vilhelms", "Vladimirs",
				"Voldemars" };

		String slovakiaFirst[] = { "Dusan", "Aljaz", "Blaz", "Brin", "Crtomir", "Cvetko", "Zoran", "Zlatko", "Zlatan",
				"Stojan", "Rajko", "Goran" };

		String sloveniaFirst[] = { "Dusan", "Aljaz", "Blaz", "Brin", "Crtomir", "Cvetko", "Zoran", "Zlatko", "Zlatan",
				"Stojan", "Rajko", "Goran" };

		String norwayFirst[] = { "Age", "Agnar", "Aksel", "Alfred", "Amund", "Andor", "Bjarte", "Christian",
				"Christoffer", "Carl", "Daniel", "David", "Edvard", "Edvin", "Erik", "Espen", "Fredrik", "Gabriel",
				"Gaute", "Hans", "Herman", "Hjalmar", "Ivan", "Isak", "Jacob", "Johan", "Johannes", "Jon", "Jonas",
				"Ketil", "Lars", "Linus", "Lukas", "Lucas", "Mats", "Matthias", "Njord", "Njal", "Nils", "Olaf",
				"Osvald", "Otto", "Oskar", "Patrick", "Paul", "Peter", "Rickard", "Reidar", "Sigmund", "Severin",
				"Samuel", "Teodor", "Theodor", "Thorbjorn", "Tobias", "Valdemar", "Waldemar" };

		int frenchChance = (1 + rand.nextInt(100));
		if (frenchChance <= 15)
			french = 1;

		if (nation == "Canada") {
			if (french == 0)
				return canadaFirst[(rand.nextInt(canadaFirst.length))];
			else
				return frenchFirst[(rand.nextInt(frenchFirst.length))];
		}

		if (nation == "United States")
			return usaFirst[(rand.nextInt(usaFirst.length))];

		if (nation == "Russia" || nation == "Belarus")
			return russiaFirst[(rand.nextInt(russiaFirst.length))];

		if (nation == "Sweden")
			return swedenFirst[(rand.nextInt(swedenFirst.length))];

		if (nation == "Finland")
			return finlandFirst[(rand.nextInt(finlandFirst.length))];

		if (nation == "Czechia")
			return czechFirst[(rand.nextInt(czechFirst.length))];

		if (nation == "Denmark")
			return denmarkFirst[(rand.nextInt(denmarkFirst.length))];

		if (nation == "Germany" || nation == "Austria")
			return germanyFirst[(rand.nextInt(germanyFirst.length))];

		if (nation == "Latvia")
			return latviaFirst[(rand.nextInt(latviaFirst.length))];

		if (nation == "Slovakia")
			return slovakiaFirst[(rand.nextInt(slovakiaFirst.length))];

		if (nation == "Switzerland") {
			if (french == 0)
				return germanyFirst[(rand.nextInt(germanyFirst.length))];
			else
				return frenchFirst[(rand.nextInt(frenchFirst.length))];
		}

		if (nation == "Slovenia")
			return sloveniaFirst[(rand.nextInt(sloveniaFirst.length))];

		if (nation == "Norway")
			return norwayFirst[(rand.nextInt(norwayFirst.length))];

		else
			return "ERROR";
	}

	public String generateLastName() {
		Random rand = new Random();
		String canadaLast[] = { "Abbey", "Abbott", "Abrams", "Acker", "Ackerman", "Adams", "Addison", "Adkins", "Aiken",
				"Ainsworth", "Aitken", "Akerman", "Akers", "Albinson", "Alden", "Alexander", "Allan", "Allard", "Allen",
				"Allison", "Anderson", "Andrews", "Andrewson", "Appleton", "Archer", "Arkwright", "Armistead",
				"Armstrong", "Arrington", "Ashwroth", "Atkinson", "Atkinson", "Attwater", "Attwood", "Audley", "Avery",
				"Ayers", "Ayres", "Badcock", "Bagley", "Bailey", "Baker", "Ball", "Banister", "Banks", "Banner",
				"Bannister", "Barber", "Barker", "Barnett", "Barr", "Barret", "Barrett", "Bates", "Baxter", "Bean",
				"Beaumont", "Beck", "Beckett", "Bell", "Bennett", "Bennington", "Benson", "Bentley", "Bird", "Bishop",
				"Black", "Blackburn", "Blackwood", "Blake", "Blythe", "Bolton", "Bond", "Bonner", "Booth", "Botterill",
				"Bourne", "Bowman", "Boyce", "Bradford", "Bradley", "Brant", "Braxton", "Breckinridge", "Brewster",
				"Bristow", "Brook", "Brooks", "Brown", "Bryant", "Bryson", "Burke", "Burns", "Butcher", "Butler",
				"Caldwell", "Carlyle", "Carpenter", "Carter", "Cartwright", "Carver", "Caufield", "Chambers",
				"Chandler", "Chapman", "Clark", "Clarke", "Clay", "Clement", "Clifford", "Clifton", "Cobb", "Coburn",
				"Cole", "Coleman", "Collingwood", "Collins", "Colton", "Cook", "Cooke", "Cooper", "Corwin", "Cotterill",
				"Cox", "Crawford", "Cross", "Cullen", "Daniels", "Davidson", "Davis", "Day", "Dickinson", "Dixon",
				"Draper", "Duke", "Dunn", "Durant", "Edison", "Elliott", "Ellington", "Ellis", "Ericson", "Fairchild",
				"Farnham", "Faulkner", "Faulk", "Fiddler", "Fisher", "Fletcher", "Ford", "Forrest", "Foster", "Folwer",
				"Fox", "Frost", "Gardiner", "Gardener", "Gardner", "Garner", "Garnett", "Gibson", "Gilbert", "Glass",
				"Goode", "Goodman", "Goodwin", "Grant", "Gray", "Green", "Graves", "Greene", "Grey", "Haines", "Haynes",
				"Hall", "Hamilton", "Hammond", "Hampton", "Hanley", "Harden", "Hrading", "Harley", "Harper", "Harris",
				"Harrison", "Hart", "Hathaway", "Hawkins", "Hayes", "Heath", "Henderson", "Hewitt", "Hext", "Hibbert",
				"Hightower", "Hilton", "Hobbs", "Hodges", "Hodson", "Holland", "Holt", "Honeycutt", "Hopkins", "Horn",
				"Huddleston", "Hudson", "Hughes", "Hull", "Hunt", "Hutchinson", "Ingram", "Irvin", "Irving", "Irwin",
				"Jackman", "Jackson", "Jarvis", "Jefferson", "Jenkins", "Jennings", "Johnson", "Johns", "Jones",
				"Jordan", "Joseph", "King", "Kingston", "Knight", "Larson", "Lawrence", "Lee", "Leighton", "Lewis",
				"Lindsay", "Linwood", "Little", "Lockwood", "Long", "Lowell", "Lowry", "Marshall", "Masterson",
				"Matthews", "May", "Merrill", "Merritt", "Miller", "Mills", "Milton", "Mitchell", "Montague", "Moore",
				"Morris", "Myers", "Nash", "Neal", "Neil", "Nelson", "Ness", "Newman", "Newton", "Nichols", "Norris",
				"Norton", "Norwood", "Park", "Parker", "Parks", "Parsons", "Patrick", "Patterson", "Patton", "Pearce",
				"Pearson", "Peck", "Penn", "Perry", "Peters", "Phillips", "Poole", "Porter", "Potter", "Pottinger",
				"Pratt", "Prescott", "Preston", "Quick", "Quincy", "Quicney", "Radcliffe", "Ramsey", "Randall",
				"Richards", "Richardson", "Rivers", "Roberts", "Robertson", "Rodgers", "Rogers", "Rollins", "Rose",
				"Ross", "Roy", "Russell", "Ryder", "Samuel", "Samuelson", "Sanders", "Sanderson", "Sanford", "Savage",
				"Scott", "Scrivens", "Seabrook", "Sessions", "Sharp", "Shaw", "Simmonds", "Simmons", "Simon", "Simpson",
				"Skinner", "Slater", "Smith", "Snyder", "Spalding", "Sparks", "Spear", "Spence", "Spencer", "Spooner",
				"Stack", "Stafford", "Stanford", "Stanley", "Stark", "Steele", "Stephens", "Stephenson", "Stone",
				"Sutton", "Swift", "Tanner", "Tatum", "Taylor", "Tennyson", "Tennison", "Terry", "Thatcher", "Thomas",
				"Thompson", "Thomson", "Thorburn", "Thorn", "Thorne", "Thornton", "Townsend", "Trask", "Tucker",
				"Turner", "Underwood", "Vernon", "Wade", "Walker", "Warner", "Warren", "Washington", "Waters", "Watson",
				"Watkins", "Watts", "Webb", "Wells", "Westbrook", "Wheeler", "White", "Whittemore", "Wickham", "Wilcox",
				"Wilkins", "Wilkinson", "Williams", "Williamson", "Wolf", "Wolfe", "Woodward", "Wright", "York" };

		String frenchLast[] = { "Abel", "Archambault", "Arnaud", "Babineaux", "Barre", "Beauchene", "Beaufort",
				"Belanger", "Bellamy", "Bernard", "Blaise", "Blanc", "Blanchard", "Bouchard", "Boucher", "Chauvin",
				"Clement", "Cloutier", "Comtois", "Courtemanche", "Delacroix", "Deschamps", "Descoteaux", "Desjardins",
				"Desroches", "Desrosiers", "Droit", "Dubois", "Duchamps", "Dumont", "Dupont", "Durand", "Faucher",
				"Faucheux", "Fontaine", "Gagner", "Garcon", "Garnier", "Gauthier", "Gerard", "Geroux", "Girard",
				"Giroux", "Guillaume", "Hebert", "Labelle", "Lachance", "Lachapelle", "Lacroix", "Lapointe", "Lavoie",
				"Leblanc", "Lebrun", "Leclair", "Leclerc", "Lecuyer", "Lefevre", "Lemieux", "Leon", "Levesque",
				"Marchand", "Martel", "Martin", "Pelletier", "Perreault", "Petit", "Poirier", "Richard", "Roussel",
				"Rousseau", "Severin", "Simon", "Soucy", "St. Martin", "St. Pierre", "Trudeau", "Villeneuve" };

		String usaLast[] = { "Abbey", "Abbott", "Abrams", "Acker", "Ackerman", "Adams", "Addison", "Adkins", "Aiken",
				"Ainsworth", "Aitken", "Akerman", "Akers", "Albinson", "Alden", "Alexander", "Allan", "Allard", "Allen",
				"Allison", "Anderson", "Andrews", "Andrewson", "Appleton", "Archer", "Arkwright", "Armistead",
				"Armstrong", "Arrington", "Ashwroth", "Atkinson", "Atkinson", "Attwater", "Attwood", "Audley", "Avery",
				"Ayers", "Ayres", "Badcock", "Bagley", "Bailey", "Baker", "Ball", "Banister", "Banks", "Banner",
				"Bannister", "Barber", "Barker", "Barnett", "Barr", "Barret", "Barrett", "Bates", "Baxter", "Bean",
				"Beaumont", "Beck", "Beckett", "Bell", "Bennett", "Bennington", "Benson", "Bentley", "Bird", "Bishop",
				"Black", "Blackburn", "Blackwood", "Blake", "Blythe", "Bolton", "Bond", "Bonner", "Booth", "Botterill",
				"Bourne", "Bowman", "Boyce", "Bradford", "Bradley", "Brant", "Braxton", "Breckinridge", "Brewster",
				"Bristow", "Brook", "Brooks", "Brown", "Bryant", "Bryson", "Burke", "Burns", "Butcher", "Butler",
				"Caldwell", "Carlyle", "Carpenter", "Carter", "Cartwright", "Carver", "Caufield", "Chambers",
				"Chandler", "Chapman", "Clark", "Clarke", "Clay", "Clement", "Clifford", "Clifton", "Cobb", "Coburn",
				"Cole", "Coleman", "Collingwood", "Collins", "Colton", "Cook", "Cooke", "Cooper", "Corwin", "Cotterill",
				"Cox", "Crawford", "Cross", "Cullen", "Daniels", "Davidson", "Davis", "Day", "Dickinson", "Dixon",
				"Draper", "Duke", "Dunn", "Durant", "Edison", "Elliott", "Ellington", "Ellis", "Ericson", "Fairchild",
				"Farnham", "Faulkner", "Faulk", "Fiddler", "Fisher", "Fletcher", "Ford", "Forrest", "Foster", "Folwer",
				"Fox", "Frost", "Gardiner", "Gardener", "Gardner", "Garner", "Garnett", "Gibson", "Gilbert", "Glass",
				"Goode", "Goodman", "Goodwin", "Grant", "Gray", "Green", "Graves", "Greene", "Grey", "Haines", "Haynes",
				"Hall", "Hamilton", "Hammond", "Hampton", "Hanley", "Harden", "Hrading", "Harley", "Harper", "Harris",
				"Harrison", "Hart", "Hathaway", "Hawkins", "Hayes", "Heath", "Henderson", "Hewitt", "Hext", "Hibbert",
				"Hightower", "Hilton", "Hobbs", "Hodges", "Hodson", "Holland", "Holt", "Honeycutt", "Hopkins", "Horn",
				"Huddleston", "Hudson", "Hughes", "Hull", "Hunt", "Hutchinson", "Ingram", "Irvin", "Irving", "Irwin",
				"Jackman", "Jackson", "Jarvis", "Jefferson", "Jenkins", "Jennings", "Johnson", "Johns", "Jones",
				"Jordan", "Joseph", "King", "Kingston", "Knight", "Larson", "Lawrence", "Lee", "Leighton", "Lewis",
				"Lindsay", "Linwood", "Little", "Lockwood", "Long", "Lowell", "Lowry", "Marshall", "Masterson",
				"Matthews", "May", "Merrill", "Merritt", "Miller", "Mills", "Milton", "Mitchell", "Montague", "Moore",
				"Morris", "Myers", "Nash", "Neal", "Neil", "Nelson", "Ness", "Newman", "Newton", "Nichols", "Norris",
				"Norton", "Norwood", "Park", "Parker", "Parks", "Parsons", "Patrick", "Patterson", "Patton", "Pearce",
				"Pearson", "Peck", "Penn", "Perry", "Peters", "Phillips", "Poole", "Porter", "Potter", "Pottinger",
				"Pratt", "Prescott", "Preston", "Quick", "Quincy", "Quincey", "Radcliffe", "Ramsey", "Randall",
				"Richards", "Richardson", "Rivers", "Roberts", "Robertson", "Rodgers", "Rogers", "Rollins", "Rose",
				"Ross", "Roy", "Russell", "Ryder", "Samuel", "Samuelson", "Sanders", "Sanderson", "Sanford", "Savage",
				"Scott", "Scrivens", "Seabrook", "Sessions", "Sharp", "Shaw", "Simmonds", "Simmons", "Simon", "Simpson",
				"Skinner", "Slater", "Smith", "Snyder", "Spalding", "Sparks", "Spear", "Spence", "Spencer", "Spooner",
				"Stack", "Stafford", "Stanford", "Stanley", "Stark", "Steele", "Stephens", "Stephenson", "Stone",
				"Sutton", "Swift", "Tanner", "Tatum", "Taylor", "Tennyson", "Tennison", "Terry", "Thatcher", "Thomas",
				"Thompson", "Thomson", "Thorburn", "Thorn", "Thorne", "Thornton", "Townsend", "Trask", "Tucker",
				"Turner", "Underwood", "Vernon", "Wade", "Walker", "Warner", "Warren", "Washington", "Waters", "Watson",
				"Watkins", "Watts", "Webb", "Wells", "Westbrook", "Wheeler", "White", "Whittemore", "Wickham", "Wilcox",
				"Wilkins", "Wilkinson", "Williams", "Williamson", "Wolf", "Wolfe", "Woodward", "Wright", "York" };

		String russiaLast[] = { "Aleksandrov", "Alekseev", "Andreev", "Antonov", "Bogdanov", "Bogomolov", "Borisov",
				"Chaykovsky", "Fedorov", "Filippov", "Fyodorov", "Ignatiev", "Ignatov", "Isaev", "Ivanov",
				"Konstantinov", "Kovalchuk", "Kovalev", "Kozlov", "Krupin", "Kuznetsov", "Lagunov", "Lebedev",
				"Maksimov", "Markov", "Matveev", "Mikhailov", "Morozov", "Nikolaev", "Novikov", "Orlov", "Pavlov",
				"Petrov", "Popov", "Romanov", "Smirnov", "Sobol", "Sokolov", "Sokolovsky", "Tchaikovsky", "Vasilev",
				"Vasiliev", "Vasilyev", "Viktorov", "Vinogradov", "Volkov", "Voronin", "Yakovlev", "Zima" };

		String swedenLast[] = { "Ahlberg", "Ahlgren", "Ahlstrom", "Akerman", "Andersson", "Arvidsson", "Axelsson",
				"Beck", "Bengtsson", "Berg", "Berglund", "Bergman", "Bjork", "Bjorkman", "Blom", "Blomgren", "Byquist",
				"Bystrom", "Carlsson", "Claesson", "Dahl", "Ek", "Eklund", "Elmersson", "Ericsson", "Eriksson", "Falk",
				"Forsberg", "Fredriksson", "Gunnarsson", "Gustafsson", "Gustavsson", "Hallman", "Hult", "Johansson",
				"Johnsson", "Karlsson", "Kjellsson", "Larsson", "Lindberg", "Lindbeck", "Lindgren", "Lindholm",
				"Lindstrom", "Lundqvist", "Nyberg", "Nyquist", "Nystrom", "Olofsson", "Ottosson", "Pettersson", "Rask",
				"Ramuelsson", "Sorensen" };

		String finlandLast[] = { "Aaltonen", "Hamalainen", "Harmaajarvi", "Heikkila", "Heikkinen", "Jarvinen",
				"Jokinen", "Karppinen", "Korhonen", "Koskinen", "Laaksonen", "Laakkonnen", "Lahti", "Laine",
				"Laukkanen", "Lehtinen", "Lehtonen", "Makela", "Makinen", "Martikainen", "Mustonen", "Niemi",
				"Nieminen", "Nikula", "Nurmi", "Pajari", "Partanen", "Pekkanen", "Pentti", "Ranta", "Rinne",
				"Ruostalainen", "Ristolainen", "Seppa", "Seppanen", "Tahtinen", "Takala", "Toivonen", "Tuominen",
				"Vanhanen", "Venalainen", "Virtanen" };

		String czechLast[] = { "Adamik", "Beranek", "Blazek", "Cervenka", "Verveny", "Chalupa", "Dolezal", "Doubek",
				"Dusek", "Dvorak", "Fiala", "Hajek", "Hasek", "Hlavac", "Hlavacek", "Horacek", "Horak", "Hruska",
				"Jandacek", "Janicek", "Jedlicka", "Jezek", "Kaluza", "Klement", "Klima", "Kopecky", "Krall", "Kramar",
				"Krejci", "Lanik", "Macek", "Marek", "Martinek", "Masek", "Maselnik", "Moravec", "Myska", "Nedbalek",
				"Nosek", "Novak", "Novotny", "Pokorny", "Polak", "Pololanik", "Prazak", "Ruzicka", "Simek", "Simon",
				"Slovacek", "Sokol", "Stepanek", "Strnad", "Suchy", "Svoboda", "Syorka", "Zima" };

		String denmarkLast[] = { "Adamsen", "Albertsen", "Andersen", "Andreassen", "Bager", "Berntsen", "Carlsen",
				"Christensen", "Dahl", "Dalgaard", "Frederiksen", "Guldbrandsen", "Holgersen", "Ibsen", "Jacobsen",
				"Jensen", "Johansen", "Jespersen", "Karlsen", "Jorgensen", "Madsen", "Martinsen", "Mortensen",
				"Nielsen", "Olsen", "Pedersen", "Petersen", "Rasmussen", "Sorensen", "Skovgaard", "Winther" };

		String germanyLast[] = { "Abel", "Achterberg", "Ackermann", "Adenauer", "Adler", "Armbruster", "Baasch",
				"Bachmann", "Bachmeier", "Bader", "Baumann", "Baumgartner", "Baumhauer", "Beckert", "Beitel", "Berg",
				"Bergmann", "Blumenthal", "Boehler", "Bosch", "Brandt", "Braun", "Bretz", "Brotz", "Diefenbach",
				"Dreschner", "Falk", "Fischer", "Foerstner", "Fleischer", "Frost", "Gerber", "Gerhardt", "Haas",
				"Hildebrand", "Hintzen", "Hirsch", "Hoffmann", "Hofmann", "Holtz", "Holzer", "Jager", "Keller", "Kempf",
				"Klein", "Kohl", "Kruger", "Leitzke", "Meier", "Muller", "Neumann", "Nussbaum", "Richter", "Rosenfeld",
				"Salzwedel", "Schlosser", "Schmidt", "Schmitz", "Schmid", "Schmitt", "Schottmer", "Schroter",
				"Schuhmacher", "Schwartz", "Simon", "Spitz", "Steen", "Vogt", "Voss", "Warner", "Weber", "Wolff",
				"Ziegler" };

		String latviaLast[] = { "Abele", "Abers", "Bumba", "Dumpe", "Gaida", "Karklins", "Klavins", "Lacis", "Lusis",
				"Osis", "Plume", "Romans", "Rudzitis", "Selesnick", "Selznick", "Zelson", "Zalisnock", "Vanags",
				"Vitols", "Zakis" };

		String slovakiaLast[] = { "Banik", "Bosko", "Cizik", "Hornik", "Jankovic", "Klement", "Kovac", "Kral", "Marek",
				"Novak", "Pokorny", "Reznik", "Simon", "Sykora", "Zima" };

		String sloveniaLast[] = { "Banik", "Bosko", "Cizik", "Hornik", "Jankovic", "Klement", "Kovac", "Kral", "Marek",
				"Novak", "Pokorny", "Reznik", "Simon", "Sykora", "Zima" };

		String norwayLast[] = { "Adamsen", "Andersen", "Andreassen", "Berg", "Christiansen", "Dahl", "Eriksen",
				"Fredriksen", "Hansen", "Johansen", "Johannessen", "Kristiansen", "Martinsen", "Ness", "Ostberg",
				"Petersen", "Pettersen", "Rasmussen", "Ruud", "Silje", "Simonsen", "Storstrand", "Thomassen", "Thorsen",
				"Voll" };

		if (nation == "Canada") {
			if (french == 0)
				return canadaLast[(rand.nextInt(canadaLast.length))];
			else
				return frenchLast[(rand.nextInt(frenchLast.length))];
		}

		if (nation == "United States") {
			int foreignChance = rand.nextInt(100) + 1;
			if (foreignChance > 10)
				return usaLast[(rand.nextInt(usaLast.length))];
			else {
				int otherCountry = rand.nextInt(13) + 1;

				if (otherCountry == 1) {
					if (french == 0)
						return canadaLast[(rand.nextInt(canadaLast.length))];
					else
						return frenchLast[(rand.nextInt(frenchLast.length))];
				}

				if (otherCountry == 2)
					return russiaLast[(rand.nextInt(russiaLast.length))];

				if (otherCountry == 3)
					return swedenLast[(rand.nextInt(swedenLast.length))];

				if (otherCountry == 4)
					return finlandLast[(rand.nextInt(finlandLast.length))];

				if (otherCountry == 5)
					return czechLast[(rand.nextInt(czechLast.length))];

				if (otherCountry == 6)
					return denmarkLast[(rand.nextInt(denmarkLast.length))];

				if (otherCountry == 7 || otherCountry == 13)
					return germanyLast[(rand.nextInt(germanyLast.length))];

				if (otherCountry == 8)
					return latviaLast[(rand.nextInt(latviaLast.length))];

				if (otherCountry == 9)
					return slovakiaLast[(rand.nextInt(slovakiaLast.length))];

				if (otherCountry == 10) {
					if (french == 0)
						return germanyLast[(rand.nextInt(germanyLast.length))];
					else
						return frenchLast[(rand.nextInt(frenchLast.length))];
				}

				if (otherCountry == 11)
					return sloveniaLast[(rand.nextInt(sloveniaLast.length))];

				if (otherCountry == 12)
					return norwayLast[(rand.nextInt(norwayLast.length))];

				else
					return "ERROR";
			}
		}

		if (nation == "Russia" || nation == "Belarus")
			return russiaLast[(rand.nextInt(russiaLast.length))];

		if (nation == "Sweden")
			return swedenLast[(rand.nextInt(swedenLast.length))];

		if (nation == "Finland")
			return finlandLast[(rand.nextInt(finlandLast.length))];

		if (nation == "Czechia")
			return czechLast[(rand.nextInt(czechLast.length))];

		if (nation == "Denmark")
			return denmarkLast[(rand.nextInt(denmarkLast.length))];

		if (nation == "Germany" || nation == "Austria")
			return germanyLast[(rand.nextInt(germanyLast.length))];

		if (nation == "Latvia")
			return latviaLast[(rand.nextInt(latviaLast.length))];

		if (nation == "Slovakia")
			return slovakiaLast[(rand.nextInt(slovakiaLast.length))];

		if (nation == "Switzerland") {
			if (french == 0)
				return germanyLast[(rand.nextInt(germanyLast.length))];
			else
				return frenchLast[(rand.nextInt(frenchLast.length))];
		}

		if (nation == "Slovenia")
			return sloveniaLast[(rand.nextInt(sloveniaLast.length))];

		if (nation == "Norway")
			return norwayLast[(rand.nextInt(norwayLast.length))];

		else
			return "ERROR";
	}
}

class Player extends Person {
	public String goldXFactor = "NONE";
	public String silverXFactor1 = "NONE";
	public String silverXFactor2 = "NONE";
	public String silverXFactor3 = "NONE";
	public String silverXFactor4 = "NONE";
	public String silverXFactor5 = "NONE";
	public int jersey;
	public String position1;
	public String position2;
	public int overall;
	public String potential;
	public String playerType;
	public char handedness; // Just L or R
	public String height;
	public int weight;
	public int durability;
	public int discipline;
	public int offhand;
	public String junior = "None";
	public int ranking = 1;

	public Player(int x) {
		super();
		ranking = x;
		age = 18;
		handedness = generateHandedness();
		position1 = generatePosition1();
		position2 = generatePosition2();
		potential = generatePotential();
		overall = generateOverall() - 7;
		playerType = generatePlayerType();
		height = generateHeight();
		weight = generateWeight();
		durability = generateDurability();
		discipline = generateDiscipline();
		jersey = generateJerseyNumber();
		junior = generatejunior();

		Random rand = new Random();
		int chance = rand.nextInt(100) + 1;
		if (potential == "HIGH Franchise") {
			goldXFactor = generateGoldFactor(playerType);
			silverXFactor1 = generateSilverFactor(playerType);
			silverXFactor2 = generateSilverFactor(playerType);
			silverXFactor3 = generateSilverFactor(playerType);
			silverXFactor4 = generateSilverFactor(playerType);
			silverXFactor5 = generateSilverFactor(playerType);
		}
		if (potential == "MED Franchise") {
			goldXFactor = generateGoldFactor(playerType);
			silverXFactor1 = generateSilverFactor(playerType);
			silverXFactor2 = generateSilverFactor(playerType);
			if (chance <= 80)
				silverXFactor3 = generateSilverFactor(playerType);
			chance = rand.nextInt(100) + 1;
			if (chance <= 50)
				silverXFactor4 = generateSilverFactor(playerType);
		}
		if (potential == "LOW Franchise") {
			if (chance <= 40)
				goldXFactor = generateGoldFactor(playerType);
			silverXFactor1 = generateSilverFactor(playerType);
			chance = rand.nextInt(100) + 1;
			if (chance <= 30)
				silverXFactor2 = generateSilverFactor(playerType);
			chance = rand.nextInt(100) + 1;
			if (chance <= 10)
				silverXFactor3 = generateSilverFactor(playerType);
		}
		if (potential == "HIGH Elite") {
			chance = rand.nextInt(100) + 1;
			if (chance <= 65)
				goldXFactor = generateGoldFactor(playerType);
			silverXFactor1 = generateSilverFactor(playerType);
			chance = rand.nextInt(100) + 1;
			if (chance <= 80)
				silverXFactor2 = generateSilverFactor(playerType);
			chance = rand.nextInt(100) + 1;
			if (chance <= 20)
				silverXFactor3 = generateSilverFactor(playerType);
		}
		if (potential == "MED Elite") {
			chance = rand.nextInt(100) + 1;
			if (chance <= 35)
				goldXFactor = generateGoldFactor(playerType);
			silverXFactor1 = generateSilverFactor(playerType);
			chance = rand.nextInt(100) + 1;
			if (chance <= 20)
				silverXFactor2 = generateSilverFactor(playerType);
			chance = rand.nextInt(100) + 1;
			if (chance <= 5)
				silverXFactor3 = generateSilverFactor(playerType);
		}
		if (potential == "LOW Elite") {
			chance = rand.nextInt(100) + 1;
			if (chance <= 8)
				goldXFactor = generateGoldFactor(playerType);
			chance = rand.nextInt(100) + 1;
			if (chance <= 65)
				silverXFactor1 = generateSilverFactor(playerType);
			chance = rand.nextInt(100) + 1;
			if (chance <= 15)
				silverXFactor2 = generateSilverFactor(playerType);
		}
		if (potential == "HIGH Top 6 F" || potential == "HIGH Top 4 D" || potential == "HIGH Starter") {
			chance = rand.nextInt(100) + 1;
			if (chance <= 15)
				goldXFactor = generateGoldFactor(playerType);
			chance = rand.nextInt(100) + 1;
			if (chance <= 80)
				silverXFactor1 = generateSilverFactor(playerType);
			chance = rand.nextInt(100) + 1;
			if (chance <= 30)
				silverXFactor2 = generateSilverFactor(playerType);
		}
		if (potential == "MED Top 6 F" || potential == "MED Top 4 D" || potential == "MED Starter") {
			chance = rand.nextInt(100) + 1;
			if (chance <= 40)
				silverXFactor1 = generateSilverFactor(playerType);
		}
		if (potential == "LOW Top 6 F" || potential == "LOW Top 4 D" || potential == "LOW Starter") {
			chance = rand.nextInt(100) + 1;
			if (chance <= 20)
				silverXFactor1 = generateSilverFactor(playerType);
		}
		if (potential == "HIGH Top 9 F" || potential == "HIGH Top 6 D" || potential == "HIGH Fringe Starter") {
			chance = rand.nextInt(100) + 1;
			if (chance <= 10)
				silverXFactor1 = generateSilverFactor(playerType);
		}
		
		while((silverXFactor5 != "NONE") && (silverXFactor5 == goldXFactor || silverXFactor5 == silverXFactor1 || silverXFactor5 == silverXFactor2 || silverXFactor5 == silverXFactor3 || silverXFactor5 == silverXFactor4))
			silverXFactor5 = generateSilverFactor(playerType);
		
		while((silverXFactor4 != "NONE") && (silverXFactor4 == goldXFactor || silverXFactor4 == silverXFactor1 || silverXFactor4 == silverXFactor2 || silverXFactor4 == silverXFactor3))
			silverXFactor4 = generateSilverFactor(playerType);
		
		while((silverXFactor3 != "NONE") && (silverXFactor3 == goldXFactor || silverXFactor3 == silverXFactor1 || silverXFactor3 == silverXFactor2))
			silverXFactor3 = generateSilverFactor(playerType);
	
		while((silverXFactor2 != "NONE") && (silverXFactor2 == goldXFactor || silverXFactor2 == silverXFactor1))
			silverXFactor2 = generateSilverFactor(playerType);
		
		while((silverXFactor1 != "NONE") && (silverXFactor1 == goldXFactor))
			silverXFactor1 = generateSilverFactor(playerType);
	}

	public String generatejunior() {
		Random rand = new Random();
		int chance = rand.nextInt(100) + 1;

		String[] ncaaTeams = { "NCAA Boston College", "NCAA Boston University", "NCAA Denver", "NCAA Maine",
				"NCAA Michigan", "NCAA Michigan State", "NCAA Minnesota", "NCAA Minnesota State",
				"NCAA Minnesota-Duluth", "NCAA North Dakota", "NCAA Northeastern", "NCAA Notre Dame", "NCAA Ohio State",
				"NCAA Penn State", "NCAA Providence", "NCAA Quinnipiac", "NCAA St. Cloud State", "NCAA UMass",
				"NCAA Western Michigan", "NCAA Wisconsin" };

		String[] chlTeams = { "OHL Barrie Colts", "OHL Erie Otters", "OHL Flint Firebirds", "OHL Guelph Storm",
				"OHL Hamilton Bulldogs", "OHL Kingston Frontenacs", "OHL Kitchener Rangers", "OHL London Knights",
				"OHL Mississauga Steelheads", "OHL Niagara IceDogs", "OHL North Bay Battalion", "OHL Oshawa Generals",
				"OHL Ottawa 67's", "OHL Owen Sound Attack", "OHL Peterborough Petes", "OHL Saginaw Spirit",
				"OHL Sarnia Sting", "OHL Sault Ste. Marie Greyhounds", "OHL Sudbury Wolves", "OHL Windsor Spitfires",
				"QMJHL Acadie-Bathurst Titan", "QMJHL Baie-Comeau Drakkar", "QMJHL Blainville-Boisbriand Armada",
				"QMJHL Cape Breton Eagles", "QMJHL Charlottetown Islanders", "QMJHL Chicoutimi Sagueneens",
				"QMJHL Drummondville Voltigeurs", "QMJHL Gatineau Olympiques", "QMJHL Halifax Mooseheads",
				"QMJHL Moncton Wildcats", "QMJHL Quebec Remparts", "QMJHL Rimouski Oceanic",
				"QMJHL Rouyn-Noranda Huskies", "QMJHL Saint John Sea Dogs", "QMJHL Shawinigan Cataractes",
				"QMJHL Sherbrooke Phoenix", "QMJHL Val-d'Or Foreurs", "QMJHL Victoriaville Tigres",
				"WHL Brandon Wheat Kings", "WHL Calgary Hitmen", "WHL Edmonton Oil Kings", "WHL Everett Silvertips",
				"WHL Kamloops Blazers", "WHL Kelowna Rockets", "WHL Lethbridge Hurricanes", "WHL Medicine Hat Tigers",
				"WHL Moose Jaw Warriors", "WHL Portland Winterhawks", "WHL Prince Albert Raiders",
				"WHL Prince George Cougars", "WHL Regina Pats", "WHL Red Deer Rebels", "WHL Saskatoon Blades",
				"WHL Seattle Thunderbirds", "WHL Spokane Chiefs", "WHL Swift Current Broncos", "WHL Tri-City Americans",
				"WHL Vancouver Giants", "WHL Victoria Royals", "WHL Winnipeg Ice" };

		String[] khlTeams = { "KHL Jokerit Helsinki", "KHL SKA Saint Petersburg", "KHL HC Sochi", "KHL Spartak Moscow",
				"KHL Torpedo Nizhny Novgorod", "KHL HC Vityaz", "KHL CSKA Moscow", "KHL Dinamo Minsk",
				"KHL Dinamo Riga", "KHL Dynamo Moscow", "KHL Lokomotiv Yaroslavl", "KHL Severstal Cherepovets",
				"KHL Ak Bars Kazan", "KHL Avtomobilist Yekaterinburg", "KHL Red Star Kunlun",
				"KHL Metallurg Magnitogorsk", "KHL Neftekhimik Nizhnekamsk", "KHL Traktor Chelyabinsk",
				"KHL Admiral Vladivostok", "KHL Amur Khabarovsk", "KHL Avangard Omsk", "KHL Barys Nur-Sultan",
				"KHL Salavat Yulaev Ufa", "KHL Sibir Novosibirsk" };

		String[] shlTeams = { "SHL Brynas IF", "SHL Djurgardens IF", "SHL Frolunda HC", "SHL Farjestad BK", "SHL HV71",
				"SHL Leksands IF", "SHL Linkoping HC", "SHL Lulea HF", "SHL Malmo Redhawks", "SHL IK Oskarshamn",
				"SHL Rogle BK", "SHL Skelleftea AIK", "SHL Vaxjo Lakers", "SHL Orebro HK" };

		String[] liigaTeams = { "Liiga Helsinki HIFK", "Liiga Hameenlinna HPK", "Liiga Tampere Ilves",
				"Liiga Mikkeli Jukurit", "Liiga Jyvaskyla JYP", "Liiga Kuopio Kalpa", "Liiga Kouvola KooKoo",
				"Liiga Oulu Karpat", "Liiga Rauma Lukko", "Liiga Lahti Pelicans", "Liiga Lappeenranta SaiPa",
				"Liiga Vaasa Sport", "Liiga Tampere Tappara", "Liiga Turku TPS", "Liiga Porin Assat" };

		String[] elhTeams = { "ELH Kometa Brno", "ELH HC Olomouc", "ELH Mountfield HK", "ELH Energie Karlovy Vary",
				"ELH BK Mlada Boleslav", "ELH Bili Tygri Liberec", "ELH Verva Litvinov", "ELH Dynamo Pardubice",
				"ELH HC Skoda Plzen", "ELH CEZ Motor Ceske Budejovice", "ELH Sparta Praha", "ELH Ocelari Trinec",
				"ELH Vitkovice Ridera", "ELH PSG Berani Zlin", "ELH Rytiri Kladno" };

		String[] delTeams = { "DEL Augsburger Panther", "DEL Eisbaren Berlin", "DEL Fischtown Penguins",
				"DEL Dusseldorfer EG", "DEL ERC Ingolstadt", "DEL Iserlohn Roosters", "DEL Kolner Haie",
				"DEL Krefeld Pinguine", "DEL Adler Mannheim", "DEL EHC Red Bull Munchen", "DEL Nurnberg Ice Tigers",
				"DEL Schwenninger Wild Wings", "DEL Straubing Tigers", "DEL Grizzlys Wolfsburg" };

		String[] nlTeams = { "NL HC Ajoie", "NL HC Ambri-Piotta", "NL SC Bern", "NL EHC Biel", "NL HC Davos",
				"NL Fribourg-Gotteron", "NL Geneve-Servette HC", "NL Lausanne HC", "NL HC Lugano",
				"NL SC Rapperswil-Jona Lakers", "NL SCL Tigers", "NL ZSC Lions", "NL EV Zug" };

		if (nation == "Canada") {
			if (chance <= 10)
				return ncaaTeams[rand.nextInt(ncaaTeams.length)];
			if (chance > 10)
				return chlTeams[rand.nextInt(chlTeams.length)];
			else
				return "ERROR";
		}

		if (nation == "United States") {
			if (chance <= 20)
				return chlTeams[rand.nextInt(chlTeams.length)];
			if (chance > 20)
				return ncaaTeams[rand.nextInt(ncaaTeams.length)];
			else
				return "ERROR";
		}

		if (nation == "Russia" || nation == "Belarus") {
			if (chance <= 40)
				return khlTeams[rand.nextInt(khlTeams.length)];
			if (chance > 40 && chance <= 90)
				return chlTeams[rand.nextInt(chlTeams.length)];
			if (chance > 90)
				return ncaaTeams[rand.nextInt(ncaaTeams.length)];
			else
				return "ERROR";
		}

		if (nation == "Sweden" || nation == "Norway") {
			if (chance <= 80)
				return shlTeams[rand.nextInt(shlTeams.length)];
			if (chance > 80 && chance <= 95)
				return chlTeams[rand.nextInt(chlTeams.length)];
			if (chance > 95)
				return ncaaTeams[rand.nextInt(ncaaTeams.length)];
		}

		if (nation == "Finland" || nation == "Latvia") {
			if (chance <= 80)
				return liigaTeams[rand.nextInt(liigaTeams.length)];
			if (chance > 80 && chance <= 95)
				return chlTeams[rand.nextInt(chlTeams.length)];
			if (chance > 95)
				return ncaaTeams[rand.nextInt(ncaaTeams.length)];
		}

		if (nation == "Czechia" || nation == "Slovakia" || nation == "Slovenia") {
			if (chance <= 80)
				return elhTeams[rand.nextInt(elhTeams.length)];
			if (chance > 80 && chance <= 95)
				return chlTeams[rand.nextInt(chlTeams.length)];
			if (chance > 95)
				return ncaaTeams[rand.nextInt(ncaaTeams.length)];
		}

		if (nation == "Germany" || nation == "Austria" || nation == "Denmark") {
			if (chance <= 80)
				return delTeams[rand.nextInt(delTeams.length)];
			if (chance > 80 && chance <= 95)
				return chlTeams[rand.nextInt(chlTeams.length)];
			if (chance > 95)
				return ncaaTeams[rand.nextInt(ncaaTeams.length)];
		}

		if (nation == "Switzerland") {
			if (chance <= 80)
				return nlTeams[rand.nextInt(nlTeams.length)];
			if (chance > 80 && chance <= 95)
				return chlTeams[rand.nextInt(chlTeams.length)];
			if (chance > 95)
				return ncaaTeams[rand.nextInt(ncaaTeams.length)];
		}
		return "ERROR";
	}

	public char generateHandedness() {
		Random rand = new Random();
		int handedness = rand.nextInt(2);
		if (handedness == 0)
			return 'L';
		if (handedness == 1)
			return 'R';
		else
			return 'X';
	}

	public int prefersOffhand() {
		Random rand = new Random();
		int offhand = rand.nextInt(10) + 1;
		if (nation == "Russia") {
			if (offhand <= 3)
				return 1;
			else
				return 0;
		} else {
			if (offhand == 1)
				return 1;
			else
				return 0;
		}
	}

	public String generatePosition1() {
		Random rand = new Random();
		int pos = rand.nextInt(100) + 1;
		if (pos <= 4)
			return "G";
		if (pos > 4 && pos <= 33) {
			if (offhand == 0 && handedness == 'L')
				return "LD";
			if (offhand == 1 && handedness == 'L')
				return "RD";
			if (offhand == 0 && handedness == 'R')
				return "RD";
			if (offhand == 1 && handedness == 'R')
				return "LD";
			else
				return "ERROR";
		}
		if (pos > 33 && pos <= 63)
			return "C";
		if (pos > 63) {
			if (offhand == 0 && handedness == 'L')
				return "LW";
			if (offhand == 1 && handedness == 'L')
				return "RW";
			if (offhand == 0 && handedness == 'R')
				return "RW";
			if (offhand == 1 && handedness == 'R')
				return "LW";
			else
				return "ERROR";
		} else
			return "ERROR";
	}

	public String generatePosition2() {
		if (position1 == "G")
			return "None";
		else {
			Random rand = new Random();
			int chance = rand.nextInt(100) + 1;
			if (position1 == "C") {
				if (chance <= 20) {
					if (handedness == 'L')
						return "RW";
					if (handedness == 'R')
						return "LW";
				}
				if (chance >= 21 && chance <= 53)
					return "None";
				if (chance >= 54) {
					if (handedness == 'L')
						return "LW";
					if (handedness == 'R')
						return "RW";
				}
			}
			if (position1 == "LW") {
				if (chance <= 20)
					return "C";
				if (chance >= 21 && chance <= 60)
					return "RW";
				if (chance >= 61)
					return "None";
			}
			if (position1 == "RW") {
				if (chance <= 20)
					return "C";
				if (chance >= 21 && chance <= 60)
					return "LW";
				if (chance >= 61)
					return "None";
			}
			if (position1 == "LD") {
				if (chance <= 40)
					return "RD";
				else
					return "None";
			}
			if (position1 == "RD") {
				if (chance <= 40)
					return "LD";
				else
					return "None";
			} else
				return "ERROR";
		}
	}

	public String generatePotential() {
		Random rand = new Random();
		int potChance = (1 + (rand.nextInt(1000)));
		if (ranking == 1) {
			if (potChance <= 50)
				return "HIGH Franchise";
			if (potChance >= 51 && potChance <= 150)
				return "MED Franchise";
			if (potChance >= 151 && potChance <= 300)
				return "LOW Franchise";
			if (potChance >= 301 && potChance <= 850)
				return "HIGH Elite";
			if (potChance > 850)
				return "MED Elite";
		}
		if (ranking >= 2 && ranking <= 5) {
			if (potChance <= 50)
				return "LOW Franchise";
			if (potChance >= 51 && potChance <= 150)
				return "HIGH Elite";
			if (potChance >= 151 && potChance <= 500)
				return "MED Elite";
			if (potChance >= 501 && potChance <= 700)
				return "LOW Elite";
			if (potChance >= 701 && potChance <= 850) {
				if (position1 == "LW" || position1 == "C" || position1 == "RW")
					return "HIGH Top 6 F";
				if (position1 == "LD" || position1 == "RD")
					return "HIGH Top 4 D";
				if (position1 == "G")
					return "HIGH Starter";
			}
			if (potChance >= 851 && potChance <= 950) {
				if (position1 == "LW" || position1 == "C" || position1 == "RW")
					return "MED Top 6 F";
				if (position1 == "LD" || position1 == "RD")
					return "MED Top 4 D";
				if (position1 == "G")
					return "MED Starter";
			}
			if (potChance > 950) {
				if (position1 == "LW" || position1 == "C" || position1 == "RW")
					return "LOW Top 6 F";
				if (position1 == "LD" || position1 == "RD")
					return "LOW Top 4 D";
				if (position1 == "G")
					return "LOW Starter";
			}
		}
		if (ranking >= 6 && ranking <= 15) {
			if (potChance <= 50)
				return "MED Elite";
			if (potChance >= 51 && potChance <= 200)
				return "LOW Elite";
			if (potChance >= 201 && potChance <= 550) {
				if (position1 == "LW" || position1 == "C" || position1 == "RW")
					return "HIGH Top 6 F";
				if (position1 == "LD" || position1 == "RD")
					return "HIGH Top 4 D";
				if (position1 == "G")
					return "HIGH Starter";
			}
			if (potChance >= 551 && potChance <= 850) {
				if (position1 == "LW" || position1 == "C" || position1 == "RW")
					return "MED Top 6 F";
				if (position1 == "LD" || position1 == "RD")
					return "MED Top 4 D";
				if (position1 == "G")
					return "MED Starter";
			}
			if (potChance > 850) {
				if (position1 == "LW" || position1 == "C" || position1 == "RW")
					return "LOW Top 6 F";
				if (position1 == "LD" || position1 == "RD")
					return "LOW Top 4 D";
				if (position1 == "G")
					return "LOW Starter";
			}
		}
		if (ranking >= 16 && ranking <= 31) {
			if (potChance <= 20)
				return "MED Elite";
			if (potChance >= 21 && potChance <= 50)
				return "LOW Elite";
			if (potChance >= 51 && potChance <= 100) {
				if (position1 == "LW" || position1 == "C" || position1 == "RW")
					return "HIGH Top 6 F";
				if (position1 == "LD" || position1 == "RD")
					return "HIGH Top 4 D";
				if (position1 == "G")
					return "HIGH Starter";
			}
			if (potChance >= 101 && potChance <= 450) {
				if (position1 == "LW" || position1 == "C" || position1 == "RW")
					return "MED Top 6 F";
				if (position1 == "LD" || position1 == "RD")
					return "MED Top 4 D";
				if (position1 == "G")
					return "MED Starter";
			}
			if (potChance >= 451 && potChance <= 700) {
				if (position1 == "LW" || position1 == "C" || position1 == "RW")
					return "LOW Top 6 F";
				if (position1 == "LD" || position1 == "RD")
					return "LOW Top 4 D";
				if (position1 == "G")
					return "LOW Starter";
			}
			if (potChance >= 701 && potChance <= 900) {
				if (position1 == "LW" || position1 == "C" || position1 == "RW")
					return "HIGH Top 9 F";
				if (position1 == "LD" || position1 == "RD")
					return "HIGH Top 6 D";
				if (position1 == "G")
					return "HIGH Fringe Starter";
			}
			if (potChance > 900) {
				if (position1 == "LW" || position1 == "C" || position1 == "RW")
					return "MED Top 9 F";
				if (position1 == "LD" || position1 == "RD")
					return "MED Top 6 D";
				if (position1 == "G")
					return "MED Fringe Starter";
			}
		}
		if (ranking >= 32 && ranking <= 47) {
			if (potChance <= 1)
				return "MED Elite";
			if (potChance >= 2 && potChance <= 6)
				return "LOW Elite";
			if (potChance >= 7 && potChance <= 40) {
				if (position1 == "LW" || position1 == "C" || position1 == "RW")
					return "HIGH Top 6 F";
				if (position1 == "LD" || position1 == "RD")
					return "HIGH Top 4 D";
				if (position1 == "G")
					return "HIGH Starter";
			}
			if (potChance >= 41 && potChance <= 200) {
				if (position1 == "LW" || position1 == "C" || position1 == "RW")
					return "MED Top 6 F";
				if (position1 == "LD" || position1 == "RD")
					return "MED Top 4 D";
				if (position1 == "G")
					return "MED Starter";
			}
			if (potChance >= 201 && potChance <= 500) {
				if (position1 == "LW" || position1 == "C" || position1 == "RW")
					return "LOW Top 6 F";
				if (position1 == "LD" || position1 == "RD")
					return "LOW Top 4 D";
				if (position1 == "G")
					return "LOW Starter";
			}
			if (potChance >= 501 && potChance <= 800) {
				if (position1 == "LW" || position1 == "C" || position1 == "RW")
					return "HIGH Top 9 F";
				if (position1 == "LD" || position1 == "RD")
					return "HIGH Top 6 D";
				if (position1 == "G")
					return "HIGH Fringe Starter";
			}
			if (potChance >= 801 && potChance <= 950) {
				if (position1 == "LW" || position1 == "C" || position1 == "RW")
					return "MED Top 9 F";
				if (position1 == "LD" || position1 == "RD")
					return "MED Top 6 D";
				if (position1 == "G")
					return "MED Fringe Starter";
			}
			if (potChance > 950) {
				if (position1 == "LW" || position1 == "C" || position1 == "RW")
					return "LOW Top 9 F";
				if (position1 == "LD" || position1 == "RD")
					return "LOW Top 6 D";
				if (position1 == "G")
					return "LOW Fringe Starter";
			}
		}
		if (ranking >= 48 && ranking <= 62) {
			if (potChance == 1)
				return "LOW Elite";
			if (potChance >= 2 && potChance <= 11) {
				if (position1 == "LW" || position1 == "C" || position1 == "RW")
					return "HIGH Top 6 F";
				if (position1 == "LD" || position1 == "RD")
					return "HIGH Top 4 D";
				if (position1 == "G")
					return "HIGH Starter";
			}
			if (potChance >= 12 && potChance <= 41) {
				if (position1 == "LW" || position1 == "C" || position1 == "RW")
					return "MED Top 6 F";
				if (position1 == "LD" || position1 == "RD")
					return "MED Top 4 D";
				if (position1 == "G")
					return "MED Starter";
			}
			if (potChance >= 42 && potChance <= 150) {
				if (position1 == "LW" || position1 == "C" || position1 == "RW")
					return "LOW Top 6 F";
				if (position1 == "LD" || position1 == "RD")
					return "LOW Top 4 D";
				if (position1 == "G")
					return "LOW Starter";
			}
			if (potChance >= 151 && potChance <= 300) {
				if (position1 == "LW" || position1 == "C" || position1 == "RW")
					return "HIGH Top 9 F";
				if (position1 == "LD" || position1 == "RD")
					return "HIGH Top 6 D";
				if (position1 == "G")
					return "HIGH Fringe Starter";
			}
			if (potChance >= 301 && potChance <= 550) {
				if (position1 == "LW" || position1 == "C" || position1 == "RW")
					return "MED Top 9 F";
				if (position1 == "LD" || position1 == "RD")
					return "MED Top 6 D";
				if (position1 == "G")
					return "MED Fringe Starter";
			}
			if (potChance >= 551 && potChance <= 700) {
				if (position1 == "LW" || position1 == "C" || position1 == "RW")
					return "LOW Top 9 F";
				if (position1 == "LD" || position1 == "RD")
					return "LOW Top 6 D";
				if (position1 == "G")
					return "LOW Fringe Starter";
			}
			if (potChance >= 701 && potChance <= 800) {
				if (position1 == "LW" || position1 == "C" || position1 == "RW")
					return "HIGH Bottom 6 F";
				if (position1 == "LD" || position1 == "RD")
					return "HIGH 7th D";
				if (position1 == "G")
					return "HIGH Backup";
			}
			if (potChance >= 801 && potChance <= 900) {
				if (position1 == "LW" || position1 == "C" || position1 == "RW")
					return "MED Bottom 6 F";
				if (position1 == "LD" || position1 == "RD")
					return "MED 7th D";
				if (position1 == "G")
					return "MED Backup";
			}
			if (potChance >= 901 && potChance <= 950) {
				if (position1 == "LW" || position1 == "C" || position1 == "RW")
					return "LOW Bottom 6 F";
				if (position1 == "LD" || position1 == "RD")
					return "LOW 7th D";
				if (position1 == "G")
					return "LOW Backup";
			}
			if (potChance > 950)
				return "AHL";
		}
		if (ranking >= 63) {
			if (potChance <= 30) {
				if (position1 == "LW" || position1 == "C" || position1 == "RW")
					return "HIGH Top 9 F";
				if (position1 == "LD" || position1 == "RD")
					return "HIGH Top 6 D";
				if (position1 == "G")
					return "HIGH Fringe Starter";
			}
			if (potChance >= 31 && potChance <= 90) {
				if (position1 == "LW" || position1 == "C" || position1 == "RW")
					return "MED Top 9 F";
				if (position1 == "LD" || position1 == "RD")
					return "MED Top 6 D";
				if (position1 == "G")
					return "MED Fringe Starter";
			}
			if (potChance >= 91 && potChance <= 210) {
				if (position1 == "LW" || position1 == "C" || position1 == "RW")
					return "LOW Top 9 F";
				if (position1 == "LD" || position1 == "RD")
					return "LOW Top 6 D";
				if (position1 == "G")
					return "LOW Fringe Starter";
			}
			if (potChance >= 211 && potChance <= 500) {
				if (position1 == "LW" || position1 == "C" || position1 == "RW")
					return "HIGH Bottom 6 F";
				if (position1 == "LD" || position1 == "RD")
					return "HIGH 7th D";
				if (position1 == "G")
					return "HIGH Backup";
			}
			if (potChance >= 501 && potChance <= 700) {
				if (position1 == "LW" || position1 == "C" || position1 == "RW")
					return "MED Bottom 6 F";
				if (position1 == "LD" || position1 == "RD")
					return "MED 7th D";
				if (position1 == "G")
					return "MED Backup";
			}
			if (potChance >= 701 && potChance <= 800) {
				if (position1 == "LW" || position1 == "C" || position1 == "RW")
					return "LOW Bottom 6 F";
				if (position1 == "LD" || position1 == "RD")
					return "LOW 7th D";
				if (position1 == "G")
					return "LOW Backup";
			}
			if (potChance > 800)
				return "AHL";
		}
		return "ERROR";
	}

	public int generateOverall() {
		Random rand = new Random();
		if (ranking == 1)
			return (79 + (rand.nextInt(5))); // 79-83
		else if (ranking >= 2 && ranking <= 5)
			return (76 + (rand.nextInt(5))); // 76-80
		else if (ranking >= 6 && ranking <= 15)
			return (75 + (rand.nextInt(4))); // 75-78
		else if (ranking >= 16 && ranking <= 31)
			return (72 + (rand.nextInt(5))); // 72-76
		else if (ranking >= 32 && ranking <= 47)
			return (70 + (rand.nextInt(6))); // 70-75
		else if (ranking >= 48 && ranking <= 62)
			return (68 + (rand.nextInt(5))); // 68-72
		else if (ranking >= 63)
			return (65 + (rand.nextInt(4))); // 65-68
		else
			return -999;
	}

	public String generatePlayerType() {
		Random rand = new Random();
		int ptn = 1 + rand.nextInt(100);
		if (position1 == "C") {
			if (ptn <= 35)
				return "TWF";
			if (ptn >= 36 && ptn <= 60)
				return "PLY";
			if (ptn >= 61 && ptn <= 77)
				return "SNP";
			if (ptn >= 78 && ptn <= 85)
				return "GRN";
			if (ptn >= 86 && ptn <= 98)
				return "PWF";
			if (ptn > 98)
				return "ENF";
		}
		if (position1 == "LW" || position1 == "RW") {
			if (ptn <= 20)
				return "TWF";
			if (ptn >= 21 && ptn <= 40)
				return "PLY";
			if (ptn >= 41 && ptn <= 65)
				return "SNP";
			if (ptn >= 66 && ptn <= 75)
				return "GRN";
			if (ptn >= 76 && ptn <= 95)
				return "PWF";
			if (ptn > 95)
				return "ENF";
		}
		if (position1 == "LD" || position1 == "RD") {
			if (ptn <= 25)
				return "OFD";
			if (ptn >= 26 && ptn <= 70)
				return "TWD";
			if (ptn >= 71 && ptn <= 95)
				return "DFD";
			if (ptn > 95)
				return "EFD";
		}
		if (position1 == "G") {
			if (ptn < 3)
				return "STD";
			if (ptn >= 4 && ptn <= 97)
				return "HYB";
			if (ptn > 97)
				return "BUT";
		}
		return "ERROR";
	}

	public String generateHeight() {
		Random rand = new Random();
		int randht = (1 + (rand.nextInt(630)));
		int height = 0;
		int add = 0;
		if (playerType == "GRN" || playerType == "PWF" || playerType == "TWF" || playerType == "DFD") {
			add = randht % 7;
			if (playerType == "GRN" || playerType == "DFD")
				height = 72 + add;
			else if (playerType == "PWF")
				height = 71 + add;
			else if (playerType == "TWF")
				height = 70 + add;
			else
				height = -999;
		} else if (playerType == "ENF" || playerType == "EFD" || playerType == "OFD") {
			add = randht % 9;
			if (playerType == "OFD")
				height = 69 + add;
			else
				height = 72 + add;
		} else {
			add = randht % 10;
			if (playerType == "SNP" || playerType == "PLY")
				height = 67 + add;
			else if (playerType == "STD" || playerType == "HYB" || playerType == "BUT" || playerType == "TWD")
				height = 69 + add;
			else
				height = -999;
		}

		int feet = height / 12;
		int inches = height % 12;
		String formattedHeight = feet + "'" + inches + "\"";
		return formattedHeight;
	}

	public int generateWeight() {
		Random rand = new Random();
		if (height.equals("5'7\""))
			return 165 + rand.nextInt(21);
		if (height.equals("5'8\""))
			return 170 + rand.nextInt(21);
		if (height.equals("5'9\""))
			return 175 + rand.nextInt(21);
		if (height.equals("5'10\""))
			return 180 + rand.nextInt(21);
		if (height.equals("5'11\""))
			return 185 + rand.nextInt(21);
		if (height.equals("6'0\""))
			return 190 + rand.nextInt(21);
		if (height.equals("6'1\""))
			return 195 + rand.nextInt(21);
		if (height.equals("6'2\""))
			return 200 + rand.nextInt(21);
		if (height.equals("6'3\""))
			return 205 + rand.nextInt(26);
		if (height.equals("6'4\""))
			return 210 + rand.nextInt(26);
		if (height.equals("6'5\""))
			return 215 + rand.nextInt(26);
		if (height.equals("6'6\""))
			return 220 + rand.nextInt(31);
		if (height.equals("6'7\""))
			return 225 + rand.nextInt(36);
		if (height.equals("6'8\""))
			return 230 + rand.nextInt(36);
		if (height.equals("6'9\""))
			return 235 + rand.nextInt(36);
		else
			return -999;
	}

	int generateDurability() {
		Random rand = new Random();
		int durchance = 1 + rand.nextInt(100);
		if (durchance <= 10)
			return 1;
		if (durchance > 10 && durchance <= 30)
			return 2;
		if (durchance > 30 && durchance <= 70)
			return 3;
		if (durchance > 70 && durchance <= 90)
			return 4;
		if (durchance > 90)
			return 5;
		else
			return -999;
	}

	int generateDiscipline() {
		Random rand = new Random();
		int dischance = 1 + rand.nextInt(100);
		if (playerType == "SNP" || playerType == "PLY" || playerType == "TWF" || playerType == "OFD"
				|| playerType == "TWD" || position1 == "G") {
			if (dischance <= 5)
				return 1;
			if (dischance >= 6 && dischance <= 20)
				return 2;
			if (dischance >= 21 && dischance <= 40)
				return 3;
			if (dischance >= 41 && dischance <= 80)
				return 4;
			if (dischance > 80)
				return 5;
			else
				return -999;
		}
		if (playerType == "ENF" || playerType == "GRN" || playerType == "PWF" || playerType == "EFD"
				|| playerType == "DFD") {
			if (dischance <= 15)
				return 1;
			if (dischance >= 16 && dischance <= 35)
				return 2;
			if (dischance >= 36 && dischance <= 65)
				return 3;
			if (dischance >= 66 && dischance <= 85)
				return 4;
			if (dischance > 85)
				return 5;
			else
				return 666;
		} else
			return 100000;
	}

	int generateJerseyNumber() {
		Random rand = new Random();
		return (1 + (rand.nextInt(98)));
	}

	public String generateSilverFactor(String playerType) {
		Random rand = new Random();
		String[] TWFfactors = { "Wheels", "Elite Edges", "In Reverse", "Ankle Breaker", "Make it Snappy", "Shnipe",
				"One Tee", "Thunder Clap", "Heatseeker", "Beauty Backhand", "Shock and Awe", "Tape to Tape", "Send It",
				"Magnetic", "Third Eye", "Yoink!", "Stick 'Em Up", "Shutdown", "Puck on a String", "Quick Pick",
				"All Alone" };
		String[] PLYfactors = { "Wheels", "Elite Edges", "In Reverse", "Ankle Breaker", "Tape to Tape", "Send It",
				"Magnetic", "Third Eye", "Puck on a String", "All Alone" };
		String[] SNPfactors = { "Wheels", "Elite Edges", "In Reverse", "Ankle Breaker", "Make it Snappy", "Shnipe",
				"One Tee", "Thunder Clap", "Heatseeker", "Beauty Backhand", "Shock and Awe", "Puck on a String",
				"All Alone", "Elite Trick Shooting" };
		String[] GRNfactors = { "Wheels", "Elite Edges", "In Reverse", "Ankle Breaker", "Yoink!", "Stick 'Em Up",
				"Shutdown", "Quick Pick" };
		String[] PWFfactors = { "Wheels", "Elite Edges", "In Reverse", "Ankle Breaker", "Make it Snappy", "Shnipe",
				"One Tee", "Thunder Clap", "Heatseeker", "Beauty Backhand", "Shock and Awe", "Yoink!", "Stick 'Em Up",
				"Shutdown", "Puck on a String", "Crease Crasher", "Big Tipper" };
		String[] ENFfactors = { "Wheels", "Elite Edges", "In Reverse", "Ankle Breaker", "Yoink!", "Stick 'Em Up",
				"Shutdown", "Crease Crasher", "Big Tipper" };
		String[] OFDTWDfactors = { "Wheels", "Elite Edges", "In Reverse", "Ankle Breaker", "Make it Snappy", "Shnipe",
				"One Tee", "Thunder Clap", "Heatseeker", "Beauty Backhand", "Shock and Awe", "Tape to Tape", "Send It",
				"Magnetic", "Third Eye", "Yoink!", "Stick 'Em Up", "Shutdown", "Quick Pick" };
		String[] DFDEFDfactors = { "Wheels", "Elite Edges", "In Reverse", "Ankle Breaker", "Yoink!", "Stick 'Em Up",
				"Shutdown", "Quick Pick" };
		String[] Gfactors = { "Contortionist", "All or Nothing", "Dialed In", "Post to Post" };

		String retval = "NULL";

		if (playerType.equals("TWF"))
			retval = TWFfactors[(rand.nextInt(TWFfactors.length))];
		if (playerType.equals("PLY"))
			retval = PLYfactors[(rand.nextInt(PLYfactors.length))];
		if (playerType.equals("SNP"))
			retval = SNPfactors[(rand.nextInt(SNPfactors.length))];
		if (playerType.equals("GRN"))
			retval = GRNfactors[(rand.nextInt(GRNfactors.length))];
		if (playerType.equals("PWF"))
			retval = PWFfactors[(rand.nextInt(PWFfactors.length))];
		if (playerType.equals("ENF"))
			retval = ENFfactors[(rand.nextInt(ENFfactors.length))];
		if (playerType.equals("OFD") || playerType.equals("TWD"))
			retval = OFDTWDfactors[(rand.nextInt(OFDTWDfactors.length))];
		if (playerType.equals("DFD") || playerType.equals("EFD"))
			retval = DFDEFDfactors[(rand.nextInt(DFDEFDfactors.length))];
		if (playerType.equals("HYB") || playerType.equals("BUT") || (playerType.equals("STD")))
			retval = Gfactors[(rand.nextInt(Gfactors.length))];

		return retval;
	}

	public String generateGoldFactor(String playerType) {
		Random rand = new Random();
		String[] TWFfactors = { "Send It", "Unstoppable Force", "No Contest", "Quick Draw", "Quick Pick",
				"Tape to Tape", "Yoink!", "Wheels", "Born Leader", "Magnetic", "Stick 'Em Up", "Shutdown", "Ice Pack",
				"In Reverse", "Elite Edges" };
		String[] PLYfactors = { "Send It", "Quick Draw", "Ankle Breaker", "Third Eye", "Tape to Tape",
				"Puck on a String", "Wheels", "Born Leader", "Magnetic", "Spin-o-Rama", "Elite Edges",
				"Beauty Backhand" };
		String[] SNPfactors = { "Heatseeker", "Seeing Eye", "Ankle Breaker", "Puck on a String", "One Tee",
				"It's Tricky", "Wheels", "Shnipe", "Born Leader", "Magnetic", "Spin-o-Rama", "Shock and Awe",
				"Make it Snappy", "Elite Edges", "Off the Rush", "Beauty Backhand", "Thunder Clap" };
		String[] GRNfactors = { "Send It", "Big Rig", "Unstoppable Force", "No Contest", "Quick Pick", "Born Leader",
				"Total Eclipse", "Big Tipper", "Close Quarters", "Magnetic", "Bouncer", "Ice Pack", "Back at Ya",
				"Elite Edges", "Truculence", "Crease Crasher" };
		String[] PWFfactors = { "Heatseeker", "Send It", "Big Rig", "No Contest", "One Tee", "Born Leader",
				"Big Tipper", "Close Quarters", "Magnetic", "Bouncer", "Back at Ya", "Elite Edges", "Truculence",
				"Crease Crasher" };
		String[] ENFfactors = { "Heatseeker", "Send It", "Big Rig", "Unstoppable Force", "No Contest", "Born Leader",
				"Total Eclipse", "Big Tipper", "Close Quarters", "Magnetic", "Bouncer", "Back at Ya", "Elite Edges",
				"Truculence", "Crease Crasher" };
		String[] OFDfactors = { "Heatseeker", "Seeing Eye", "Send It", "Unstoppable Force", "Quick Pick", "Third Eye",
				"Tape to Tape", "Yoink!", "One Tee", "Shnipe", "Born Leader", "Magnetic", "Stick 'Em Up", "Spin-o-Rama",
				"Back at Ya", "In Reverse", "Elite Edges", "Off the Rush", "Thunder Clap" };
		String[] TWDfactors = { "Send It", "Unstoppable Force", "Quick Pick", "Third Eye", "Tape to Tape", "Yoink!",
				"Born Leader", "Magnetic", "Stick 'Em Up", "Shutdown", "Back at Ya", "In Reverse", "Elite Edges",
				"Truculence", "Thunder Clap" };
		String[] DFDfactors = { "Send It", "No Contest", "Quick Pick", "Yoink!", "Born Leader", "Magnetic",
				"Stick 'Em Up", "Shutdown", "Ice Pack", "Back at Ya", "In Reverse", "Elite Edges", "Truculence" };
		String[] EFDfactors = { "Send It", "No Contest", "Quick Pick", "Yoink!", "Born Leader", "Magnetic",
				"Stick 'Em Up", "Shutdown", "Back at Ya", "In Reverse", "Elite Edges" };
		String[] BUTfactors = { "Dialed In", "Butterfly Effect", "Handled It", "Contortionist", "Last Stand",
				"Whirlwind", "Adventurer", "Sponge", "All or Nothing", "Post to Post", "X-Ray", "Elite Edges" };
		String[] Gfactors = { "Dialed In", "Handled It", "Contortionist", "Last Stand", "Whirlwind", "Adventurer",
				"Sponge", "All or Nothing", "Post to Post", "X-Ray", "Elite Edges" };

		if (playerType.equals("TWF"))
			return TWFfactors[(rand.nextInt(TWFfactors.length))];
		if (playerType.equals("PLY"))
			return PLYfactors[(rand.nextInt(PLYfactors.length))];
		if (playerType.equals("SNP"))
			return SNPfactors[(rand.nextInt(SNPfactors.length))];
		if (playerType.equals("GRN"))
			return GRNfactors[(rand.nextInt(GRNfactors.length))];
		if (playerType.equals("PWF"))
			return PWFfactors[(rand.nextInt(PWFfactors.length))];
		if (playerType.equals("ENF"))
			return ENFfactors[(rand.nextInt(ENFfactors.length))];
		if (playerType.equals("OFD"))
			return OFDfactors[(rand.nextInt(OFDfactors.length))];
		if (playerType.equals("TWD"))
			return TWDfactors[(rand.nextInt(TWDfactors.length))];
		if (playerType.equals("DFD"))
			return DFDfactors[(rand.nextInt(DFDfactors.length))];
		if (playerType.equals("EFD"))
			return EFDfactors[(rand.nextInt(EFDfactors.length))];
		if (playerType.equals("BUT"))
			return BUTfactors[(rand.nextInt(BUTfactors.length))];
		if (playerType.equals("HYB") || (playerType.equals("STD")))
			return Gfactors[(rand.nextInt(Gfactors.length))];
		else
			return "ERROR";
	}

	public void print() {
		System.out.printf("%d. %s %s, %s", ranking, firstName, lastName, position1);
		if (position2 != "None" && position2 != "ERROR")
			System.out.printf("/%s", position2);
		System.out.printf(" (%c", handedness);
		System.out.printf("H %s), %s", playerType, nation);
		if (junior != "None")
			System.out.printf(", %s", junior);
		System.out.printf("\n%s %d lbs.\n", height, weight);
		System.out.printf("OVERALL: %d\nPOTENTIAL: %s\n", overall, potential);
		System.out.printf("DURABILITY: %d\nDISCIPLINE: %d\n", durability, discipline);
		System.out.printf("PREFERRED JERSEY NUMBER: #%d\n", jersey);
		if (goldXFactor != "NONE")
			System.out.printf("SUPERSTAR ABILITY: %s\n", goldXFactor);
		if (silverXFactor1 != "NONE")
			System.out.printf("ZONE ABILITY: %s\n", silverXFactor1);
		if (silverXFactor2 != "NONE")
			System.out.printf("ZONE ABILITY: %s\n", silverXFactor2);
		if (silverXFactor3 != "NONE")
			System.out.printf("ZONE ABILITY: %s\n", silverXFactor3);
		if (silverXFactor4 != "NONE")
			System.out.printf("ZONE ABILITY: %s\n", silverXFactor4);
		if (silverXFactor5 != "NONE")
			System.out.printf("ZONE ABILITY: %s\n", silverXFactor5);
		System.out.printf("\n\n");
	}
}

class Coach extends Person {
	public String forecheck = generateForecheck();
	public String neutralZone = generateNeutralZone();
	public int trapForecheck = 3;
	public String offensivePressure = generateOffensivePressure();
	public String defensivePressure = generateDefensivePressure();
	public String defensiveStrategy = generateDefensiveStrategy();
	public String penaltyKill = generatePenaltyKill();
	public String powerPlay = generatePowerPlay();
	public int ppCarryDump = generatePpCarryDump();
	public String controlBreakout = "Strong Side Slant";
	public String powerPlayBreakout = "Five Back";
	public String quickBreakout = "Close Support";

	public Coach() {
		super();
		age = generateAge();
	}
	
	public String generateForecheck() {
		Random rand = new Random();
		String[] arr = {"Weak Side Lock", "1-2-2 Aggressive", "2-3", "3-2 Blue"};
		return arr[rand.nextInt(arr.length)];
	}
	
	public String generateNeutralZone() {
		Random rand = new Random();
		String[] arr = {"1-2-2 Red", "1-2-2 Blue", "1-3-1"};
		return arr[rand.nextInt(arr.length)];
	}
	
	public String generateOffensivePressure() {
		Random rand = new Random();
		String[] arr = {"Standard", "Aggressive"};
		return arr[rand.nextInt(arr.length)];
	}
	
	public String generateDefensivePressure() {
		Random rand = new Random();
		String[] arr = {"Contain Puck", "Normal"};
		return arr[rand.nextInt(arr.length)];
	}
	
	public String generateDefensiveStrategy() {
		Random rand = new Random();
		String[] arr = {"Collapsing", "Tight Point", "Staggered"};
		return arr[rand.nextInt(arr.length)];
	}
	
	public String generatePenaltyKill() {
		Random rand = new Random();
		String[] arr = {"Passive Box", "Large Box", "Diamond"};
		return arr[rand.nextInt(arr.length)];
	}
	
	public String generatePowerPlay() {
		Random rand = new Random();
		String[] arr = {"Overload", "Shooting", "Umbrella"};
		return arr[rand.nextInt(arr.length)];
	}
	
	public int generatePpCarryDump() {
		Random rand = new Random();
		int x = rand.nextInt(100) + 1;
		if(x <= 15)
			return 3;
		else if(x >= 16 && x <= 50)
			return 4;
		else if(x >= 51 && x <= 85)
			return 5;
		else if(x >= 86)
			return 6;
		else
			return 1000;
	}
	

	public int generateAge() {
		Random rand = new Random();
		return (35 + (rand.nextInt(26))); // Returns some number between 35 and 60 inclusive
		// I am expecting that i3SL will have a max coach age of 70, which means created
		// coaches will be able to coach for at least 10 years
	}

	public void print() {
		System.out.printf("%s %s, %d, %s\n", firstName, lastName, age, nation);
		System.out.printf("FORECHECK: %s\n", forecheck);
		System.out.printf("NEUTRAL ZONE: %s\n", neutralZone);
		System.out.printf("TRAP/FORECHECK: %s\n", trapForecheck);
		System.out.printf("OFFENSIVE PRESSURE: %s\n", offensivePressure);
		System.out.printf("DEFENSIVE PRESSURE: %s\n", defensivePressure);
		System.out.printf("DEFENSIVE STRATEGY: %s\n", defensiveStrategy);
		System.out.printf("PENALTY KILL: %s\n", penaltyKill);
		System.out.printf("POWERPLAY: %s\n", powerPlay);
		System.out.printf("PP CARRY/DUMP: %s\n", ppCarryDump);
		System.out.printf("CONTROL BREAKOUT: %s\n", controlBreakout);
		System.out.printf("POWER PLAY BREAKOUT: %s\n", powerPlayBreakout);
		System.out.printf("QUICK BREAKOUT: %s\n\n", quickBreakout);
	}
}