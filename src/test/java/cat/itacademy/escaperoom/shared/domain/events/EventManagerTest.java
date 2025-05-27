package cat.itacademy.escaperoom.shared.domain.events;

import cat.itacademy.project.shared.domain.dtos.dto;
import cat.itacademy.project.shared.domain.events.EventListener;
import cat.itacademy.project.shared.domain.events.EventManager;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

class EventManagerTest {

    @Test
    void testSingletonPattern() {
        EventManager instance1 = EventManager.getInstance();
        instance1.registerTopics(List.of("test.topic"));
        EventManager instance2 = EventManager.getInstance();
        instance2.registerTopics(List.of("test.topic"));

        assertSame(instance1, instance2);
    }

    @Test
    void testTopicInitialization() {
        EventManager instance1 = EventManager.getInstance();
        instance1.registerTopics(List.of("topic1"));
        TestListener listener = new TestListener();

        instance1.subscribe("topic1", listener);

        instance1.publish("topic1", new TestDTO("test message"));

        assertEquals(1, listener.getUpdateCount());
        assertEquals("test message", listener.getLastMessage());

        EventManager instance2 = EventManager.getInstance();
        instance2.registerTopics(List.of("topic2"));

        assertSame(instance1, instance2);

        TestListener listener2 = new TestListener();
        instance2.subscribe("topic2", listener2);

        instance2.publish("topic2", new TestDTO("test message 2"));

        assertEquals(1, listener2.getUpdateCount());
        assertEquals("test message 2", listener2.getLastMessage());
    }

    @Test
    void testThreadSafety() throws InterruptedException {
        int threadCount = 10;

        EventManager eventManager = EventManager.getInstance();
        eventManager.registerTopics(List.of("thread.test"));

        TestListener listener = new TestListener();
        eventManager.subscribe("thread.test", listener);

        CountDownLatch startLatch = new CountDownLatch(1);

        CountDownLatch finishLatch = new CountDownLatch(threadCount);

        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);

        for (int i = 0; i < threadCount; i++) {
            final int threadId = i;
            executorService.submit(() -> {
                try {
                    startLatch.await();

                    EventManager instance = EventManager.getInstance();
                    instance.registerTopics(List.of("thread.test"));

                    instance.publish("thread.test", new TestDTO("message from thread " + threadId));
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    finishLatch.countDown();
                }
            });
        }

        startLatch.countDown();

        finishLatch.await();

        executorService.shutdown();

        assertEquals(threadCount, listener.getUpdateCount());
    }

    private record TestDTO(String message) implements dto {
    }

    private static class TestListener implements EventListener {
        private final AtomicInteger updateCount = new AtomicInteger(0);
        private String lastMessage;

        @Override
        public void update(String topic, dto dto) {
            updateCount.incrementAndGet();
            if (dto instanceof TestDTO) {
                lastMessage = ((TestDTO) dto).message();
            }
        }

        public int getUpdateCount() {
            return updateCount.get();
        }

        public String getLastMessage() {
            return lastMessage;
        }
    }
}
