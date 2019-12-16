package org.aoc.intcode;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		
		List<Integer> initialMemory = new ArrayList<>();
		Scanner scanner = openInputFile("./input.txt");
		scanner.useDelimiter(",");
		scanner.forEachRemaining(data -> initialMemory.add(Integer.parseInt(data.trim())));
		scanner.close();
				
		IntcodeComputer computer = new IntcodeComputer(new IntcodeMemory(initialMemory));
		computer.run();
	}
	
	private static Scanner openInputFile(String filename) {
		Scanner scanner = null;
		try {
			File file = new File(filename);
			scanner = new Scanner(file);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return scanner;
	}
}
