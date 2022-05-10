package org.bonn.ooka.LZU;


import java.io.*;
import java.lang.reflect.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;

/***
 * TODO HIER WEITER MACHEN: AUFTEILEN VON BYTECODE LOADEN und STARTEN, STATES, KILLEN (Achtung Increments/Decrements von ID), UNIT/Integration Tests
 */

public class ThreadManager {

    private LinkedList<Component> worker;
    public int idCounter = 1;

    private static final String stateFilePath = "LZU_savedStates.dat";
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

    public void saveStatesToFile(){
       try{
         FileOutputStream fos = new FileOutputStream(stateFilePath,false);
         ObjectOutputStream oos = new ObjectOutputStream(fos);

         for(Component comp : worker){
            ComponentState state = new ComponentState(comp.getID(),comp.getState(),comp.getPath().toString());
            oos.writeObject(state);
         }

         oos.close();
         fos.close();

       }catch(Exception ex){
           ex.printStackTrace();
       }
    }

    public void restoreStatesFromFile(){
        try {
            FileInputStream fin = new FileInputStream(stateFilePath);
            ObjectInputStream oin = new ObjectInputStream(fin);
            worker = new LinkedList<>();

            do{
                ComponentState savedComp = (ComponentState)oin.readObject();
                Path jar_path = Paths.get(savedComp.getJar_path());
                Component comp = new Component(savedComp.getId(),jar_path);
                comp.setState(savedComp.getState());



                switch (comp.getState()){
                    case "Loaded":
                        comp.load_component();
                        syncLoadedComponent(comp);
                        injectLoggerForComponent(comp.getID());
                        break;
                    case "Running":
                        comp.load_component();
                        syncLoadedComponent(comp);
                        injectLoggerForComponent(comp.getID());
                        startComponent(comp.getID());
                        break;
                    case "Stopped":
                        comp.load_component();
                        comp.setState("Stopped");
                        syncLoadedComponent(comp);
                        injectLoggerForComponent(comp.getID());
                       break;
                    default:
                        break;
                }

            }while(fin.available() > 0);

            oin.close();
            fin.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }

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
        getComponent(id).setState("Running");
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
        else{
            component.setState("Stopped");
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
