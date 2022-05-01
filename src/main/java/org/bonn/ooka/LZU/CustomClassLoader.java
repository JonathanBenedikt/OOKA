package org.bonn.ooka.LZU;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class CustomClassLoader extends URLClassLoader{

    private String jar_path;
    //private ClassLoader jdkLoader;
    //private Map<String, String> classPathMap = new HashMap<>();

    public CustomClassLoader(URL[] urls, ClassLoader parent) {
        super(urls,parent);
        if(urls[0] != null)
            this.jar_path = urls[0].toString().substring(5);
    }

    /*
    public CustomClassLoader(String className ,String classPath){
        classPathMap.put(className, classPath);
    }

    public CustomClassLoader(ClassLoader parent){
        super(parent);
    }

    public void setClassPathMap(String className, String pathToClass){

       classPathMap.put(className,pathToClass);
       //"target/classes/org/bonn/ooka/cachecomponent/CachePort.class"
    }

    @Override
    public Class<?> findClass(String name) throws ClassNotFoundException{
        String classPath = classPathMap.get(name);
        File file = new File(classPath);
        if (! file.exists()) {
            throw new ClassNotFoundException();
        }
        byte[] classBytes = getClassData(file);
        if (classBytes == null || classBytes.length == 0) {
            throw new ClassNotFoundException();
        }
        return defineClass(classBytes, 0, classBytes.length);
    }
    /*
     */

    /*
    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException{
    System.out.println("The Class Loading started for "+name);
    //return super.loadClass(name);
        Class result = null;
        try{
            result = jdkLoader.loadClass(name);
        } catch (Exception ex){

        }
        if(result != null){
            return result;
        }
        String classPath = classPathMap.get(name);
        File file = new File(classPath);

        if(!file.exists()) {
            throw new ClassNotFoundException();
        }

        byte[] classBytes = getClassData(file);
        if(classBytes == null || classBytes.length == 0){
            throw new ClassNotFoundException();
        }
        return defineClass(classBytes, 0, classBytes.length);
    }
*/


    private byte[] getClassData(File file){
        try (InputStream ins = new FileInputStream(file); ByteArrayOutputStream baos = new
                ByteArrayOutputStream()) {
            byte[] buffer = new byte[4096];
            int bytesNumRead = 0;
            while ((bytesNumRead = ins.read(buffer)) != -1) {
                baos.write(buffer, 0, bytesNumRead);
            }
            return baos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new byte[] {};

    }

    public LinkedList<Class> loadJar(){
        LinkedList<Class> loadedClasses = new LinkedList<>();
        try{
            JarFile jarFile = new JarFile(String.valueOf(this.jar_path));
            Enumeration<JarEntry> e = jarFile.entries();

            URL[] url = { new URL("jar:file:" + this.jar_path+"!/") };
            while (e.hasMoreElements()) {
                JarEntry je = e.nextElement();
                if(je.isDirectory() || !je.getName().endsWith(".class")){
                    continue;
                }
                // -6 because of .class
                String className = je.getName().substring(0,je.getName().length()-6);
                className = className.replace('/', '.');
                //setClassPathMap(className, "target/classes/"+je.getName());
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

    public Class<?> getClass(String name) throws ClassNotFoundException {
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
