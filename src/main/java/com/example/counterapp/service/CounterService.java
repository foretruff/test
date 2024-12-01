package com.example.counterapp.service;

@Service
@RequiredArgsConstructor
public class CounterService {

    private final CounterEventRepository counterEventRepository;

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
        CounterEvent event = new CounterEvent(eventType, threadName, oldValue, newValue);
        counterEventRepository.save(event);
        System.out.println(
                String.format("%s Thread: %s, Old Value: %d, New Value: %d",
                        eventType, threadName, oldValue, newValue)
        );
    }

    public boolean isCounterInBounds() {
        int currentValue = counter.get();
        return currentValue > minValue && currentValue < maxValue;
    }

}
