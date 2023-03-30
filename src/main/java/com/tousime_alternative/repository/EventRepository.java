package com.tousime_alternative.repository;

import com.tousime_alternative.model.Event;
import io.micrometer.observation.Observation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
}
