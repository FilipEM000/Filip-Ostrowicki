package pd18;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

public class DateCalculator {
    public int age(LocalDate birthDate) {
        return Period.between(birthDate, LocalDate.now()).getYears();
    }

    public LocalDate nextPayDay(LocalDate from) {
        return from.withDayOfMonth(from.lengthOfMonth());
    }

    public long businessDaysBetween(LocalDate from, LocalDate to) {
        int daysBetween = Period.between(from, to).getDays();
        long businessDays = 0;
        for (int i = 0; i < daysBetween; i++) {
            if (from.plusDays(i).getDayOfWeek() == DayOfWeek.SATURDAY || from.plusDays(i).getDayOfWeek() == DayOfWeek.SUNDAY) {
                continue;
            }
            businessDays++;
        }
        return businessDays;
    }

    public ZonedDateTime convertTimezone(ZonedDateTime dateTime, String targetZone) {
        return dateTime.withZoneSameInstant(ZoneId.of(targetZone));
    }

    public String formatForLocale(LocalDateTime dateTime, String languageTag) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale.forLanguageTag(languageTag));

        return dateTime.format(dateTimeFormatter);
    }

    public boolean isValidDate(String input, String pattern) {
        try {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
            LocalDate.parse(input, dateTimeFormatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
