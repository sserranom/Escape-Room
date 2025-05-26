package cat.itacademy.project.business_logic.escaperoom.application;

import cat.itacademy.project.business_logic.escaperoom.domain.EscapeRoomRepository;
import cat.itacademy.project.shared.domain.dtos.escape_room.EscapeRoomDTO;

import java.util.Optional;

public class FindEscapeRoomByIdService  {
    private final EscapeRoomRepository repo;

    public FindEscapeRoomByIdService( EscapeRoomRepository repo) {
        this.repo = repo;
    }

    public Optional<EscapeRoomDTO> execute(int idToFind) {
        return repo.findById(idToFind);

    }
}
