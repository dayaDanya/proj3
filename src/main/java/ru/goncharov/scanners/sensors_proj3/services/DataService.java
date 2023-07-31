package ru.goncharov.scanners.sensors_proj3.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.goncharov.scanners.sensors_proj3.models.Data;
import ru.goncharov.scanners.sensors_proj3.models.Sensor;
import ru.goncharov.scanners.sensors_proj3.repositories.DataRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class DataService {
    private final DataRepository dataRepository;
    @Autowired
    public DataService(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }
    public List<Data> findAll(){
        return dataRepository.findAll();
    }
    public Optional<Data> findOne(int id){
        return dataRepository.findById(id);
    }
    public Optional<Data> findBySensor(Sensor sensor){
        return dataRepository.findBySensor(sensor);
    }
    public int getRainyDaysCount(){
        return dataRepository.findByRaining(true).size();
    }
    @Transactional
    public void save(Data data){
        data.setMeasuredAt(LocalDateTime.now());
        dataRepository.save(data);
    }
    @Transactional
    public void delete(Data data){
        dataRepository.delete(data);
    }
}
