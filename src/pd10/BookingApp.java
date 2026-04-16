package pd10;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookingApp {
    public static void main(String[] args) {
        List<Trip> trips = new ArrayList<>();
        trips.add(Trip.of(Destination.of("Warszawa", "Polska"), TransportType.CAR, new BigDecimal(299), 3));
        trips.add(Trip.of(Destination.of("Ateny", "Grecja"), TransportType.PLANE, new BigDecimal(599), 5));
        trips.add(Trip.of(Destination.of("Tokyo", "Japonia"), TransportType.PLANE, new BigDecimal(1999), 7));
        trips.add(Trip.of(Destination.of("Zakopane", "Polska"), TransportType.TRAIN, new BigDecimal(399), 4));

        System.out.println(findByDestination(trips, "Ateny"));
        System.out.println(findBestTrip(User.of("Adam", null, new BigDecimal(800)), trips));
        System.out.println(buildTripDescription(User.of("Adam", null, new BigDecimal(800)), trips));
    }

    private static Optional<Trip> findByDestination(List<Trip> trips, String destinationName) {
        return trips.stream()
                .filter(trip -> trip.getDestination().getName().equals(destinationName))
                .findFirst();
    }

    private static Optional<Trip> findBestTrip(User user, List<Trip> trips) {
        if (user.getPreferredTransport().isEmpty() && user.getBudget().isEmpty()) {
            return Optional.empty();
        }

        return trips.stream()
                .filter(trip -> user.getPreferredTransport().isEmpty() || trip.getTransport() == user.getPreferredTransport().get())
                .filter(trip -> user.getBudget().isEmpty() || trip.getPrice().compareTo(user.getBudget().get()) <= 0)
                .findFirst();
    }

    private static String buildTripDescription(User user, List<Trip> trips) {
        return findBestTrip(user, trips)
                .map(trip -> trip.getDestination().getName() + " - " + trip.getTransport() + " - " + trip.getPrice())
                .orElse("No trip available");
    }
}
