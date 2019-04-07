package ru.rzn.sbt.rmi.rmifilereader;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringRmiClient {
    public static void main(String[] args) throws java.rmi.RemoteException {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config-client.xml");
        IFileReader fileReader = (IFileReader) context.getBean("fileReadService");
        if (fileReader.openFile("pom.xml")) {
            String line;
            while((line = fileReader.nextLine()) != null) {
                System.out.println(line);
            }
            fileReader.closeFile();
        }
    }
}
