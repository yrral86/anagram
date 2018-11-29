package net.yrral.anagram;

import java.io.IOException;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Finder {

	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println("Usage:");
			System.out.println("Finder dictionary.txt:");
			System.out.println("dictionary.txt should be the path to a dictionary file with one word per line");
			System.exit(1);
		}
		
		System.out.println("Welcome to the Anagram Finder");
		System.out.println("-----------------------------");
		Dictionary dictionary = null;
		long start = System.currentTimeMillis();
		try {
			dictionary = new Dictionary(args[0]);
		} catch (IOException e) {
			System.out.println("Failed to load dictionary file: " + args[0]);
			System.out.println(e.getClass().getName());
			System.exit(2);
		}
		long ellapsed = System.currentTimeMillis() - start;
		System.out.println("Dictionary loaded in " + ellapsed + " ms");
		
		try (Scanner scanner = new Scanner(System.in)) {
			while (true) {
				System.out.print("AnagramFinder> ");
				String word = scanner.nextLine();
				if (word.equals("exit")) {
					System.exit(0);
				} else {
					System.out.println(dictionary.getAnagrams(word).stream().collect(Collectors.joining(", ")));
				}
			}
		}
	}

}
