package com.example.counterapp.controller;

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
