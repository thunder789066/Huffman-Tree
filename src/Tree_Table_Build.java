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
	
	private static HashMap<String, Integer> sortMap() {
		List<Map.Entry<String, Integer> > templist = new LinkedList<Map.Entry<String, Integer> >(freq_table.entrySet());
		
		Collections.sort(templist, new Comparator<Map.Entry<String, Integer> >() { 
            public int compare(Map.Entry<String, Integer> i1, Map.Entry<String, Integer> i2) { 
                return (i1.getValue()).compareTo(i2.getValue()); 
            }
        });
		
		HashMap<String, Integer> tempMap = new LinkedHashMap<String, Integer>(); 
        for (Map.Entry<String, Integer> entry : templist) { 
        	tempMap.put(entry.getKey(), entry.getValue()); 
        } 
		
		return tempMap;
	}
	
	public void outputTable() {
		Map<String, Integer> sortedFreqTable = sortMap();
		System.out.println("  Symbol   |   Value"
						  + "\r\n ---------------------");
		for (Entry<String, Integer> entry : sortedFreqTable.entrySet()) {
			String symbol = entry.getKey();
			int value = entry.getValue();
            if (symbol == "space")
            	System.out.println("   " + symbol + "   |     " + value);
            System.out.println("   " + symbol + "\t   |     " + value);
		}
	}
}
