package ru.rzn.sbt.rmi.rmichat;

import java.rmi.RemoteException;

/**
 * Класс бизнес-логики сервера чата
 */
public class ChatCentralNodeImpl implements IChatCentralNode {
    @Override
    public void register(Integer port, String nickname) throws RemoteException {

    }

    @Override
    public void send(String message) throws RemoteException {

    }
}
