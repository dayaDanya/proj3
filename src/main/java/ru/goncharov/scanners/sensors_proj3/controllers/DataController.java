package ru.goncharov.scanners.sensors_proj3.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.goncharov.scanners.sensors_proj3.dto.DataDTO;
import ru.goncharov.scanners.sensors_proj3.models.Data;
import ru.goncharov.scanners.sensors_proj3.services.DataService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/measurements")
public class DataController {

    private final DataService dataService;

    private final ModelMapper modelMapper;
    @Autowired
    public DataController(DataService dataService, ModelMapper modelMapper) {
        this.dataService = dataService;
        this.modelMapper = modelMapper;
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

    public DataDTO convertToDataDTO(Data data){
        return modelMapper.map(data, DataDTO.class);
    }
}
