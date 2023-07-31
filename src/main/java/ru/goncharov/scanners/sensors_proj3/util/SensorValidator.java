package ru.goncharov.scanners.sensors_proj3.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.goncharov.scanners.sensors_proj3.dto.SensorDTO;
import ru.goncharov.scanners.sensors_proj3.models.Sensor;
import ru.goncharov.scanners.sensors_proj3.services.SensorService;

@Component
public class SensorValidator implements Validator {

    private final SensorService sensorService;

    @Autowired
    public SensorValidator(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return SensorDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Sensor sensor = (Sensor) target;
        System.out.println(sensor.getName());
        if (sensorService.findByName(sensor.getName()).isPresent()) {
            errors.rejectValue("name", "",
                    "Сенсор с таким названием уже существует");
        }
    }
}
