package pd14;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class EventProcessor {

    public void process(List<Event> events, List<Predicate<Event>> filters, Function<Event, String> formatter, Consumer<String> output) {
        Predicate<Event> combined = event -> true;
        for (Predicate<Event> eventPredicate : filters) {
            combined = combined.and(eventPredicate);
        }

        for (Event event : events) {
            if (combined.test(event)) {
                String formatted = formatter.apply(event);
                output.accept(formatted);
            }
        }
    }
}
