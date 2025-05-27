package cat.itacademy.project.shared.domain.events;

import cat.itacademy.project.shared.domain.dtos.DTO;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class EventManager {
    private static final Map<String, List<EventListener>> listeners = new ConcurrentHashMap<>();
    private static volatile EventManager instance;

    // Private constructor to prevent direct instantiation
    private EventManager() {
    }

    // Thread-safe singleton implementation with double-checked locking
    public static EventManager getInstance() {
        if (instance == null) {
            synchronized (EventManager.class) {
                if (instance == null) {
                    instance = new EventManager();
                }
            }
        }
        return instance;
    }

    // Register topics
    public void registerTopics(List<String> topics) {
        for (String topic : topics) {
            if (!listeners.containsKey(topic)) {
                listeners.put(topic, new CopyOnWriteArrayList<>());
            }
        }
    }

    public void subscribe(String topic, EventListener listener) {
        List<EventListener> subscribers = listeners.get(topic);
        if (subscribers != null) {
            subscribers.add(listener);
        }
    }

    public void publish(String topic, DTO message) {
        List<EventListener> subscribers = listeners.get(topic);
        if (subscribers != null) {
            for (EventListener listener : subscribers) {
                listener.update(topic, message);
            }
        }
    }
}
