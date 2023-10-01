package ru.goncharov.scanners.sensors_proj3.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class SensorDTO {
    @NotEmpty
    @Size(min = 3, max = 30, message = "Длина названия от 3 до 30 символов")
    private String name;

    /// конструкторы не нужны т.к. jackson переводит JSON с помощью сеттеров
    
//    public SensorDTO(String name) {
//        this.name = name;
//    }
//
//    public SensorDTO() {
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
