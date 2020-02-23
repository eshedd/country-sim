import java.util.*;

public class Alliance {
	private ArrayList<Country> roster;
	private String name;
	
	public Alliance(String name) {
		this.name = name;
		roster = new ArrayList<Country>();
	}
	public void addCountry(Country country) {
		roster.add(country);
	}
	public String toString() {
		String list = "Alliance: " + name + "";
		for(Country country : roster) {
			list += "\n " + country.toString(true);
		}
		return list;
	}
}
