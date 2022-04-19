package org.bonn.ooka.LZU;

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

    public Component(Path jar_path){
        this.jar_path = jar_path;
    }

    private void load_class(Path pathToJar) {
        //Todo nachbessern, wo der Fehler behandelt wird. Designtechnisch besser im Userinterface?
        try{
            JarFile jarFile = new JarFile(String.valueOf(pathToJar));
            Enumeration<JarEntry> e = jarFile.entries();

            URL[] urls = { new URL("jar:file:" + pathToJar+"!/") };
            URLClassLoader cl = URLClassLoader.newInstance(urls);

            while (e.hasMoreElements()) {
                JarEntry je = e.nextElement();
                if(je.isDirectory() || !je.getName().endsWith(".class")){
                    continue;
                }
                // -6 because of .class
                String className = je.getName().substring(0,je.getName().length()-6);
                className = className.replace('/', '.');
                Class c = cl.loadClass(className);

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
        System.out.println("I'm alive!");
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
