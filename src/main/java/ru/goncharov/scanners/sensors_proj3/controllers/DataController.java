package ru.goncharov.scanners.sensors_proj3.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.goncharov.scanners.sensors_proj3.dto.DataDTO;
import ru.goncharov.scanners.sensors_proj3.models.Data;
import ru.goncharov.scanners.sensors_proj3.services.DataService;
import ru.goncharov.scanners.sensors_proj3.util.DataValidator;
import ru.goncharov.scanners.sensors_proj3.util.SensorErrorResponse;
import ru.goncharov.scanners.sensors_proj3.util.SensorNotFoundException;
import ru.goncharov.scanners.sensors_proj3.util.SensorNotRegisteredException;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/measurements")
public class DataController {

    private final DataService dataService;

    private final ModelMapper modelMapper;

    private final DataValidator dataValidator;
    @Autowired
    public DataController(DataService dataService, ModelMapper modelMapper, DataValidator dataValidator) {
        this.dataService = dataService;
        this.modelMapper = modelMapper;
        this.dataValidator = dataValidator;
    }
    @GetMapping
    public List<DataDTO> getMeasurements(){
        return dataService.findAll()
                .stream()
                .map(this::convertToDataDTO)
                .collect(Collectors.toList());
    }
    @GetMapping("/rainyDaysCount")
    public Map<String, Integer> getRainyDaysCount(){
        Map<String, Integer> count = new HashMap<>();
        count.put("rainyDaysCount", dataService.getRainyDaysCount());
        return count;
    }
    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addMeasurement(@RequestBody @Valid DataDTO dataDTO,
                                                     BindingResult bindingResult){
        dataValidator.validate(convertToData(dataDTO), bindingResult);
        if(bindingResult.hasErrors()){
            StringBuilder errorMsg = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMsg.append(error.getField()).append(" - ").append(error.getDefaultMessage())
                        .append(";");
                throw new SensorNotFoundException(errorMsg.toString());
            }
        }
        dataService.save(convertToData(dataDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<SensorErrorResponse> handleException(SensorNotFoundException e) {

        SensorErrorResponse response = new SensorErrorResponse
                ("Sensor with this id wasn't found",
                        System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND); //404
    }


    public DataDTO convertToDataDTO(Data data){
        return modelMapper.map(data, DataDTO.class);
    }

    public Data convertToData(DataDTO dataDTO){
        return modelMapper.map(dataDTO, Data.class);
    }
}
