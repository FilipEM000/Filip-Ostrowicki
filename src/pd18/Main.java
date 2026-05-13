package pd18;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class Main {
    public static void main(String[] args) {
        DateCalculator dateCalculator = new DateCalculator();

        System.out.println(dateCalculator.age(LocalDate.of(2003, 7, 23)));
        System.out.println(dateCalculator.nextPayDay(LocalDate.now()));
        System.out.println(dateCalculator.businessDaysBetween(LocalDate.now(), LocalDate.now().plusDays(15)));
        System.out.println(dateCalculator.convertTimezone(ZonedDateTime.of(LocalDateTime.now(), ZoneId.of("Europe/Warsaw")), "Asia/Tokyo"));
        System.out.println(dateCalculator.formatForLocale(LocalDateTime.now(), "US"));
        System.out.println(dateCalculator.isValidDate("05/08/2026", "dd/MM/yyyy"));
    }
}
