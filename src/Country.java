import java.util.*;

public class Country {

	public static final int VOTE_COUNT_RANGE = 100;
	public static final Random RANDOM = new Random();
	private static final ArrayList<Country> countries = new ArrayList<Country>(); // stores all countries
	private static final Government[] VALUES = Government.values();
	private static final int SIZE = VALUES.length;
	private static final Scanner SCANNER = new Scanner(System.in);
	
	private String name;
	private String style;
	private Person leader;
	private Government gov;
	private Person[] candidates;

	public Country(String name) {
		this.name = name;
		leader = new Person();
	    gov = VALUES[(RANDOM.nextInt(SIZE))];
		countries.add(this);
	}

	public static void printCountries(boolean abridge) {
		if(abridge) {
			for (final Country country : Country.countries) {
				System.out.println(country.toString(true));
			}
		} else {
			for (Country country : Country.countries) {
				System.out.println(country);
			}
		}
	}

	public static Country getCountry(int index) {
		return Country.countries.get(index);
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setGov(Government gov) {
		this.gov = gov;
	}

	public Government getGov() {
		return gov;
	}


	public void setStyle(String style) {
		this.style = style;
	}

	public String getStyle() {
		return style;
	}

	public String getLeader() {
		return leader.getName();
	}
	
	public static ArrayList<Country> getCountries() {
		return new ArrayList<Country>(countries);  // returns a copy
	}
	
	public void election() {
		if(gov.electionsAllowed()) {
			System.out.println("Enter number of candidates in election (no more than 7): ");
			int candidateAmount = Integer.parseInt(SCANNER.nextLine());
			candidates = new Person[candidateAmount];
			Person winner;
			for(int i = 0; i < candidates.length; i++) {
				candidates[i] = new Person();
			}
			winner = getElectionResults(candidates);
			System.out.println("Elected: " + winner + "\n");
		} else {
			System.out.println("The " + gov + " government-style does not permit elections.");
		}
	}

	private String translateVotePoints(int total) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < total; i++) {
			builder.append('□');
		}
		return "\t|" + builder.toString();
	}

	private Person getElectionResults(Person[] candidates) {

		String[] graphRows = {"\t|\n","\t|\n","\t|\n","\t|\n","\t|\n","\t|\n","\t|\n", "\t -------------------\n"};

		int[] votes = new int[candidates.length];
		int[] votePoints = new int[candidates.length];
		String[] placeholders = {"α","β","γ","δ","ε","ζ","η"};
		int totalVotes = 0;

		for(int i = 0; i < candidates.length; i++) {
			votes[i] = RANDOM.nextInt(VOTE_COUNT_RANGE);
			totalVotes += votes[i];
		}
		int maxVotes = votes[0];
		Person winner = candidates[0];

		for(int i = 0; i < votes.length; i++) {
			int votePercentage = (votes[i]*100)/totalVotes;
			votePoints[i] = (int)Math.round((votes[i] * 20)/totalVotes);
			graphRows[i] = placeholders[i] + "(" + votePercentage + "%)" + translateVotePoints(votePoints[i]) + "\n";
			System.out.println(placeholders[i] + " - " + candidates[i].getName() + " (" + votePercentage + "%)");

			if(votes[i] > maxVotes) {
				maxVotes = votes[i];
				winner = candidates[i];
			}
		}
		System.out.println();
		for(String row : graphRows) {
			System.out.print(row);
		}
		System.out.println("\t   Vote Percentage\n");

		return winner;
	}

	@Override
	public String toString() {
		return "Country: " + name + "\n\tGovernment Type: " + gov + "\n\tLeader: " + leader + "\n";
	}
	
	public String toString(boolean abridge) {
		if(abridge) {
			return name + " (" + gov + ")";
		} else {
			return this.toString();
		}
	}
}
