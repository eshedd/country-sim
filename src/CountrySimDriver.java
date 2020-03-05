import java.util.*;

public class CountrySimDriver {

	public static Random RANDOM = new Random();
	public static Scanner SCANNER = new Scanner(System.in);

	public static String createName() {
		String name = "";
		final int length = RANDOM.nextInt(5) + 2;
		boolean vowelStart = RANDOM.nextBoolean();
		final boolean blendStart = RANDOM.nextBoolean();
		final boolean blendEnd = RANDOM.nextBoolean();
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
				name += vowels[RANDOM.nextInt(vowels.length - 1)];
			} else {
				if (i == 0 && blendStart) {
					name += startConBlend[RANDOM.nextInt(startConBlend.length - 1)];
				} else if (i == (length - 1) && blendEnd) {
					name += endConBlend[RANDOM.nextInt(endConBlend.length - 1)];
				} else {
					name += consonants[RANDOM.nextInt(consonants.length - 1)];
				}
			}
			vowelStart = !vowelStart;
		}
		name = name.substring(0, 1).toUpperCase() + name.substring(1);

		return name;
	}

	public static boolean makeAlliance(Country player) {
		System.out.println("\nStart an alliance? (y or n)");
		String response = SCANNER.nextLine();
		if(response.equalsIgnoreCase("y")) {
			System.out.println("\nName the alliance: ");
			String allianceName = SCANNER.nextLine();
			new Alliance(allianceName, true);
			return true;
		} else {
			return false;
		}
	}

	public static boolean holdElection(Country player) {
		if(player.getGov().electionsAllowed()) {
			System.out.println("\nHold an election? (y or n)");
			String response = SCANNER.nextLine();
			if (response.equalsIgnoreCase("y")) {
				player.election();
				return true;
			} else {  // non-yes response
				return false;
			}
		} else {  // can't hold election
			return false;
		}
	}

	public static void main(final String... args) { // using variable arguments because fancy

		int countryAmount = 0;
		String response;

		Person.createNameLists();  // grabbing all the names from the txt files and putting them in Person class's static name lists

		System.out.println("Welcome to CountrySim\n---------------------");

		System.out.println("How many countries to generate?");
		countryAmount = Integer.parseInt(SCANNER.nextLine());
		for (int i = 0; i < countryAmount; i++) {
			new Country(createName());
		}

		Country.printCountries(true);

		System.out.println("Choose your country");
		Country player = Country.getCountry(0);
		boolean countrySelected = false;
		while(!countrySelected) {
			response = SCANNER.nextLine();
			for(Country country : Country.getCountries()) {
				if(response.equalsIgnoreCase(country.getName())) {
					boolean acceptOffer = RANDOM.nextBoolean();
					if(acceptOffer) {
						System.out.println(country.getName() + " accepts!\nYour " + country);
						player = country;
						countrySelected = true;
						break;
					} else {
						System.out.println(country.getName() + " refuses!");
					}
				}
			}
		}



		holdElection(player);

		makeAlliance(player);

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
