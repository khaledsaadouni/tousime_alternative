package com.tousime_alternative.repository;

import com.tousime_alternative.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {
    List<Activity> findAllByProgramId(long id);

}

