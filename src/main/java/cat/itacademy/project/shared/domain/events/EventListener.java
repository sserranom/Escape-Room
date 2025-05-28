package cat.itacademy.project.shared.domain.events;


import cat.itacademy.project.shared.domain.dtos.DTO;

public interface EventListener {

    void update(String topic, DTO dto);


}
