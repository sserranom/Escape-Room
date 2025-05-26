package cat.itacademy.project.shared.domain.events;


import cat.itacademy.project.shared.domain.dtos.dto;

public interface EventListener {

    void update(String topic, dto dto);
}
