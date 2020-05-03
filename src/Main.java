//package io;
import java.util.*;
import java.nio.file.*;

public class Main {
	
	public static String readFile(String file) throws Exception {
		String data;
		data = new String(Files.readAllBytes(Paths.get(file)));
		return data;
	}

	public static void main(String[] args) throws Exception {
		String data = readFile("C:\\Users\\Christina\\Documents\\All Programs\\Git Repos\\Huffman_Tree\\src\\test.txt");
		data = data.replace(System.getProperty("line.separator"), " ");
		
		Frequency_Table_Build freq = new Frequency_Table_Build(data);
		HuffmanTree tree = new HuffmanTree(freq.getMap());
		
		/*for testing*/
		//System.out.println(data);
		//freq.outputTable();
		String out = tree.decode("c");
		System.out.println(out);
	}

}
