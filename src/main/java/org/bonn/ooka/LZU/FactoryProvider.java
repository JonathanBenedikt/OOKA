package org.bonn.ooka.LZU;

public class FactoryProvider {

    public static AbstractLoggerFactory getFactory(String factoryName){

        if(factoryName == "StandardLoggerFactory"){
            return new StandardLoggerFactory();
        }
        return null;
    }
}
