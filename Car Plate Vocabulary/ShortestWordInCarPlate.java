import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ShortestWordInCarPlate {
    private Map<Integer, List<String>> vocabulary_by_size =
            new TreeMap<Integer, List<String>>();

    ShortestWordInCarPlate(List<String> vocabulary) {
        for (String word : vocabulary) {
            int word_len = word.length();
            vocabulary_by_size.computeIfAbsent(
                    word_len, k -> new ArrayList<String>()).add(word);
        }
    }

    public String findShortestWord(String car_plate) {
        // find the letters in the car plate
        List<Character> letters = new ArrayList<>();
        for (int i = 0; i < car_plate.length(); i++) {
            char ch = car_plate.toLowerCase().charAt(i);
            if (Character.isLetter(ch)) {
                letters.add(ch);
            }
        }
        for (Map.Entry<Integer, List<String>> entry : vocabulary_by_size.entrySet()) {
            // skip vocabulary sizes that are too small
            if (entry.getKey() < letters.size()) {
                continue;
            }

            for (String vocabulary_word : entry.getValue()) {
                // search for a vocabulary word with all the letters from the plate
                boolean is_valid = true;
                Map<Character, Integer> letter_counter = new TreeMap<Character, Integer>();
                for (int i = 0; i < vocabulary_word.length(); i++) {
                    Character ch = vocabulary_word.charAt(i);
                    int count = letter_counter.getOrDefault(ch, 0);
                    letter_counter.put(ch, count + 1);
                }
                for (Character letter : letters) {
                    int count = letter_counter.getOrDefault(letter, 0);
                    if (count < 1) {
                        is_valid = false;
                        break;
                    }
                    letter_counter.put(letter, count - 1);
                }
                if (is_valid) {
                    return vocabulary_word;
                }
            }
        }
        return "";
    }

    public static void main(String[] args) {
        java.util.ArrayList<String> list = new java.util.ArrayList<String>();
        list.add("sort");
        list.add("car");
        list.add("rest");
        list.add("rust");
        list.add("sir");
        list.add("cast");
        ShortestWordInCarPlate finder = new ShortestWordInCarPlate((list));
        String result = finder.findShortestWord("RT 123 SO");
        System.out.println("Shortest word is " + result);
        result = finder.findShortestWord("RC 10014");
        System.out.println("Shortest word is " + result);
    }
}
