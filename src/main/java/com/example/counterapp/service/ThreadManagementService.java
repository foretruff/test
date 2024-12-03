package com.example.counterapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
@RequiredArgsConstructor
public class ThreadManagementService {

    private final CounterService counterService;
    private ExecutorService executorService;

    @Value("${thread.min-sleep}")
    private int minSleep;

    @Value("${thread.max-sleep}")
    private int maxSleep;

    public void startThreads(int producerCount, int consumerCount) {
        // Shutdown existing threads if any
        if (executorService != null && !executorService.isShutdown()) {
            executorService.shutdownNow();
        }

        executorService = Executors.newFixedThreadPool(producerCount + consumerCount);

        // Start Producer Threads
        for (int i = 0; i < producerCount; i++) {
            executorService.submit(new ProducerThread("Producer-" + i));
        }

        // Start Consumer Threads
        for (int i = 0; i < consumerCount; i++) {
            executorService.submit(new ConsumerThread("Consumer-" + i));
        }
    }

    private class ProducerThread implements Runnable {
        private final String name;
        private final Random random = new Random();

        public ProducerThread(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            try {
                while (counterService.isCounterInBounds()) {
                    if (counterService.incrementCounter(name)) {
                        Thread.sleep(random.nextInt(minSleep, maxSleep));
                    } else {
                        break;
                    }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private class ConsumerThread implements Runnable {
        private final String name;
        private final Random random = new Random();

        public ConsumerThread(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            try {
                while (counterService.isCounterInBounds()) {
                    if (counterService.decrementCounter(name)) {
                        Thread.sleep(random.nextInt(minSleep, maxSleep));
                    } else {
                        break;
                    }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

}