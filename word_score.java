import java.io.*;
import java.math.*;
import java.util.*;

class word_score {
	
	public static void main(String[] args) {
		int list_size = Integer.parseInt(args[0]);
		List<String> list_of_words = new ArrayList<String>();
		try {	
			Scanner user_input = new Scanner(System.in);
			for (int i = 0; i < list_size; i++) {
				System.out.print("Enter a word: ");
				list_of_words.add(user_input.next());
			}
			user_input.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			Score_sort sorter = new Score_sort();
			List<String> score_sorted_words = sorter.sort_by_score_desc(list_of_words);
			System.out.println();
			System.out.println("Result: ");
			for (String word : score_sorted_words) {
				System.out.println(word);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class Score_sort {
	Map<Character, Integer> scores;
	static String SEPARATOR = " ";
	
	Score_sort() {
		scores = new HashMap<Character, Integer>();
		int value = 1;
		for (char current_char = 'a'; current_char <= 'z'; current_char++, value++) {
			scores.put(current_char, value);
		}
	}
	
	List<String> sort_by_score_desc(List<String> unsorted_list) {
		List<String> sorted_list = new ArrayList<String>();
		SortedMap<Integer, String> words_and_score_map = new TreeMap<Integer, String>(Collections.reverseOrder());
		for (String word : unsorted_list) {
			int word_score = calculate_score(word);
			if (words_and_score_map.containsKey(word_score)) {
				word = words_and_score_map.get(word_score) + SEPARATOR + word;
			}
			words_and_score_map.put(word_score, word);
		}
		for (Integer score : words_and_score_map.keySet()) {
			sorted_list.add(words_and_score_map.get(score));
		}
		return sorted_list;
	}
	
	int calculate_score(String word) {
		char[] chars_in_word = word.toLowerCase().toCharArray();
		int score = 0;
		for (char current_character : chars_in_word) {
			score += scores.get(current_character);
		}
		return score;
	}
}

