import java.util.*;

public class CountrySimDriver {

	public static Random rand = new Random();
	public static Scanner scan = new Scanner(System.in);

	public static String createName() {
		String name = "";
		final int length = rand.nextInt(5) + 2;
		boolean vowelStart = rand.nextBoolean();
		final boolean blendStart = rand.nextBoolean();
		final boolean blendEnd = rand.nextBoolean();
		final String[] consonants = { "b", "c", "d", "f", "g", "h", "j", "k", "l", "m", "n", "p", "qu", "r", "s", "t",
				"v", "w", "x", "y", "z", "sh", "ch", "th", "ll" };
		final String[] vowels = { "a", "e", "i", "o", "u", "y", "a", "e", "i", "o", "u", "y", "a", "e", "i", "o", "u",
				"y", "ae", "ai", "ao", "au", "ay", "ea", "ei", "eo", "eu", "ey", "ia", "ie", "io", "iu", "oa", "oe",
				"oi", "oo", "ee", "ya", "ye", "yi", "yo", "yu" };
		final String[] startConBlend = { "bh", "bl", "br", "ch", "cl", "cr", "dh", "dr", "fl", "fr", "gh", "gl", "gn",
				"gr", "kh", "kl", "kn", "kr", "mg", "pl", "pr", "ph", "pf", "sh", "sk", "sl", "sm", "sn", "sp", "squ",
				"st", "sw", "th", "tr", "ts", "tw", "wr", "wh" };
		final String[] endConBlend = { "bs", "ch", "rst", "rch", "rth", "sch", "cs", "ct", "ck", "ds", "fs", "ft", "gh",
				"gs", "hb", "hd", "hl", "hm", "kh", "ks" };

		for (int i = 0; i < length; i++) {
			if (vowelStart) {
				name += vowels[rand.nextInt(vowels.length - 1)];
			} else {
				if (i == 0 && blendStart) {
					name += startConBlend[rand.nextInt(startConBlend.length - 1)];
				} else if (i == (length - 1) && blendEnd) {
					name += endConBlend[rand.nextInt(endConBlend.length - 1)];
				} else {
					name += consonants[rand.nextInt(consonants.length - 1)];
				}
			}
			vowelStart = !vowelStart;
		}
		name = name.substring(0, 1).toUpperCase() + name.substring(1);

		return name;
	}
	
	public static boolean makeAlliance() {
		System.out.println("\nStart an alliance? (y or n)");
		String response = scan.nextLine();
		if(response.equalsIgnoreCase("y")) {
			System.out.println("\nName the alliance: ");
			String allianceName = scan.nextLine();
			new Alliance(allianceName, true);
			return true;
		} else {
			return false;
		}
	}

	public static void main(final String... args) { // using variable arguments because fancy
		
		int countryAmount = 0;
		String response;
		
		Person.createNameLists();  // grabbing all the names from the txt files and putting them in Person's static name lists
		
		System.out.println("Welcome to CountrySim\n---------------------");

		System.out.println("How many countries to generate?");
		countryAmount = Integer.parseInt(scan.nextLine());
		for (int i = 0; i < countryAmount; i++) {
			new Country(createName());
		}
		
		Country.printCountries(true);

		while (true) {
			System.out.println("\nHold an election? (y or n)");
			response = scan.nextLine();
			if (response.equalsIgnoreCase("y")) {
				Country.getCountry(0).election();
			} else {
				break;
			}
		}
		
		makeAlliance();

		Alliance.printAlliances();
		/* country.assassinateLeader() - percent chance to kill
		 * reputation
		 * discordLevel = disagreeance in country gov style with leader
		 * fallibility = percent chance that gov will fall (normally very very low)
		 * war
		 * suggest leader
		 * heirs to thrown - unique gov twists
		 * influence of gov style on leader attributes
		 * incorporate dictionary
		 * add voting and monarch tree
		 */
	}

}
