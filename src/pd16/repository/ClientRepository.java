package pd16.repository;

import pd16.model.Client;
import pd16.exception.ClientRegistrationException;

import java.util.HashSet;
import java.util.Set;

public class ClientRepository {
    private Set<Client> clients = new HashSet<>();

    public Set<Client> findAll() {
        return clients;
    }

    public void save(Client client) {
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