package ru.rzn.sbt.rmi.rmichat;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * Сервер чата
 */
public class ChatServer {
    public void lock() throws InterruptedException {
        synchronized (this) {
            wait();
        }
    }

    public static void main(String[] args) throws RemoteException, AlreadyBoundException, InterruptedException {
        Registry registryCentral = LocateRegistry.createRegistry(1099);
        ChatCentralNodeImpl node = new ChatCentralNodeImpl();
        IChatCentralNode centralNode = (IChatCentralNode) UnicastRemoteObject.exportObject(node, 0);
        registryCentral.bind("centralNode", centralNode);
        new ChatServer().lock();
    }
}
