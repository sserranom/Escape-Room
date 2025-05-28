package cat.itacademy.project.shared.domain.events;

import cat.itacademy.project.shared.domain.dtos.DTO;

import java.util.List;

public class Messenger {
    private final EventManager publisher;


    public Messenger(EventManager publisher) {
        this.publisher = EventManager.getInstance();
        this.publisher.registerTopics(List.of("customer", "order", "product"));
    }

    public void subscribe(String topic, EventListener listener) {
        publisher.subscribe(topic, listener);
    }

    public void execute(String topic, DTO message) {
//        if (customers.isEmpty()) {
//            throw new IllegalStateException("No customers available to notify.");
//        }
        publisher.publish(topic, message);
    }
}
