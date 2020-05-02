import java.util.*;
import java.lang.*;
import java.util.Map.Entry;

public class Tree_Table_Build {
	private static String text = null;
	private static Map<String, Integer> freq_table = new HashMap<String, Integer>();
	
	public Tree_Table_Build(String data) {
		text = data;
		freqTableBuild(text);
	}
	
	private static boolean double_checker(String letter) { // checks if char exists in hashmap
		Iterator<Map.Entry<String, Integer>> iterator = freq_table.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<String, Integer> symbol = iterator.next();
			if (symbol.getKey().equalsIgnoreCase(letter))
				return true;
		} return false;
	}
	
	private static String spaceCheck(String letter) {
		if (letter == " ")
			return "space";
		return letter;
	}
	
	private static void freqTableBuild(String text) {
		for (int i = 0; i < text.length(); i++) {
			String str = String.valueOf(text.charAt(i));
			boolean check = double_checker(str);
			if (check == false) {		// if NOT FOUND, add to hashmap
				str = spaceCheck(str);
				freq_table.put(str, 1);
			} else {					//if FOUND, update element (int) +1
				Iterator<Map.Entry<String, Integer>> iterator = freq_table.entrySet().iterator();
				while (iterator.hasNext()) {
					Map.Entry<String, Integer> symbol = iterator.next();
					if (symbol.getKey().equalsIgnoreCase(str)) {
						freq_table.put(str, freq_table.get(str) + 1);
					}
				}
			}
		}
	}
	
	public static void outputTable() {
		System.out.println("  Symbol   |   Value"
						  + "\r\n ---------------------");
		/*System.out.println("   space   |     3"
						  + "\r\n   f\t   |     4"); */
		for (Entry<String, Integer> entry : freq_table.entrySet()) {
			String symbol = entry.getKey();
			int value = entry.getValue();
            if (symbol == "space")
            	System.out.println("   " + symbol + "   |     " + value);
            System.out.println("   " + symbol + "\t   |     " + value);
		}
	}
}
