import java.util.PriorityQueue;
import java.util.*;

public class HuffmanTree {
	   private Node root;
	   
	   /* Building Huffman Tree given from Frequency_Table_Build
	    * Key - letter, Value - frequency of letter */
	   public HuffmanTree(Map<String, Integer> freqTable) {
		   PriorityQueue<Node> nodes = new PriorityQueue<Node>();
		   for (String letter : freqTable.keySet()) {
			   Node newNode = new Node();
			   newNode.symbol = letter;
			   newNode.freq = freqTable.get(letter);
			   nodes.add(newNode);
		   }
		   
		   while (nodes.size() > 1) {
			   Node smallest = nodes.remove();
			   Node nextSmallest = nodes.remove();
			   Node newNode = new Node();
			   newNode.freq = smallest.freq + nextSmallest.freq;
			   newNode.left = smallest;
			   newNode.right = nextSmallest;
			   nodes.add(newNode);
		   } root = nodes.remove();      
	   }
	   
	   /* Decoding tree as 0 (left) & 1 (right) */
	   public String decode(String input) {
		   String result = "";
		   Node n = root;
		   for (int i = 0; i < input.length(); i++) {
			   char symbol = input.charAt(i);
			   if (symbol == '0')
				   n = n.left;
			   else
				   n = n.right;
			   
			   if (n.left == null) { // n is a leaf
				   result += n.symbol;
				   n = root;
			   }
		   } return result;
	   }

	   public Map<String, String> getEncodingMap() {
		   Map<String, String> map = new HashMap<String, String>();
		   if (root != null)
			   root.fillEncodingMap(map, "");
		   return map;
	   }

	   class Node implements Comparable<Node> {
		   public String symbol;
		   public int freq;
		   public Node left, right;
		   
		   public int compareTo(Node other) {
			   return freq - other.freq;
		   }
		   
		   public void fillEncodingMap(Map<String, String> map, String val) {
			   if (left == null) // it's a leaf
				   map.put(symbol, val);
			   else {
				   left.fillEncodingMap(map, val + "0");
				   right.fillEncodingMap(map, val + "1");
			   }
		   }
	   }
}
