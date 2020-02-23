import java.util.*;

public class Country {

	public static final ArrayList<Country> COUNTRIES = new ArrayList<Country>(); // stores all countries
	public static final int VOTE_COUNT_RANGE = 100;

	private String name;
	private String gov;
	private String style;
	private Person leader;
	private String[] govList = {"Anarchy","Aristocracy","Bureaucracy","Capitalist","Colonialist","Communist","Democracy","Federalist","Feudalist","Kleptocracy","Meritocracy","Dictatorship","Monarchy","Oligarchy","Plutocracy","Republic","Socialist","Theocracy","Totalitarian","Tribalist"};
	private Person[] candidates;

	public static Random rand = new Random();

	public Country(String name) {
		this.name = name;
		leader = new Person();
		gov = govList[rand.nextInt(govList.length - 1)];
		COUNTRIES.add(this);
	}

	public static void printCountries(boolean abridge) {
		if(abridge) {
			for (final Country country : COUNTRIES) {
				System.out.println(country.toString(true));
			}
		} else {
			for (Country country : COUNTRIES) {
				System.out.println(country);
			}
		}
	}

	public static Country getCountry(int index) {
		return COUNTRIES.get(index);
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getName() {
		return name;
	}


	public void setGov(String gov) {
		this.gov = gov;
	}


	public String getGov() {
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

	public void election(int candidateAmount) {
		candidates = new Person[candidateAmount];
		Person winner;
		for(int i = 0; i < candidates.length; i++) {
			candidates[i] = new Person();
		}
		winner = getElectionResults(candidates);
		//printCandidates();
		System.out.println("Elected: " + winner + "\n");
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
			votes[i] = rand.nextInt(VOTE_COUNT_RANGE);
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
