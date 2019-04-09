package ru.rzn.sbt.rmi.rmichat;

import java.rmi.RemoteException;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Класс бизнес-логики клиента чата
 */
public class ChatLocalNodeImpl implements IChatLocalNode {

    private Queue<String> messages;
    private final Object lock;

    class LocalThread implements Runnable {

        @Override
        public void run() {
            synchronized (lock) {
                try {
                    while (true) {
                        lock.wait();
                        while(!messages.isEmpty()) {
                            System.out.println(messages.poll());
                        }
                    }
                } catch (InterruptedException e) {
                    System.out.println("Client has finished its work");
                }
            }
        }
    }

    ChatLocalNodeImpl() {
        messages = new ConcurrentLinkedQueue<>();
        lock = new Object();
        Thread chatLocalThread = new Thread(new LocalThread());
        chatLocalThread.start();
    }

    @Override
    public void receive(String message) throws RemoteException {
        messages.add(message);
        synchronized (lock) {
            lock.notify();
        }
    }
}
