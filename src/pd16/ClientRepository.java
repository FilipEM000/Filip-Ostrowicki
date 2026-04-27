package pd16;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class ClientRepository {
    Set<Client> clients = new HashSet<>();
    Scanner scanner = new Scanner(System.in);

    public void addClient(Client client) {
        clients.add(client);
    }

    public boolean isEmailTaken(String email) {
        return clients.stream()
                .anyMatch(client -> client.getEmail().equals(email));
    }

    public Client findClient() {
        System.out.println("Podaj email użytkownika");
        String email = scanner.nextLine();

        return clients.stream()
                .filter(client1 -> client1.getEmail().equals(email))
                .findAny()
                .orElseThrow(() -> new ClientRegistrationException("Klient z podanym emailem nie istnieje"));
    }
}
