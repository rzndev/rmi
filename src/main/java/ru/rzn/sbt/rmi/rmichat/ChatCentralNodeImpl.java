package ru.rzn.sbt.rmi.rmichat;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.RemoteServer;
import java.rmi.server.ServerNotActiveException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;

/**
 * Класс бизнес-логики сервера чата
 */
public class ChatCentralNodeImpl implements IChatCentralNode {

    List<ClientData> clients;

    ChatCentralNodeImpl() {
        clients = new CopyOnWriteArrayList<>();
    }

    @Override
    public void register(Integer port, String nickname) throws RemoteException {
        try {
            String hostName = RemoteServer.getClientHost();
            Registry registryLocal = LocateRegistry.getRegistry(hostName, port);
            IChatLocalNode chatLocalNode = (IChatLocalNode) registryLocal.lookup("localNode") ;
            clients.add(new ClientData(port, nickname, hostName, chatLocalNode));
        } catch (ServerNotActiveException e) {
            throw new RemoteException(e.getMessage(), e);
        } catch (NotBoundException e) {
            throw new RemoteException(e.getMessage(), e);
        }
    }

    @Override
    public void send(String message) throws RemoteException {
        Consumer<ClientData> consumer = (t->{
            try {
                t.getChatLocalNode().receive(message);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        });
        clients.forEach(consumer);
    }
}
