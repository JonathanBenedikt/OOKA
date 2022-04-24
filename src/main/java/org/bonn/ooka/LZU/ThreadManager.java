package org.bonn.ooka.LZU;


import org.bonn.ooka.buchungssystem.ss2022.Start;
import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import java.lang.reflect.*;
import java.lang.annotation.Annotation;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.Set;

public class ThreadManager {

    private LinkedList<Component> worker;
    public int idCounter = 1;

    public ThreadManager() {
    this.worker = new LinkedList<>();
    }

    private static void runMethodsWithAnnotation(Class<? extends Annotation> annotation) throws Exception{
        Reflections reflections = new Reflections(new ConfigurationBuilder()
                .setUrls(ClasspathHelper.forJavaClassPath())
                .setScanners(new MethodAnnotationsScanner()));

        Set<Method> methods = reflections.getMethodsAnnotatedWith(annotation);

        for (Method m : methods) {
            m.invoke(null);
        }

    }

    public void startComponentInThread(Component component){
        worker.add(component);
        Thread thread = new Thread(component);
        thread.start();
        try {
            runMethodsWithAnnotation(Start.class);
        }catch (Exception ex)
        {

        }
    }

    public void showManagedComponents(){
        System.out.println("List of the currently managed Components by the LZU: ");
        System.out.println("ID\t\tState\t\tPath");
        for(Component component : worker){
            System.out.println(component.getID()+"\t\t"+component.getState()+"\t\t"+component.getPath());
        }
    }

    public void startThread(Path pathToClass) {
        Component component= new Component(idCounter++,pathToClass);
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
