import java.util.*;

public class Alliance {
	private static final ArrayList<Alliance> alliances = new ArrayList<Alliance>();
	public final ArrayList<Country> roster;
	private String name;
	private final Scanner SCANNER = new Scanner(System.in);
	private final Random RANDOM = new Random();

	
	public Alliance(String name, boolean playerChooses) {
		this.name = name;
		roster = new ArrayList<Country>();
		alliances.add(this);
		if(playerChooses) {
			Country.printCountries(true);
			System.out.println("\nChoose countries to offer alliance (enter one name at a time; when finished, type 'done')\n");
			int refusals = 0;
			while(refusals < 3) {
				String response = SCANNER.nextLine();
				if(response.equalsIgnoreCase("done")) {
					break;
				}
				if(requestAlly(response)) {
					refusals++;
				}
			}
		}
	}
	
	public boolean requestAlly(String response) {
		boolean isMatch = false;
		for(Country country : Country.getCountries()) {
			if(response.equalsIgnoreCase(country.getName())) {
				boolean acceptOffer = RANDOM.nextBoolean();
				isMatch = true;
				if(acceptOffer) {
					System.out.println(country.getName() + " accepts");
					this.addCountry(country);
					return true;
				} else {
					System.out.println(country.getName() + " refuses");
					return false;
				}
			}
		}
		if(!isMatch) {
			System.out.println("Invalid");
		}
		return true;
	}
	
	public boolean addCountry(Country country) {
		return roster.add(country);
	}

	public boolean removeCountry(Country country) {
		return roster.remove(country);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Country> getRoster() {
		return new ArrayList<Country>(roster);  // merely a copy of the roster
	}
	// no setter for roster, must change through the provided remove and add country methods

	public static void printAlliances() {
		for (Alliance alliance : alliances) {
			System.out.println(alliance);
		}
	}

	@Override
	public String toString() {
		String list = "Alliance: " + name + "";
		for(Country country : roster) {
			list += "\n " + country.toString(true);
		}
		return list;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Alliance) {
			Alliance tempAlliance = (Alliance) obj;
			return name.equalsIgnoreCase(tempAlliance.name) &&
				roster.equals(tempAlliance.getRoster());  // pretty sure this will compare the countries of both alliances
		} else {
			return false;
		}
	}
	
}
