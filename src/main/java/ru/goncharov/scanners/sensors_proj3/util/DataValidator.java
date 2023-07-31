package ru.goncharov.scanners.sensors_proj3.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.goncharov.scanners.sensors_proj3.dto.DataDTO;
import ru.goncharov.scanners.sensors_proj3.models.Data;
import ru.goncharov.scanners.sensors_proj3.services.DataService;
import ru.goncharov.scanners.sensors_proj3.services.SensorService;

@Component
public class DataValidator implements Validator {

    private final SensorService sensorService;
    @Autowired
    public DataValidator(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Data.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Data data = (Data) target;
        String name = data.getSensor().getName();
        if(sensorService.findByName(name).isEmpty()){
            errors.rejectValue("sensor", "",
                    "Сенсор с таким названием не найден");
        }
    }
}
