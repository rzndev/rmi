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
        IChatCentralNode localNode = (IChatCentralNode) UnicastRemoteObject.exportObject(new ChatCentralNodeImpl(), 1099);
        registryCentral.bind("centralNode", localNode);
        new ChatServer().lock();
    }
}
