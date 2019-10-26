package gr.matamis.server;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class PrintApplicationServer {

    private static final String PASSWORD_FILE = "password.data";

    public static void main(String[] args) {
        try {
            Path passwordFile = Paths.get(PASSWORD_FILE);
            AuthenticationService authenticationService = new FileBasedAuthenticator(passwordFile);
            Remote printServer = new RmiPrintServer(authenticationService);

            Registry registry = LocateRegistry.createRegistry(5050);
            registry.rebind("printer", printServer);
        } catch (IOException e) {
            System.err.println("Ton mpoulo: " + e.getMessage());
        }
    }
}
