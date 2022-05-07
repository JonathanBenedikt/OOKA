package org.bonn.ooka.LZU;

public class StandardLoggerFactory implements AbstractLoggerFactory{
    @Override
    public Logger createLogger() {
        return new Logger();
    }
}
