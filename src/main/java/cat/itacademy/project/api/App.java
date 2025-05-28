package cat.itacademy.project.api;

import cat.itacademy.project.api.escaperoom.application.EscapeRoomCreatedEventListener;
import cat.itacademy.project.api.puzzle.PuzzleCompletedEventListener;
import cat.itacademy.project.api.puzzle.PuzzlePublishedEventListener;
import cat.itacademy.project.shared.domain.events.EventManager;

import java.util.List;

public class App {
    private static final EventManager eventManager = EventManager.getInstance();
    private static final List<String> topics = List.of(
            "escape_room.created",
            "puzzle.published",
            "puzzle.completed"
    );

    private static void createTopics() {
        eventManager.registerTopics(topics);
    }

    private static void subscribeToTopics() {
        eventManager.subscribe("escape_room.created", new EscapeRoomCreatedEventListener());
        eventManager.subscribe("puzzle.published", new PuzzlePublishedEventListener());
        eventManager.subscribe("puzzle.completed", new PuzzleCompletedEventListener());
    }

    public static void start() {
        createTopics();
        subscribeToTopics();
        System.out.println("Event Manager started and topics registered.");
        System.out.println("Topics: " + topics);
    }
}
