package org.bonn.ooka.LZU;

import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class CustomClassLoader extends ClassLoader{

    public CustomClassLoader(ClassLoader parent){
        super(parent);
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException{
    System.out.println("The Class Loading started for "+name);
    return super.loadClass(name);
    }

    public LinkedList<Class> loadJar(String jar_path){
        LinkedList<Class> loadedClasses = new LinkedList<>();
        try{
            JarFile jarFile = new JarFile(String.valueOf(jar_path));
            Enumeration<JarEntry> e = jarFile.entries();

            URL[] url = { new URL("jar:file:" + jar_path+"!/") };
            while (e.hasMoreElements()) {
                JarEntry je = e.nextElement();
                if(je.isDirectory() || !je.getName().endsWith(".class")){
                    continue;
                }
                // -6 because of .class
                String className = je.getName().substring(0,je.getName().length()-6);
                className = className.replace('/', '.');
                Class c = this.loadClass(className);
                loadedClasses.add(c);
                System.out.println("Class with the name "+c.getName()+" has been loaded");
                //@start ueber relection aufrufen

            }
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return loadedClasses;
    }

    private byte[] loadClassData(String name) throws IOException {
        InputStream stream = getClass().getClassLoader().getResourceAsStream(name);
        int size = stream.available();
        byte buffer[] = new byte[size];
        DataInputStream input = new DataInputStream(stream);
        input.readFully(buffer);
        input.close();
        return buffer;
    }

    private Class<?> getClass(String name) throws ClassNotFoundException {
        String file = name.replace('.', File.separatorChar) + ".class";
        byte[] byteArray = null;
        try{
            byteArray = loadClassData(file);
            Class<?> c = defineClass(name, byteArray, 0, byteArray.length);
            resolveClass(c);
            return c;
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }

}
