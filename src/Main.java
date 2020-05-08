import java.nio.file.*;

public class Main {
	
	public static String readFile(String file) throws Exception {
		String data;
		data = new String(Files.readAllBytes(Paths.get(file)));
		return data;
	}

	public static void main(String[] args) throws Exception {
		//String test_data = readFile("C:\\Users\\Christina\\Documents\\All Programs\\Git Repos\\Huffman_Tree\\src\\test.txt");
		String data = readFile("C:\\Users\\Christina\\Documents\\All Programs\\Git Repos\\Huffman_Tree\\src\\Sakiro.txt");
		data = data.replace(System.getProperty("line.separator"), " ").toLowerCase();
		
		Frequency_Table_Build freq = new Frequency_Table_Build(data);
		
		System.out.println("   Frequency Table\n");
		freq.outputTable();
		
		System.out.println("\n\n    Huffman Tree\n");
		HuffmanTree.buildHuffmanTree(data);
	}

}
