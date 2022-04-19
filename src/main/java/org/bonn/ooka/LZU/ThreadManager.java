package org.bonn.ooka.LZU;

import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;

public class ThreadManager {

    private Runnable[] worker;

    public ThreadManager() {

    }

    public void startThread(Path pathToClass) {
        Component component= new Component(pathToClass);
        Thread thread = new Thread(component);
        thread.start();
        thread.interrupt();

        System.out.println(thread.getState());
        System.out.println(thread.isAlive());
        System.out.println(thread.getId());
        System.out.println(thread.getState());

    }

    public void stopThread() {

    }

}
