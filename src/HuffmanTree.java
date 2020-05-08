import java.util.*;
import java.util.Map.Entry;

//A Tree node
class Node {
	char ch;
	int freq;
	Node left = null, right = null;
	
	Node(char ch, int freq) {
		this.ch = ch;
		this.freq = freq;
	}

	public Node(char ch, int freq, Node left, Node right) {
		this.ch = ch;
		this.freq = freq;
		this.left = left;
		this.right = right;
	}
};

public class HuffmanTree {
	// traverse Huffman Tree & store Huffman Codes in a map
	public static void encode(Node root, String str, Map<Character, String> huffmanCode) {
		if (root == null)
			return;
		
		if (root.left == null && root.right == null)
			huffmanCode.put(root.ch, str);
		
		encode(root.left, str + "0", huffmanCode);
		encode(root.right, str + "1", huffmanCode);
	}
	
	// traverse the Huffman Tree and decode the encoded string
	public static int decode(Node root, int index, StringBuilder sb) {
		if (root == null)
			return index;
		
		if (root.left == null && root.right == null) {
			System.out.print(root.ch);
			return index;
		}
		index++;
		
		if (sb.charAt(index) == '0')
			index = decode(root.left, index, sb);
		else
			index = decode(root.right, index, sb);
		return index;
	}
	
	private static Map<Character, String> sortMap(Map<Character, String> huffmanCode) {
		List<Map.Entry<Character, String> > templist = new LinkedList<Map.Entry<Character, String> >(huffmanCode.entrySet());
		
		Collections.sort(templist, new Comparator<Map.Entry<Character, String> >() { 
            public int compare(Map.Entry<Character, String> i1, Map.Entry<Character, String> i2) { 
                return (i1.getValue()).compareTo(i2.getValue()); 
            }
        });
		
		HashMap<Character, String> tempMap = new LinkedHashMap<Character, String>(); 
        for (Map.Entry<Character, String> entry : templist) { 
        	tempMap.put(entry.getKey(), entry.getValue()); 
        } 
		
		return tempMap;
	}
	
	private static void outputTable(Map<Character, String> huffmanCode) {
		Map<Character, String> sortedFreqTable = sortMap(huffmanCode);
		System.out.println("  Symbol  |  Code"
						  + "\r\n ---------------------");
		for (Entry<Character, String> entry : sortedFreqTable.entrySet()) {
			Character symbol = entry.getKey();
			String value = entry.getValue();
            if (symbol == ' ')
            	System.out.println("   space" + "  |  " + value);
            else
            	System.out.println("   " + symbol + "\t  |  " + value);
		}
	}

	// Builds Huffman Tree & huffmanCode and decode given input text
	public static void buildHuffmanTree(String text) {
		// count frequency of appearance of each character & store in map
		Map<Character, Integer> freq = new HashMap<>();
		for (int i = 0 ; i < text.length(); i++) {
			if (!freq.containsKey(text.charAt(i)))
				freq.put(text.charAt(i), 0);
			freq.put(text.charAt(i), freq.get(text.charAt(i)) + 1);
		}
		
		/* Create a priority queue to store live nodes of Huffman tree
		Notice that highest priority item has lowest frequency */
		PriorityQueue<Node> pq = new PriorityQueue<>((l, r) -> l.freq - r.freq);
		
		// Create leaf node for each char & add to priority queue.
		for (Map.Entry<Character, Integer> entry : freq.entrySet()) {
			pq.add(new Node(entry.getKey(), entry.getValue()));
		}
		
		while (pq.size() != 1) { // Remove 2 nodes of highest priority (lowest freq) from queue
			Node left = pq.poll();
			Node right = pq.poll();
			
			/* Create a new internal node w/ two nodes as children & w/ frequency
			 * equal to the sum of the two nodes frequencies. Add new node to priority queue. */
			int sum = left.freq + right.freq;
			pq.add(new Node('\0', sum, left, right));
		}
		
		Node root = pq.peek(); // root stores pointer to root of Huffman Tree
		
		// traverse the Huffman tree and store the Huffman codes in a map
		Map<Character, String> huffmanCode = new HashMap<>();
		encode(root, "", huffmanCode);
		
		outputTable(huffmanCode);
	}
}
