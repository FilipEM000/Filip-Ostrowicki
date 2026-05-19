package pd12;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Test {
    public static void main(String[] args) {
        ExperimentA();
        System.out.println();
        ExperimentB();  // samo equals nie wystarczyło, ponieważ HashSet w pierwszej kolejności porównuje obiekty za pomocą metody hashCode(), a dopiero jeśli hash() zwróci ten sam wynik, HashSet sprawdza metodą equals()
        System.out.println();
        ExperimentC(); // teraz kolekcje działają poprawnie, ponieważ kontrakt equals() i hashCode() są spełnione
        System.out.println();
        ExperimentD(); // po zmianie pola, które jest uwzględniane w hashCode(), hash dla obiektu ulega zmianie, przez co nie pasuje do oryginalnego bucketu
    }

    private static void ExperimentA() {
        Set<UserAccountV1> userSet = new HashSet<>();

        UserAccountV1 user1 = new UserAccountV1(1234, "adam@wp.pl", "Adam");
        UserAccountV1 user2 = new UserAccountV1(1234, "adam@wp.pl", "Adam");

        userSet.add(user1);
        userSet.add(user2);

        System.out.println("EKSPERYMENT A");
        System.out.println("rozmiar seta: " + userSet.size());
        System.out.println("wynik equals: " + user1.equals(user2));
    }

    private static void ExperimentB() {
        Set<UserAccountV2> userSet = new HashSet<>();

        UserAccountV2 user1 = new UserAccountV2(1234, "adam@wp.pl", "Adam");
        UserAccountV2 user2 = new UserAccountV2(1234, "adam@wp.pl", "Adam");

        userSet.add(user1);
        userSet.add(user2);

        System.out.println("EKSPERYMENT B");
        System.out.println("rozmiar seta: " + userSet.size());
        System.out.println("wynik contains : " + userSet.contains(new UserAccountV2(1234, "adam@wp.pl", "Adam")));
    }

    private static void ExperimentC() {
        Set<UserAccountV3> userSet = new HashSet<>();
        Map<UserAccountV3, String> userMap = new HashMap<>();

        UserAccountV3 user1 = new UserAccountV3(1234, "adam@wp.pl", "Adam");
        UserAccountV3 user2 = new UserAccountV3(1234, "adam@wp.pl", "Adam");

        userSet.add(user1);
        userSet.add(user2);
        userMap.put(user1, "user1");
        userMap.put(user2, "user2");

        System.out.println("EKSPERYMENT C");
        System.out.println("wynik contains dla seta: " + userSet.contains(new UserAccountV3(1234, "adam@wp.pl", "Adam")));
        System.out.println("wynik get dla mapy: " + userMap.get(new UserAccountV3(1234, "adam@wp.pl", "Adam"))); //w wyniku wyświetla się "user2", ponieważ jest to ostatnia dodana wartość dla tego samego klucza
    }

    private static void ExperimentD() {
        Set<UserAccountV4> userSet = new HashSet<>();

        UserAccountV4 user1 = new UserAccountV4(1234, "adam@wp.pl", "Adam", "ACTIVE");

        userSet.add(user1);
        user1.setStatus("BLOCKED");

        System.out.println("EKSPERYMENT D");
        System.out.println("wynik contains: " + userSet.contains(user1));
        System.out.println("wynik remove: " + userSet.remove(user1));
    }
}
