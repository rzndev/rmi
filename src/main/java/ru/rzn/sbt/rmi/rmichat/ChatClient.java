package ru.rzn.sbt.rmi.rmichat;

import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

/**
 * Класс клиента чата
 */
public class ChatClient {

    /**
     * Адрес сервера чата
     */
    final static String centralNodeHost = "127.0.0.1";
    final static int clientPort = 10000;

    public static void main(String[] args) {
        String nickName = "guest";
        if (args.length > 0)
            nickName = args[0];
        try {
            Registry registryCentral = LocateRegistry.getRegistry(centralNodeHost);
            IChatCentralNode chatCentralNode = (IChatCentralNode) registryCentral.lookup("centralNode");

            Registry registryLocal = LocateRegistry.createRegistry(clientPort);
            IChatLocalNode localNode = (IChatLocalNode) UnicastRemoteObject.exportObject(new ChatLocalNodeImpl(), 0);
            registryLocal.bind("localNode", localNode);

            System.out.println("Registering client on server " + chatCentralNode);
            chatCentralNode.register(clientPort, nickName);
            System.out.println("Client successfully registered on server" + chatCentralNode);

            Scanner inputReader = new Scanner(System.in);
            while(true) {
                System.out.print("Input chat string: ");
                chatCentralNode.send(nickName + ": " + inputReader.nextLine());
            }
        } catch (RemoteException ex) {
            ex.printStackTrace();
        } catch (NotBoundException ex) {
            ex.printStackTrace();
        } catch (AlreadyBoundException ex) {
            ex.printStackTrace();
        }

    }
}
