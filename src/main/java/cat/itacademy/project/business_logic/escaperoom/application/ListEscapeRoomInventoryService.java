package cat.itacademy.project.business_logic.escaperoom.application;

import cat.itacademy.project.business_logic.escaperoom.domain.EscapeRoomRepository;
import cat.itacademy.project.shared.domain.dtos.escape_room.EscapeRoomInventoryDto;

public class ListEscapeRoomInventoryService {
    private final EscapeRoomRepository repo;

    public ListEscapeRoomInventoryService(EscapeRoomRepository repo) {
        this.repo = repo;
    }

    public EscapeRoomInventoryDto execute(int escapeRoomId) {
        return repo.findInventoryByEscapeRoomId(escapeRoomId);

    }
}
