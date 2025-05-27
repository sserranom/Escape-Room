package cat.itacademy.project.shared.domain.events;


import cat.itacademy.project.shared.domain.dtos.DTO;
import cat.itacademy.project.shared.domain.dtos.escape_room.EscapeRoomDTO;

public interface EventListener {

    void update(String topic, DTO dto);


}
