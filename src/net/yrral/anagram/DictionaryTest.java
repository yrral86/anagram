package net.yrral.anagram;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class DictionaryTest {
	private static Dictionary d;

	@BeforeAll
	static void setup() throws IOException {
		d = new Dictionary("data/dictionary.txt");
	}

	@Test
	void testStop() {
		Set<String> stopAnagrams = d.getAnagrams("stop");
		assertEquals(4, stopAnagrams.size());
		assertTrue(stopAnagrams.contains("post"));
		assertTrue(stopAnagrams.contains("stop"));
		assertTrue(stopAnagrams.contains("spot"));
		assertTrue(stopAnagrams.contains("tops"));
	}

	@Test
	void testAccept() {
		Set<String> acceptAnagrams = d.getAnagrams("accept");
		assertEquals(1, acceptAnagrams.size());
		assertTrue(acceptAnagrams.contains("accept"));
	}

	@Test
	void testExit() {
		Set<String> exitAnagrams = d.getAnagrams("exit");
		assertEquals(1, exitAnagrams.size());
		assertTrue(exitAnagrams.contains("exit"));
	}

	@Test
	void testOreo() {
		Set<String> oreoAnagrams = d.getAnagrams("oreo");
		assertEquals(0, oreoAnagrams.size());
	}
}
