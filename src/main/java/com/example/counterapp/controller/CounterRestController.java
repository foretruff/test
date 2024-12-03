package com.example.counterapp.controller;

import com.example.counterapp.model.dto.CounterThreadRequestDto;
import com.example.counterapp.service.CounterService;
import com.example.counterapp.service.ThreadManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/counter")
@RequiredArgsConstructor
public class CounterRestController {

    private final CounterService counterService;
    private final ThreadManagementService threadManagementService;

    @PostMapping("/threads")
    @ResponseStatus(HttpStatus.CREATED)
    public void manageThreads(@RequestBody CounterThreadRequestDto request) {
        threadManagementService.startThreads(
                request.getProducerThreads(),
                request.getConsumerThreads()
        );
    }

    @PutMapping("/value")
    @ResponseStatus(HttpStatus.OK)
    public void updateCounter(@RequestParam int value) {
        counterService.setCounterValue(value);
    }

}
