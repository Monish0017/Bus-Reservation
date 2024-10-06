package com.example.busreservation.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection = "schedules")
public class Schedule {
    @Id
    private String scheduleId;
    private String departureCity;
    private String destinationCity;
    private Date departureTime;
    private Date arrivalTime;
}
