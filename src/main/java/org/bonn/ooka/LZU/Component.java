package org.bonn.ooka.LZU;

import javax.print.DocFlavor;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class Component implements Runnable{

    private int iD;
    private String state;
    private boolean running;
    private Path jar_path;
    private URLClassLoader classLoader;

    public Component(int iD, Path jar_path){
        this.iD = iD;
        this.jar_path = jar_path;
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
        try{
            JarFile jarFile = new JarFile(String.valueOf(jar_path));
            Enumeration<JarEntry> e = jarFile.entries();

            URL[] url = { new URL("jar:file:" + jar_path+"!/") };
            classLoader = new URLClassLoader(url);

            while (e.hasMoreElements()) {
                JarEntry je = e.nextElement();
                if(je.isDirectory() || !je.getName().endsWith(".class")){
                    continue;
                }
                // -6 because of .class
                String className = je.getName().substring(0,je.getName().length()-6);
                className = className.replace('/', '.');
                Class c = classLoader.loadClass(className);

                //@start ueber relection aufrufen
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
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
