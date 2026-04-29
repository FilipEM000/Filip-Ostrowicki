package pd16.Repository;

import pd16.Model.Client;
import pd16.Exception.ClientRegistrationException;

import java.util.HashSet;
import java.util.Set;

public class ClientRepository {
    Set<Client> clients = new HashSet<>();

    public void addClient(Client client) {
        clients.add(client);
    }

    public boolean isEmailTaken(String email) {
        return clients.stream()
                .anyMatch(client -> client.getEmail().equals(email));
    }

    public Client findClient(String email) {
        return clients.stream()
                .filter(client1 -> client1.getEmail().equals(email))
                .findAny()
                .orElseThrow(() -> new ClientRegistrationException("Klient z podanym emailem nie istnieje"));
    }
}
