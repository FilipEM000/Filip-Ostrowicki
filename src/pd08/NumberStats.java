package pd08;

import java.util.ArrayList;
import java.util.List;

public class NumberStats<T extends Number & Comparable<T>> {
    List<T> listOfNumbers = new ArrayList<>();

    public void add(T number) {
        listOfNumbers.add(number);
    }

    public T getMin() {
        if (listOfNumbers.isEmpty()) {
            return null;
        }
        T min = listOfNumbers.get(0);
        for (T number : listOfNumbers) {
            if (number.compareTo(min) < 0) {
                min = number;
            }
        }
        return min;
    }

    public T getMax() {
        if (listOfNumbers.isEmpty()) {
            return null;
        }
        T max = listOfNumbers.get(0);
        for (T number : listOfNumbers) {
            if (number.compareTo(max) > 0) {
                max = number;
            }
        }
        return max;
    }

    public double getAverage() {
        return listOfNumbers.isEmpty() ? 0 : getSum() / getCount();
    }

    public double getSum() {
        if (listOfNumbers.isEmpty()) {
            return 0;
        }
        double sum = 0;
        for (T number : listOfNumbers) {
            sum = sum + number.doubleValue();
        }
        return sum;
    }

    public int getCount() {
        return listOfNumbers.size();
    }

    public List<T> getSorted() {
        if (listOfNumbers.isEmpty()) {
            return null;
        }
        List<T> sortedCopy = new ArrayList<>(listOfNumbers);
        sortedCopy.sort(null);
        return sortedCopy;
    }
}
