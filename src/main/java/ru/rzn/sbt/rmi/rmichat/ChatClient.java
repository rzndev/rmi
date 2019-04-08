package ru.rzn.sbt.rmi.rmichat;

import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * Класс клиента чата
 */
public class ChatClient {
    public static void main(String[] args) {
        try {
            Registry registryCentral = LocateRegistry.getRegistry();
            IChatCentralNode chatCentralNode = (IChatCentralNode) registryCentral.lookup("centralNode");

            Registry registry = LocateRegistry.createRegistry(1099);
            IChatLocalNode fileReader = (IChatLocalNode) UnicastRemoteObject.exportObject(new ChatCentralNodeImpl(), 0);
            registry.bind("FileReaderService", fileReader);
        } catch (RemoteException ex) {
            ex.printStackTrace();
        } catch (NotBoundException ex) {
            ex.printStackTrace();
        } catch (AlreadyBoundException ex) {
            ex.printStackTrace();
        }

    }
}
