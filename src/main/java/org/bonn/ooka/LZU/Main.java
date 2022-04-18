package org.bonn.ooka.LZU;

public class Main {
    public static void main(String[] args){
        ComponentThread componentThread = new ComponentThread();
        Thread thread = new Thread(componentThread);
        thread.start();
        thread.interrupt();

        System.out.println(thread.getState());
        System.out.println(thread.isAlive());
        System.out.println(thread.getId());
        System.out.println(thread.getState());
    }
}
