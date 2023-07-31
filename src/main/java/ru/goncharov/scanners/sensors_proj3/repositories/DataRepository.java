package ru.goncharov.scanners.sensors_proj3.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.goncharov.scanners.sensors_proj3.models.Data;
import ru.goncharov.scanners.sensors_proj3.models.Sensor;

import java.util.List;
import java.util.Optional;
@Repository
public interface DataRepository extends JpaRepository<Data, Integer> {
    Optional<Data> findBySensor(Sensor sensor);

    List<Data> findByRaining(boolean raining);
}
