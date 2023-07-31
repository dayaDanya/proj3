package ru.goncharov.scanners.sensors_proj3.dto;

import ru.goncharov.scanners.sensors_proj3.models.Sensor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

public class DataDTO {
    //@NotEmpty
    @Min(value = -100)
    @Max(value = 100)
    private int value;
   // @NotEmpty
    private boolean raining;
   // @NotEmpty
    private Sensor sensor;

    public DataDTO(int value, boolean raining, Sensor sensor) {
        this.value = value;
        this.raining = raining;
        this.sensor = sensor;
    }

    public DataDTO() {
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean isRaining() {
        return raining;
    }

    public void setRaining(boolean raining) {
        this.raining = raining;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }
}
