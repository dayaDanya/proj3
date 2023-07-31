package ru.goncharov.scanners.sensors_proj3.util;

public class SensorNotRegisteredException extends RuntimeException{

    public SensorNotRegisteredException(String msg){
        super(msg);
    }
}
