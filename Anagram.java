/** Functions for checking if a given string is an anagram. */
public class Anagram {
	public static void main(String args[]) {
		// Tests the isAnagram function.
		System.out.println(isAnagram("silent","listen"));  // true
		System.out.println(isAnagram("William Shakespeare","I am a weakish speller")); // true
		System.out.println(isAnagram("Madam Curie","Radium came")); // true
		System.out.println(isAnagram("Tom Marvolo Riddle","I am Lord Voldemort")); // true

		// Tests the preProcess function.
		System.out.println(preProcess("What? No way!!!"));
		
		// Tests the randomAnagram function.
		System.out.println("silent and " + randomAnagram("silent") + " are anagrams.");
		
		// Performs a stress test of randomAnagram 
		String str = "1234567";
		Boolean pass = true;
		//// 10 can be changed to much larger values, like 1000
		for (int i = 0; i < 10; i++) {
			String randomAnagram = randomAnagram(str);
			System.out.println(randomAnagram);
			pass = pass && isAnagram(str, randomAnagram);
			if (!pass) break;
		}
		System.out.println(pass ? "test passed" : "test Failed");
	}  

	// Returns true if the two given strings are anagrams, false otherwise.
	public static boolean isAnagram(String str1, String str2) {
		//"cleaning" the two input strings
		String word1 = preProcessSpaces(str1);
		String word2 = preProcessSpaces(str2);

		String firstPart = "";
		String lastPart = "";
		char curr;
		int index;
		//checking the requirement of matching lengths for the 2 words
		if(word1.length() != word2.length()) return false;

		//going over the first word, and checking if all its characters exist in the second word
		for(int i = 0; i<word1.length(); i++) {
			curr = word1.charAt(i);
			index = word2.indexOf(curr);
			//if that letter was found in the second word - deleting it from that word and moving on
			if(index != -1) {
				firstPart = word2.substring(0, index);
				if(index + 1 == word2.length()) lastPart = "";
				else lastPart = word2.substring(index + 1);
				word2 = firstPart + lastPart;
			} 
			else return false; //if the letter wasn't found on the second word, then it's not an anagram
		}
		//if we made it thus far (through all the characters of the first word)
		//without finding a mismathcing letter - then the 2 words are anagrams
		return true;
	}
	   
	// Returns a preprocessed version of the given string: all the letter characters are converted
	// to lower-case, and all the other characters are deleted, except for spaces, which are left
	// as is. For example, the string "What? No way!" becomes "whatnoway"
	public static String preProcess(String str) {
		String input = str.toLowerCase();
		String clean = "";
		char curr;
		String abc = "abcdefghijklmnopqrstuvwxyz ";
		
		//going over the input word and copying only the ABC characters into a new string
		for(int i = 0; i < input.length(); i++) {
			curr = input.charAt(i);
			//checking if that character is a letter, and if so - adding it to the filtered word
			if(abc.indexOf(curr) != -1) clean += curr;
		}
		return clean;
	}

	//same method - but deleting the spaces (to be used only in "isAnagram")
	public static String preProcessSpaces(String str) {
		String input = str.toLowerCase();
		String clean = "";
		char curr;
		String abc = "abcdefghijklmnopqrstuvwxyz";
		
		//going over the input word and copying only the ABC characters into a new string
		for(int i = 0; i < input.length(); i++) {
			curr = input.charAt(i);
			//checking if that character is a letter, and if so - adding it to the filtered word
			if(abc.indexOf(curr) != -1) clean += curr;
		}
		return clean;
	} 
	   
	// Returns a random anagram of the given string. The random anagram consists of the same
	// characters as the given string, re-arranged in a random order. 
	public static String randomAnagram(String str) {
		String word = str;
		String anagram = "";
		String firstPart = "";
		String lastPart = "";
		int index;

		//going over the input word, and for every character (not by order)
		//copying it to the string "anagram" and deleting it from the original word.
		//running until there are no more characters left in the original word.
		while(! word.equals("")) {
			index = (int) (Math.random() * word.length()); //generating a random index
			anagram += word.charAt(index); //adding the new character to the anagram
			//deleting that character from the original word
			firstPart = word.substring(0, index);
			if(index + 1 == word.length()) lastPart = "";
			else lastPart = word.substring(index + 1);				
			word = firstPart + lastPart;
			} 
		return anagram;
	}
}
