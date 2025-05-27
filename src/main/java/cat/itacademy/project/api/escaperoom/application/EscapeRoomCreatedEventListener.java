package cat.itacademy.project.api.escaperoom.application;

import cat.itacademy.project.shared.domain.dtos.DTO;
import cat.itacademy.project.shared.domain.dtos.escape_room.EscapeRoomDTO;
import cat.itacademy.project.shared.domain.events.EventListener;

public class EscapeRoomCreatedEventListener implements EventListener {
    @Override
    public void update(String topic, DTO data) {
        EscapeRoomDTO escapeRoomDTO = (EscapeRoomDTO) data;
        System.out.println("Escape room created: " + escapeRoomDTO);

    }
}
