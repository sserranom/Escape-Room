package cat.itacademy.project.shared.domain.events;

import cat.itacademy.project.shared.domain.dtos.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventManager {
    private final Map<String, List<EventListener>> listeners = new HashMap<>();

    public EventManager(List<String> topics) {
        for (String topic : topics) {
            listeners.put(topic, new ArrayList<>());
        }
    }

    public void subscribe(String topic, EventListener stockEventListener) {
        List<EventListener> subscribers = listeners.get(topic);
        subscribers.add(stockEventListener);
    }

    public void publish(String topic, dto message) {
        List<EventListener> subscribers = listeners.get(topic);
        for (EventListener stockEventListener : subscribers) {
            stockEventListener.update(topic, message);
        }
    }
}