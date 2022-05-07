package org.bonn.ooka.LZU;

import java.io.Serializable;

public class ComponentState implements Serializable {
    private int id;
    private String state;
    private String jar_path;

    public ComponentState(int initID, String initState, String initJar_Path){
        id = initID;
        state = initState;
        jar_path = initJar_Path;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getJar_path() {
        return jar_path;
    }

    public void setJar_path(String jar_path) {
        this.jar_path = jar_path;
    }
}
