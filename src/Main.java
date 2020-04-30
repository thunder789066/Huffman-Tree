//package io;
import java.util.*;
import java.lang.*;
import java.nio.file.*;
import java.util.Map.Entry;

public class Main {
	
	public static String readFile(String file) throws Exception {
		String data;
		data = new String(Files.readAllBytes(Paths.get(file)));
		return data;
	}
	
	public static int findinfile(String file) {
		int count = 0;
		
		for (int i = 0; i < file.length(); i++) {
			if (file.charAt(i) == 'a') {
				count++;
			}
		}
		
		return count;
	}

	public static void main(String[] args) throws Exception {
		String data = readFile("C:\\Users\\Christina\\Documents\\All Programs\\Git Repos\\Huffman_Tree\\src\\test.txt");
		System.out.println(data);
		
		System.out.println("a " + findinfile(data));
	}

}
