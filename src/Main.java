//package io;
import java.util.*;
import java.lang.*;
import java.nio.file.*;

public class Main {
	
	public static String readFile(String file) throws Exception {
		String data;
		data = new String(Files.readAllBytes(Paths.get(file)));
		return data;
	}
	
	// for testing
	public static int findinfile(String file) { // counting every instance if char is found
		int count = 0;
		
		for (int i = 0; i < file.length(); i++) {
			if (file.charAt(i) == 'a') {
				count++;
			}
		}
		
		return count;
	}// */

	public static void main(String[] args) throws Exception {
		String newline = System.getProperty("line.separator");
		String data = readFile("C:\\Users\\Christina\\Documents\\All Programs\\Git Repos\\Huffman_Tree\\src\\test.txt");
		data = data.replace(newline, " ");
		Tree_Table_Build tree = new Tree_Table_Build(data);
		
		/*for testing*/
		//System.out.println(data);
		//System.out.println("a " + findinfile(data));
		tree.outputTable();
	}

}
