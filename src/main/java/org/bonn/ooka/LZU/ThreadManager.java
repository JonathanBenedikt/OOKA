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

/***
 * TODO HIER WEITER MACHEN: AUFTEILEN VON BYTECODE LOADEN und STARTEN, STATES, KILLEN (Achtung Increments/Decrements von ID), UNIT/Integration Tests
 */

public class ThreadManager {

    private LinkedList<Component> worker;
    public int idCounter = 1;

    public ThreadManager() {
    this.worker = new LinkedList<>();
    }

    public String getStateForComponent(int id){
        Component selectedComponent = getComponent(id);
        return selectedComponent.getState();
    }

    public void syncLoadedComponent(Component component){
        this.worker.add(component);
        return;
    }

    private Component getComponent(int id){
        for(Component aComponent : this.worker){
            if(aComponent.getID() == id) {
                return aComponent;
            }
        }
        return null;
    }

    public void injectLoggerForComponent(int id){
        Field loggerField = getComponent(id).getAnnotatedField(Inject.class);

        if(loggerField == null )
            return;

        if(loggerField.getType().getClass().equals(Logger.class.getClass()) ){

            try {
                loggerField.setAccessible(true);
                loggerField.set(this, new Logger());

            }catch (Exception ex){
               ex.printStackTrace();
            }

        }

    }



    public void startComponent(int id){
        Component component = getComponent(id);
        Thread thread = new Thread(component);
        thread.start();
    }

    public void showManagedComponents(){
        System.out.println("List of the currently managed Components by the LZU: ");
        System.out.println("ID\t\tState\t\tPath");
        for(Component component : worker){
            System.out.println(component.getID()+"\t\t"+component.getState()+"\t\t"+component.getPath());
        }
    }


    public void stopThread() {
    }

    public void stopComponent(int id){
        Component component = getComponent(id);
        if(component.getState() == "Running") {
            component.stopComponent();
        }
        //this.worker.remove(component);
        return;
    }

    public void deleteComponent(int id){
        try {
            stopComponent(id);
        }catch (Exception ex)
        {
            System.out.println("Component with ID "+id+" could not be stopped properly");
            ex.printStackTrace();
        }
        this.worker.remove(getComponent(id));
    }

}
