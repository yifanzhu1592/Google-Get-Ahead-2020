import java.util.List;
import java.util.Queue;

// A class for location on the grid
class Location {
    int x;
    int y;

    Location(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

// A class for each stage in our hunting
class Stage {
    private Location current_location;
    private String prefix;
    private List<Location> visited_locations;

    Stage(Location current_location, String prefix,
          List<Location> visited_locations) {
        this.current_location = current_location;
        this.prefix = prefix;
        this.visited_locations = visited_locations;
    }
    // Getters and Setters are nice but in an interview you can omit them
    // and make the variables public as long you specify that you do
    // that only to save time and space.
    public Location get_current_location() {
        return this.current_location;
    }

    public String get_prefix() {
        return this.prefix;
    }

    public List<Location> get_visited_locations() {
        return this.visited_locations;
    }
}

public class WordHunter {
    private String[][] grid;
    private Queue<Stage> queue = new java.util.LinkedList<Stage>();
    private int max_len;
    private List<String> words = new java.util.ArrayList<String>();
    private String longest = "";
    private Location[] possible_moves = {new Location(1, 0), new Location(0, 1),
            new Location(-1, 0), new Location(0, -1),
            new Location(1, 1), new Location(1, -1),
            new Location(-1, 1), new Location(-1, -1)};

    WordHunter(String[][] grid) {
        this.grid = grid;
        this.max_len = grid.length;
        for (int i = 0; i < this.max_len; i++) {
            for (int j = 0; j < this.max_len; j++) {
                java.util.ArrayList<Location> visited_list =
                        new java.util.ArrayList<Location>();
                visited_list.add(new Location(i, j));
                queue.add(new Stage(new Location(i, j), grid[i][j], visited_list));
            }
        }
    }

    // These two functions are placeholders, given that the problems statement tells
    // us we are given a dictionary that implements them, we don't need to worry
    // about it, we can just call them in our solution.
    private boolean isWord(String word) {
        return true;
    }

    private boolean isPrefix(String word) {
        return true;
    }

    public List<String> getWords(){
        return this.words;
    }

    public boolean contains(final List<Location> list, Location location) {
        return list.stream().anyMatch(o -> o.x == location.x && o.y == location.y);
    }

    public String findLongestWord() {
        while (!queue.isEmpty()) {
            Stage stage = queue.poll();
            // check all possible directions
            for (Location possible_move : this.possible_moves) {
                int new_x = stage.get_current_location().x + possible_move.x;
                int new_y = stage.get_current_location().y + possible_move.y;
                Location new_location = new Location(new_x, new_y);
                // check if the new location is inside the grid
                if (new_x >= 0 && new_x < max_len && new_y >= 0 &&
                    new_y < max_len) {
                    // check if the new location was not visited
                    if (!this.contains(stage.get_visited_locations(),
                                        new_location)) {
                        String new_prefix = stage.get_prefix() +
                                            this.grid[new_x][new_y];
                        // check that the new prefix is a word
                        if (isWord(new_prefix)) {
                            words.add(new_prefix);
                            // check if longest word so far
                            if (new_prefix.length() > this.longest.length()) {
                                this.longest = new_prefix;
                            }
                        }
                        // check that the new prefix is a prefix
                        if (isPrefix(new_prefix)) {
                            // add to queue
                            java.util.ArrayList<Location> visited_list =
                                    new java.util.ArrayList<Location>(
                                            stage.get_visited_locations());
                            visited_list.add(new_location);
                            Stage new_stage = new Stage(
                                    new_location, new_prefix, visited_list);
                            queue.add(new_stage);
                        }
                    }
                }
            }
        }
        return longest;
    }

    public static void main(String[] args) {
        String[][] grid1 = new String[][]{
                {"f", "x", "e", "i"},
                {"a", "m", "l", "o"},
                {"e", "w", "b", "x"},
                {"a", "s", "t", "u"}
        };
        String[][] grid2 = new String[][]{
                {"f", "x", "i", "e"},
                {"a", "m", "x", "o"},
                {"e", "w", "b", "c"},
                {"a", "s", "t", "u"}
        };
        WordHunter wordHunter1 = new WordHunter(grid1);
        String word = wordHunter1.findLongestWord();
        System.out.println("longest word is " + word);
        System.out.println("The found words are " + wordHunter1.getWords());

        WordHunter wordHunter2 = new WordHunter(grid2);
        String word2 = wordHunter2.findLongestWord();
        System.out.println("longest word is " + word2);
        System.out.println("The found words are " + wordHunter2.getWords());
    }
}
