package ru.rzn.sbt.rmi.rmifilereader;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RmiClient {
    public static void main(String[] args) throws java.rmi.RemoteException, java.rmi.NotBoundException{
        Registry registry = LocateRegistry.getRegistry("172.18.1.15");
        IFileReader fileReader = (IFileReader)registry.lookup("server.filereader");
        if (fileReader.openFile("pom.xml")) {
            String line;
            while((line = fileReader.nextLine()) != null) {
                System.out.println(line);
            }
            fileReader.closeFile();
        }
    }
}
