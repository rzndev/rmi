package ru.rzn.sbt.rmi.rmifilereader;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class RmiServer  {

    public void lock() throws InterruptedException {
        synchronized (this) {
            wait();
        }
    }

    private IFileReaderImpl fileReader;

    RmiServer() {
        fileReader = new IFileReaderImpl();
    }

    public void register() throws RemoteException, AlreadyBoundException {
        Registry registry = LocateRegistry.createRegistry(1099);
        IFileReader fileReaderRemote = (IFileReader) UnicastRemoteObject.exportObject(fileReader, 0);
        registry.bind("FileReaderService", fileReaderRemote);

    }

    public static void main(String[] args) throws java.rmi.RemoteException, java.rmi.AlreadyBoundException, InterruptedException {
        RmiServer server = new RmiServer();
        server.register();
        server.lock();
    }
}
