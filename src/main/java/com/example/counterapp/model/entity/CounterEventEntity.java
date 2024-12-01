package com.example.counterapp.model.entity;

@Data
@Entity
@Table(name = "counter_events")
@NoArgsConstructor
public class CounterEventEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "event_type")
    private String eventType;

    @Column(name = "thread_name")
    private String threadName;

    @Column(name = "old_value")
    private Integer oldValue;

    @Column(name = "new_value")
    private Integer newValue;

    @Column(name = "timestamp")
    private LocalDateTime timestamp;

    public CounterEvent(String eventType, String threadName, Integer oldValue, Integer newValue) {
        this.eventType = eventType;
        this.threadName = threadName;
        this.oldValue = oldValue;
        this.newValue = newValue;
        this.timestamp = LocalDateTime.now();
    }

}
