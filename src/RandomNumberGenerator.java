import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomNumberGenerator {
    private List<Integer> numbers = new ArrayList<>();
    private int index = 0;

    public RandomNumberGenerator() {
        // Fill the list with numbers 0 to 15 and shuffle them
        for (int i = 0; i <= 19; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers);
    }

    public Integer getNext() {
        // Return null if all numbers have been used
        if (index >= numbers.size()) {
            return null;
        }
        return numbers.get(index++);
    }

}