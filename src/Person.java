import java.io.*;
import java.lang.Math;
import java.util.*;

public class Person {
	private static final String FIRST_NAMES_FILE_PATH = "src/first_names.txt";
	private static final String LAST_NAMES_FILE_PATH = "src/last_names.txt";
	private static ArrayList<String> firstNameList;
	private static ArrayList<String> lastNameList;
	
	private Random rand = new Random();
	
	private String firstName;
	private String lastName;
	private int authorityAmount;  // high = total gov control; low = no gov control
	private int authorityConsolidation;  // high = dictator; low = complete democracy

	public Person() {
		createName();
		authorityAmount = rand.nextInt(100);
		authorityConsolidation = rand.nextInt(100);
	}

	private String plotPoint(int xVal) {
		String point = "\t\t  |\n";
		switch(xVal) {
		case 0: point = "\tx         |\n";
			break;
		case 1: point = "\t x        |\n";
			break;
		case 2: point = "\t  x       |\n";
			break;
		case 3: point = "\t   x      |\n";
			break;
		case 4: point = "\t    x     |\n";
			break;
		case 5: point = "\t     x    |\n";
			break;
		case 6: point = "\t      x   |\n";
			break;
		case 7: point = "\t       x  |\n";
			break;
		case 8: point = "\t        x |\n";
			break;
		case 9: point = "\t         x|\n";
			break;
		case 10: point = "\t          x\n";
			break;
		case 11: point = "\t          |x\n";
			break;
		case 12: point = "\t          | x\n";
			break;
		case 13: point = "\t          |  x\n";
			break;
		case 14: point = "\t          |   x\n";
			break;
		case 15: point = "\t          |    x\n";
			break;
		case 16: point = "\t          |     x\n";
			break;
		case 17: point = "\t          |      x\n";
			break;
		case 18: point = "\t          |       x\n";
			break;
		case 19: point = "\t          |        x\n";
			break;
		case 20: point = "\t          |         x\n";
			break;
		}
		return point;
	}
	
	private String plotPointXAxis(int xVal) {
		String point = "Anarchy ----------|---------- Archy\n";
		switch(xVal) {
		case 0: point = "Anarchy x---------|---------- Archy\n";
			break;
		case 1: point = "Anarchy -x--------|---------- Archy\n";
			break;
		case 2: point = "Anarchy --x-------|---------- Archy\n";
			break;
		case 3: point = "Anarchy ---x------|---------- Archy\n";
			break;
		case 4: point = "Anarchy ----x-----|---------- Archy\n";
			break;
		case 5: point = "Anarchy -----x----|---------- Archy\n";
			break;
		case 6: point = "Anarchy ------x---|---------- Archy\n";
			break;
		case 7: point = "Anarchy -------x--|---------- Archy\n";
			break;
		case 8: point = "Anarchy --------x-|---------- Archy\n";
			break;
		case 9: point = "Anarchy ---------x|---------- Archy\n";
			break;
		case 10: point = "Anarchy ----------x---------- Archy\n";
			break;
		case 11: point = "Anarchy ----------|x--------- Archy\n";
			break;
		case 12: point = "Anarchy ----------|-x-------- Archy\n";
			break;
		case 13: point = "Anarchy ----------|--x------- Archy\n";
			break;
		case 14: point = "Anarchy ----------|---x------ Archy\n";
			break;
		case 15: point = "Anarchy ----------|----x----- Archy\n";
			break;
		case 16: point = "Anarchy ----------|-----x---- Archy\n";
			break;
		case 17: point = "Anarchy ----------|------x--- Archy\n";
			break;
		case 18: point = "Anarchy ----------|-------x-- Archy\n";
			break;
		case 19: point = "Anarchy ----------|--------x- Archy\n";
			break;
		case 20: point = "Anarchy ----------|---------x Archy\n";
			break;
		}
		return point;
	}
	
	private void createName() {
		firstName = firstNameList.get(rand.nextInt(firstNameList.size() - 1));
		lastName = lastNameList.get(rand.nextInt(lastNameList.size() - 1));
	}
	
	public static void createNameLists() {
		// fill firstNameList
		firstNameList = new ArrayList<String>();
		lastNameList = new ArrayList<String>();
		File myObj = new File(FIRST_NAMES_FILE_PATH);
		try {
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				String name = myReader.nextLine();
				firstNameList.add(name.substring(0, 1).toUpperCase() + name.substring(1));

			}
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		// fill lastNameList
		myObj = new File(LAST_NAMES_FILE_PATH);
		try {
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				String name = myReader.nextLine();
				lastNameList.add(name.substring(0, 1).toUpperCase() + name.substring(1));
			}
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}
	
	public String getName() {
		return firstName + " " + lastName;
	}

	public String getSpectrum() {
		String graph = "";
		int xVal = (int)Math.round(((double)authorityAmount)/5);
		int yVal = (int)Math.round(((double)authorityConsolidation)/10);
		
		String[] rows = {"\t      Democracy\n", "\t\t  |\n", "\t\t  |\n", "\t\t  |\n", "\t\t  |\n", "\t\t  |\n", "Anarchy ----------|---------- Archy\n", "\t\t  |\n", "\t\t  |\n", "\t\t  |\n", "\t\t  |\n", "\t\t  |\n", "\t      Individual\n"};

		switch(yVal) {
		case 0: rows[11] = plotPoint(xVal);
			break;
		case 1: rows[10] = plotPoint(xVal);
			break;
		case 2: rows[9] = plotPoint(xVal);
			break;
		case 3: rows[8] = plotPoint(xVal);
			break;
		case 4: rows[7] = plotPoint(xVal);
			break;
		case 5: rows[6] = plotPointXAxis(xVal);
			break;
		case 6: rows[5] = plotPoint(xVal);
			break;
		case 7: rows[4] = plotPoint(xVal);
			break;
		case 8: rows[3] = plotPoint(xVal);
			break;
		case 9: rows[2] = plotPoint(xVal);
			break;
		case 10: rows[1] = plotPoint(xVal);
			break;
		}
		
		for (String row : rows) {
			graph += row;
		}
		
		return graph;
	}

	public int getAuthAmount() {
		return authorityAmount;
	}
	
	public int getAuthConsolidation() {
		return authorityConsolidation;
	}
	
	public String toString() {
		return firstName + " " + lastName + "\n\t Authority Support: " + authorityAmount + "/100\n\t Democracy Support: " + authorityConsolidation + "/100\n\n" + getSpectrum();
	}

}
