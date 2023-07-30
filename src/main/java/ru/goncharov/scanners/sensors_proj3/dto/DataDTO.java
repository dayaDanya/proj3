package ru.goncharov.scanners.sensors_proj3.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

public class DataDTO {
    @NotEmpty
    @Min(value = -100)
    @Max(value = 100)
    private int value;
    @NotEmpty
    private boolean raining;
    @NotEmpty
    private SensorDTO sensor;

    public DataDTO(int value, boolean raining, SensorDTO sensor) {
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

    public SensorDTO getSensor() {
        return sensor;
    }

    public void setSensor(SensorDTO sensor) {
        this.sensor = sensor;
    }
}
