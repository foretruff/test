package com.example.counterapp.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public class CounterEventJpaRepository extends JpaRepository<CounterEvent, Long> {

}
