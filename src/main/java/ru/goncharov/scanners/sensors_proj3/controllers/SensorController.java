package ru.goncharov.scanners.sensors_proj3.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.goncharov.scanners.sensors_proj3.dto.SensorDTO;
import ru.goncharov.scanners.sensors_proj3.models.Sensor;
import ru.goncharov.scanners.sensors_proj3.services.SensorService;
import ru.goncharov.scanners.sensors_proj3.util.SensorErrorResponse;
import ru.goncharov.scanners.sensors_proj3.util.SensorNotRegisteredException;
import ru.goncharov.scanners.sensors_proj3.util.SensorValidator;

import javax.validation.Valid;
import java.util.List;

@RestController //каждый метод возвращает данные
@RequestMapping("/sensors")
public class SensorController {

    private final SensorService sensorService;

    private final ModelMapper modelMapper;

    private final SensorValidator sensorValidator;

    @Autowired
    public SensorController(SensorService sensorService, ModelMapper modelMapper, SensorValidator sensorValidator) {
        this.sensorService = sensorService;
        this.modelMapper = modelMapper;
        this.sensorValidator = sensorValidator;
    }

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> register(@RequestBody @Valid SensorDTO sensorDTO,
                                                   BindingResult bindingResult){
        sensorValidator.validate(sensorDTO, bindingResult);
        if(bindingResult.hasErrors()){
            StringBuilder errorMsg = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMsg.append(error.getField()).append(" - ").append(error.getDefaultMessage())
                        .append(";");
                throw new SensorNotRegisteredException(errorMsg.toString());
            }
        }
        sensorService.save(convertToSensor(sensorDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<SensorErrorResponse> handleException(SensorNotRegisteredException e) {

        SensorErrorResponse response = new SensorErrorResponse
                (e.getMessage(),
                        System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST); //404
    }

    private Sensor convertToSensor(SensorDTO sensorDTO){
        return modelMapper.map(sensorDTO, Sensor.class);
    }
}
