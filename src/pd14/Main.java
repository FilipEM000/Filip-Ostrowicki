package pd14;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {
        EventProcessor eventProcessor = new EventProcessor();

        List<Event> events = new ArrayList<>(List.of(
                new Event("fdffddss", 1203),
                new Event("kbfd", 1010),
                new Event("fsdosaf", 1004)));

        Predicate<Event> isNameLong = event -> event.getName().length() > 5;
        Predicate<Event> doesNameStartsWithF = event -> event.getName().startsWith("f");
        Predicate<Event> isIdDivisibleByTwo = event -> event.getId() % 2 == 0;
        List<Predicate<Event>> filters = new ArrayList<>(List.of(isNameLong, doesNameStartsWithF, isIdDivisibleByTwo));

        Function<Event, String> formatter = Event::toString;
        Consumer<String> output = System.out::println;

        eventProcessor.process(events, filters, formatter, output);
    }
}
