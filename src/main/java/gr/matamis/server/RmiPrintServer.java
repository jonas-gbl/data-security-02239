package gr.matamis.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RmiPrintServer extends UnicastRemoteObject implements PrintServer {

    private final ConsolePrintServer consolePrintServer;

    protected RmiPrintServer(AuthenticationService authenticationService) throws RemoteException {
        this.consolePrintServer = new ConsolePrintServer(authenticationService);
    }

    @Override
    public void print(String filename, String printer, Credentials credentials) throws AuthenticationException {
        this.consolePrintServer.print(filename, printer, credentials);
    }

    @Override
    public void queue(Credentials credentials) throws RemoteException {
        this.consolePrintServer.queue(credentials);
    }

    @Override
    public void start(Credentials credentials) throws RemoteException {
        this.consolePrintServer.start(credentials);
    }

    @Override
    public void restart(Credentials credentials) throws RemoteException {
        this.consolePrintServer.restart(credentials);
    }

    @Override
    public void stop(Credentials credentials) throws RemoteException {
        this.consolePrintServer.stop(credentials);
    }

    @Override
    public void printerStatus(Credentials credentials) throws RemoteException {
        this.consolePrintServer.printerStatus(credentials);
    }

    @Override
    public void readConfig(String parameter, Credentials credentials) throws RemoteException {
        this.readConfig(parameter, credentials);
    }

    @Override
    public void setConfig(String parameter, String value, Credentials credentials) throws RemoteException {
        this.setConfig(parameter, value, credentials);
    }
}
