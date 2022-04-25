package org.bonn.ooka.LZU;

import javax.print.DocFlavor;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class Component implements Runnable{

    private int iD;
    private String state;
    private boolean running;
    private Path jar_path;
    private CustomClassLoader classLoader;
    private LinkedList<Class> loadedClasses;

    public Component(int iD, Path jar_path){
        this.iD = iD;
        this.jar_path = jar_path;
        this.loadedClasses = new LinkedList<>();
    }

    public int getID(){
        return this.iD;
    }

    public String getState(){
        return this.state;
    }

    public boolean isRunning(){
        return this.running;
    }

    public String getPath(){
        return this.jar_path.toString();
    }

    private void load_class() {
        //Todo nachbessern, wo der Fehler behandelt wird. Designtechnisch besser im Userinterface?
        classLoader = new CustomClassLoader(Thread.currentThread().getContextClassLoader());
        classLoader.loadJar(this.jar_path.toString());
    }

    public void printLoadedClasses(){
        System.out.println("For the component with the ID "+iD+", the following classes are loaded:");
        for(Class c : loadedClasses){
            System.out.println("Class with the name "+c.getName());
        }
    }
    @Override
    public void run() {
        //Todo load_class aufrufen
        this.state = "Starting";
        load_class();
        return;
    }

    /*ToDO:
    Übergangsfunktionen States
    ClassLoader
    Backup-Mechanismus
    Start- und Stop-Möglichkeiten
    ID
    Packages
     */
}
