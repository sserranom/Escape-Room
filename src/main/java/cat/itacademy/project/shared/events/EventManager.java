package cat.itacademy.project.shared.events;

import cat.itacademy.project.shared.EventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventManager {
    private static Map<String, List<EventListener>> subscribers = new HashMap<>();

    private EventManager(String... eventTypes) {
        for (String eventType : eventTypes) {
            subscribers.put(eventType, new ArrayList<>());
        }
    }


    public void subscribe(String eventType, EventListener listener) {
        List<EventListener> allSubscribers = this.subscribers.get(eventType);
        allSubscribers.add(listener);
    }

    public void unsubscribe(String eventType, EventListener listener) {
        List<EventListener> users = subscribers.get(eventType);
        users.remove(listener);
    }

    public void notify(String eventType, Record dto) {
        List<EventListener> users = subscribers.get(eventType);
        for (EventListener listener : users) {
            listener.update(eventType, dto);
        }
    }
}