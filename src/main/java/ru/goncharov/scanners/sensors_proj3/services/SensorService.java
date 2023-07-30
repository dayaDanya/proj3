package ru.goncharov.scanners.sensors_proj3.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.goncharov.scanners.sensors_proj3.models.Sensor;
import ru.goncharov.scanners.sensors_proj3.repositories.SensorRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class SensorService{
    private final SensorRepository sensorRepository;
    @Autowired
    public SensorService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    public List<Sensor> findAll(){
        return sensorRepository.findAll();
    }

    public Optional<Sensor> findOne(int id){
        return sensorRepository.findById(id);
    }

    @Transactional
    public void save(Sensor sensor){
        sensorRepository.save(sensor);
    }

    @Transactional
    public void delete(Sensor sensor){
        sensorRepository.delete(sensor);
    }

    public Optional<Sensor> findByName(String name){
        return sensorRepository.findByName(name);
    }
}
