package ru.rzn.sbt.rmi.rmifilereader;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class RmiServer  {
    public static void main(String[] args) throws java.rmi.RemoteException, java.rmi.AlreadyBoundException{
        Registry registry = LocateRegistry.createRegistry(1099);
        IFileReader fileReader = (IFileReader) UnicastRemoteObject.exportObject(new IFileReaderImpl(), 0);
        registry.bind("FileReaderService", fileReader);
    }
}
