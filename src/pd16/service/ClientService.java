package pd16.service;

import pd16.model.Client;
import pd16.exception.ClientRegistrationException;
import pd16.repository.ClientRepository;

import java.util.Scanner;

public class ClientService {
    ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public void registerClient() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj nazwę użytkownika");
        String name = scanner.nextLine();
        System.out.println("Podaj email użytkownika");
        String email = scanner.nextLine();
        if (name.isEmpty() || email.isEmpty()) {
            throw new ClientRegistrationException("Klient nie może miec pustej nazwy ani emaila");
        }

        if (clientRepository.isEmailTaken(email)) {
            throw new ClientRegistrationException("Klient z podanym adresem email już istnieje");
        }

        clientRepository.save(Client.of(name, email));
    }
}