package org.bonn.ooka.LZU;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.nio.file.Path;
import java.util.LinkedList;

public class Component implements Runnable, Serializable {

    private int iD;
    private String state;
    private Path jar_path;
    private CustomClassLoader classLoader;
    private LinkedList<Class> classes;

    public Component(int iD, Path jar_path){
        this.iD = iD;
        this.jar_path = jar_path;
        this.state = "Initializing";
    }

    public int getID(){
        return this.iD;
    }

    public String getState(){
        return this.state;
    }

    public String getPath(){
        return this.jar_path.toString();
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    /***
     * Start component.
     * Loads Bytecode from Component
     * Calls the @start method at runtime using reflections
     */
    public void run() {
        //Todo load_class aufrufen
        startComponent();
        return;
    }

    void load_component() {
        //Todo nachbessern, wo der Fehler behandelt wird. Designtechnisch besser im Userinterface?
        try {
            this.classLoader = new CustomClassLoader(new URL[]{new URL("file://"+jar_path.toString())}, Thread.currentThread().getContextClassLoader());
            this.classes = classLoader.loadJar();
            this.state = "Loaded";
        }catch (Exception ex){
            ex.printStackTrace();
        }
        //this.classes = classLoader.loadJar(this.jar_path.toString());

    }

    /***
     * Calls startmethod from loaded Classes at runtime via reflections.
     */
    private void startComponent() {
        Method method = getAnnotatedMethod(Start.class);
        try {
            method.invoke(null);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    void stopComponent() {
        Method method = getAnnotatedMethod(Stop.class);
        try {
            method.invoke(null);
            this.state = "Stopped";
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    public Field getAnnotatedField(Class<? extends Annotation> annotation){
        for (Class aClass : classes) {
            for (final Field field: aClass.getDeclaredFields()) {
                if(field.isAnnotationPresent(annotation)) {
                    return field;
                }
            }
        }
        return null;
    }

    private Method getAnnotatedMethod(Class<? extends Annotation> annotation) {
        for (Class aClass : classes) {
            for (final Method method: aClass.getDeclaredMethods()) {
                if(method.isAnnotationPresent(annotation)) {
                    return method;
                }
            }
        }
        return null;
    }

}
