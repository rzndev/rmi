package ru.rzn.sbt.rmi.rmifilereader;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringRmiServer {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config-server.xml");
        System.out.println("Waiting for Request from Client ...");
    }
}
