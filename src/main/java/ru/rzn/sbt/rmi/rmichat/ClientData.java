package ru.rzn.sbt.rmi.rmichat;

/**
 * Данные клиента чата
 */

public class ClientData {

    private int port;
    private String nickName;
    private String host;
    private IChatLocalNode chatLocalNode;

    /**
     * Получить порт соединения
     * @return
     */
    public int getPort() {
        return port;
    }

    /**
     * Установить порт соединения
     * @param port
     */
    public void setPort(int port) {
        this.port = port;
    }

    /**
     * Получить nick клиента
     * @return
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * Установить nick клиента
     * @param nickName
     */
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    /**
     * Получить хост клиента
     * @return
     */
    public String getHost() {
        return host;
    }

    /**
     * Установить хост клиент
     * @param host
     */
    public void setHost(String host) {
        this.host = host;
    }

    public IChatLocalNode getChatLocalNode() {
        return chatLocalNode;
    }

    public void setChatLocalNode(IChatLocalNode chatLocalNode) {
        this.chatLocalNode = chatLocalNode;
    }

    public ClientData(int port, String nickName, String host, IChatLocalNode chatLocalNode) {
        this.port = port;
        this.nickName = nickName;
        this.host = host;
        this.chatLocalNode = chatLocalNode;
    }
}
