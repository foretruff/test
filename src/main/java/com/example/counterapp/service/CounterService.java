package com.example.counterapp.service;

import com.example.counterapp.model.entity.CounterEventEntity;
import com.example.counterapp.repository.jpa.CounterEventJpaRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
public class CounterService {

    private final CounterEventJpaRepository counterEventRepository;

    @Getter
    private final AtomicInteger counter = new AtomicInteger(50);

    @Value("${counter.min-value}")
    private int minValue;

    @Value("${counter.max-value}")
    private int maxValue;

    public synchronized boolean incrementCounter(String threadName) {
        int oldValue = counter.get();
        if (oldValue >= maxValue) return false;

        int newValue = counter.incrementAndGet();
        logCounterEvent("PRODUCER", threadName, oldValue, newValue);
        return true;
    }

    public synchronized boolean decrementCounter(String threadName) {
        int oldValue = counter.get();
        if (oldValue <= minValue) return false;

        int newValue = counter.decrementAndGet();
        logCounterEvent("CONSUMER", threadName, oldValue, newValue);
        return true;
    }

    public void setCounterValue(int value) {
        counter.set(value);
    }

    private void logCounterEvent(String eventType, String threadName, int oldValue, int newValue) {
        CounterEventEntity event = new CounterEventEntity(eventType, threadName, oldValue, newValue);
        counterEventRepository.save(event);
        System.out.printf("%s Thread: %s, Old Value: %d, New Value: %d%n",
                eventType, threadName, oldValue, newValue);
    }

    public boolean isCounterInBounds() {
        int currentValue = counter.get();
        return currentValue > minValue && currentValue < maxValue;
    }

}
