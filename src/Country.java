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
		return new ArrayList<Country>(Country.countries);  // returns a copy
	}
	
	public void election() {
		if(gov.electionsAllowed()) {
			System.out.println("Enter number of candidates in election (no more than 7): ");
			int candidateAmount = Integer.parseInt(SCANNER.nextLine());
			candidates = new Person[candidateAmount];
			for(int i = 0; i < candidates.length; i++) {
				candidates[i] = new Person();
			}
			Person winner = getElectionResults(candidates);
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

		String[] abbreviations = new String[candidates.length];  // truncated candidates names
		int[] votes = new int[candidates.length];  // each element = total votes for each candidate
		int[] votePoints = new int[candidates.length];  // 1 votepoint = 1 box in histogram
		int totalVotes = 0;  // total votes for all candidates in election

		for(int i = 0; i < candidates.length; i++) {  // each loop-thru determines candidate's abbreviation & votes
			abbreviations[i] = candidates[i].getAbbreviation();
			votes[i] = RANDOM.nextInt(VOTE_COUNT_RANGE);
			totalVotes += votes[i];
		}
		
		int maxVotes = votes[0];  // initializing highest vote count
		Person winner = candidates[0];  // initializing highest voted for candidate

		for(int i = 0; i < votes.length; i++) {
			int votePercentage = (votes[i]*100)/totalVotes;
			votePoints[i] = (int)Math.round((votes[i] * 20)/totalVotes);  // calculate length of each candidate's histogram bar
			graphRows[i] = abbreviations[i] + "(" + votePercentage + "%)" + translateVotePoints(votePoints[i]) + "\n";  // builds histogram bars
			System.out.println(abbreviations[i] + " - " + candidates[i].getName() + " (" + votePercentage + "%)");  // prints legend

			if(votes[i] > maxVotes) {  // determines highest votes and corresponding candidate
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
