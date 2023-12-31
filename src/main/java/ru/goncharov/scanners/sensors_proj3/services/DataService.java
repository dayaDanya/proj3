package ru.goncharov.scanners.sensors_proj3.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.goncharov.scanners.sensors_proj3.models.Data;
import ru.goncharov.scanners.sensors_proj3.models.Sensor;
import ru.goncharov.scanners.sensors_proj3.repositories.DataRepository;
import ru.goncharov.scanners.sensors_proj3.repositories.SensorRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class DataService {
    private final DataRepository dataRepository;

    private final SensorRepository sensorRepository;
    @Autowired
    public DataService(DataRepository dataRepository, SensorRepository sensorRepository) {
        this.dataRepository = dataRepository;
        this.sensorRepository = sensorRepository;
    }
    public List<Data> findAll(){
        return dataRepository.findAll();
    }
    public Optional<Data> findOne(int id){
        return dataRepository.findById(id);
    }
    public int getRainyDaysCount(){
        return dataRepository.findByRaining(true).size();
    }
    @Transactional
    public void save(Data data){
        Sensor sensor = sensorRepository.findByName(
                data.getSensor().getName()).get();
        data.setSensor(sensor);
        data.setMeasuredAt(LocalDateTime.now());
        dataRepository.save(data);
    }
    @Transactional
    public void delete(Data data){
        dataRepository.delete(data);
    }
}
