package ru.rzn.sbt.rmi.rmifilereader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.rmi.RemoteException;

public class IFileReaderImpl implements IFileReader {

    BufferedReader in;


    @Override
    public boolean openFile(String filename) throws RemoteException {
        try {
            in = new BufferedReader(new FileReader(filename));
        } catch (FileNotFoundException ex) {
            throw new RemoteException(ex.toString(), ex);
        }
        return true;
    }

    @Override
    public String nextLine() throws RemoteException {
        String line;
        if (in == null) {
            throw new RemoteException("File not opened");
        }
        try {
            line = in.readLine();
        } catch (IOException ex) {
            throw new RemoteException(ex.toString(), ex);
        }
        return line;
    }

    @Override
    public boolean closeFile() throws RemoteException {
        if (in == null)
            return false;
        try {
            in.close();
        } catch (IOException ex) {
            throw new RemoteException(ex.toString(), ex);
        }
        return true;
    }
}
