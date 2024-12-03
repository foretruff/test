package com.example.counterapp.repository.jpa;

import com.example.counterapp.model.entity.CounterEventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CounterEventJpaRepository extends JpaRepository<CounterEventEntity, Long> {

}
