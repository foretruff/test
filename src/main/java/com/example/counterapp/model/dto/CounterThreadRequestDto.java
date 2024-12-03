package com.example.counterapp.model.dto;

import lombok.Data;

@Data
public class CounterThreadRequestDto {

    private int producerThreads;

    private int consumerThreads;

}
