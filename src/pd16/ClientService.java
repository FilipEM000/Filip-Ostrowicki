package pd16;

import java.util.Scanner;

public class ClientService {
    ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }
    Scanner scanner = new Scanner(System.in);

    public void registerClient() {
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

        clientRepository.addClient(Client.of(name, email));
        System.out.println(clientRepository.clients);
    }
}
